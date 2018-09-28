package org.gmnz.vega.repository;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.gmnz.vega.domain.User;


class UsersDaoImpl extends BasicDaoImpl implements UsersDao {

//@formatter:off

	private static final String SQL_FIND_ALL =
			" select u.user_name, u.full_name, group_concat(r.role_name SEPARATOR ',') " +
			" from vega_user u join vega_role r on u.user_name = r.user_name " +
			" group by u.user_name" ;

	private static final String SQL_FIND_BY_ID =
			"select " + 
			"	u.user_name, " + 
			"	u.full_name, " + 
			"	group_concat(r.role_name separator ',') " + 
			"from " + 
			"	vega_user u " + 
			"join vega_role r on " + 
			"	u.user_name = r.user_name " + 
			"where u.user_name = ? " + 
			"group by " + 
			"	u.user_name ";
	
	private static final String SQL_FIND_ROLES = 
			"select distinct vr.role_name from vega_role vr";
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
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("UsersDaoImpl.findAll error", e);
		} finally {
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
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("UsersDaoImpl.findById error", e);
		} finally {
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
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("UsersDaoImpl.findAllRoles error", e);
		} finally {
			releaseResources(s, rs);
		}
	}

}