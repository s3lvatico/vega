package org.gmnz.vega.repository;


import org.gmnz.vega.domain.User;

import java.util.List;
import java.util.Set;


public interface UsersDao extends ConnectionOrientedDao {

	List<User> findAll() throws DaoException;


	User findById(String userId) throws DaoException;


	List<String> findAllRoles() throws DaoException;


	void updateUser(User user, String password) throws DaoException;

	Set<String> findRolesForUsersOtherThan(String userName) throws DaoException;

}
