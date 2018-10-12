package org.gmnz.vega.repository;


import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.gmnz.vega.domain.User;


public interface UsersDao extends ConnectionOrientedDao {

	List<User> findAll() throws DaoException;

	User findById(String userId) throws DaoException;

	void createUser(String userId, String fullName, String password, Collection<String> roles) throws DaoException;
	
	List<String> findAllRoles() throws DaoException;

	void updateUser(User user, String password) throws DaoException;

	Set<String> findRolesForUsersOtherThan(String userName) throws DaoException;

}
