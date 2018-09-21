package org.gmnz.vega.repository;


import org.gmnz.vega.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


class UsersDaoImpl extends BasicDaoImpl implements UsersDao {

//@formatter:off

	private static final String FIND_ALL_SQL =
			" select u.user_name, u.full_name, group_concat(r.role_name SEPARATOR ',') " +
			" from vega_user u join vega_role r on u.user_name = r.user_name " +
			" group by u.user_name" ;

//@formatter:on



	@Override
	public List<User> findAll() throws DaoException {
		Statement s = null;
		ResultSet rs = null;
		try {
			s = connection.createStatement();
			rs = s.executeQuery(FIND_ALL_SQL);
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


}
