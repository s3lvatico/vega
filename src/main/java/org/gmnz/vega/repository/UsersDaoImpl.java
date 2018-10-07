package org.gmnz.vega.repository;


import org.gmnz.vega.VegaUtil;
import org.gmnz.vega.domain.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;


class UsersDaoImpl extends BasicDaoImpl implements UsersDao {

//@formatter:off

	private static final String SQL_FIND_ALL =
			" select u.user_name, u.full_name, group_concat(r.role_name) " +
			" from vega_user u join vega_role r on u.user_name = r.user_name " +
			" group by u.user_name, u.FULL_NAME ";

	private static final String SQL_FIND_BY_ID =
			"select " + 
			"	u.user_name, " + 
			"	u.full_name, " + 
			"	group_concat(r.role_name) " +
			"from " + 
			"	vega_user u " + 
			"join vega_role r on " + 
			"	u.user_name = r.user_name " + 
			"where u.user_name = ? " + 
			"group by " + 
			"	u.user_name,u.full_name ";
	
	private static final String SQL_FIND_ROLES = 
			"select distinct role_name from vega_role ";

	private static final String SQL_UPDATE_USER =
			"update vega_user set full_name = ? where user_name = ?";

	private static final String SQL_UPDATE_USER_W_PASSWORD =
			"update vega_user set full_name = ?, user_password = ? where user_name = ?";

	private static final String SQL_CLEAR_USER_ROLES =
			"delete from vega_role where user_name = ?";

	private static final String SQL_INSERT_USER_ROLES =
			"insert into vega_role values (?, ?)";
//@formatter:on



	@Override
	public List<User> findAll() throws DaoException {
		Statement s = null;
		ResultSet rs = null;
		try {
			s = connection.createStatement();
			rs = s.executeQuery(SQL_FIND_ALL);
			ArrayList<User> users = new ArrayList<>();
			while (rs.next()) {
				String userId = rs.getString(1);
				String fullName = rs.getString(2);
				String roles = rs.getString(3);
				User u = new User();
				u.setUserId(userId);
				u.setFullName(fullName);
				u.setRoles(Arrays.asList(roles.split(",")));
				users.add(u);
			}
			return users;
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("UsersDaoImpl.findAll error", e);
		}
		finally {
			releaseResources(s, rs);
		}
	}



	public Set<String> findRolesForUsersOtherThan(String userName) throws DaoException {
		PreparedStatement s = null;
		ResultSet rs = null;
		try {
			s = connection.prepareStatement("SELECT ROLE_NAME from VEGA_ROLE where USER_NAME <> ?");
			s.setString(1, userName);
			rs = s.executeQuery();
			HashSet<String> otherRoles = new HashSet<>();
			if (rs.next()) {
				otherRoles.add(rs.getString(1));
			}
			return otherRoles;
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("UsersDaoImpl.findRolesForUsersOtherThan error", e);
		}
		finally {
			releaseResources(s, rs);
		}
	}



	@Override
	public User findById(String userId) throws DaoException {
		PreparedStatement s = null;
		ResultSet rs = null;
		try {
			s = connection.prepareStatement(SQL_FIND_BY_ID);
			s.setString(1, userId);
			User u = null;

			rs = s.executeQuery();

			if (rs.next()) {
				u = new User();
				String id = rs.getString(1);
				String fullName = rs.getString(2);
				String roles = rs.getString(3);
				u.setUserId(id);
				u.setFullName(fullName);
				u.setRoles(Arrays.asList(roles.split(",")));
			}
			return u;
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("UsersDaoImpl.findById error", e);
		}
		finally {
			releaseResources(s, rs);
		}
	}



	@Override
	public List<String> findAllRoles() throws DaoException {
		Statement s = null;
		ResultSet rs = null;
		try {
			s = connection.createStatement();
			rs = s.executeQuery(SQL_FIND_ROLES);
			ArrayList<String> roles = new ArrayList<>();
			while (rs.next()) {
				roles.add(rs.getString(1));
			}
			return roles;
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("UsersDaoImpl.findAllRoles error", e);
		}
		finally {
			releaseResources(s, rs);
		}
	}



	public void updateUser(User user, String password) throws DaoException {
		PreparedStatement s = null;
		try {
			connection.setAutoCommit(false);
			boolean shouldUpdatePassword = !VegaUtil.stringNullOrEmpty(password);
			if (shouldUpdatePassword) {
				String hashedPassword = VegaUtil.getSha256Digest(password);
				s = connection.prepareStatement(SQL_UPDATE_USER_W_PASSWORD);
				s.setString(1, user.getFullName());
				s.setString(2, hashedPassword);
				s.setString(3, user.getUserId());
			}
			else {
				s = connection.prepareStatement(SQL_UPDATE_USER);
				s.setString(1, user.getFullName());
				s.setString(2, user.getUserId());
			}
			s.executeUpdate();

			s = connection.prepareStatement(SQL_CLEAR_USER_ROLES);
			s.setString(1, user.getUserId());
			s.executeUpdate();
			s = connection.prepareStatement(SQL_INSERT_USER_ROLES);
			for (String role : user.getRoles()) {
				s.setString(1, role);
				s.setString(2, user.getUserId());
				s.executeUpdate();
			}

			connection.commit();
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("UsersDaoImpl.updateUser error", e);
		}
		finally {
			releaseResources(s);
		}
	}

}
