package org.gmnz.vega.repository;


import org.gmnz.vega.domain.User;

import java.util.List;


public interface UsersDao extends ConnectionOrientedDao {

	List<User> findAll() throws DaoException;


	User findById(String userId) throws DaoException;


	List<String> findAllRoles() throws DaoException;


	void updateUser(User user, String password) throws DaoException;

}
