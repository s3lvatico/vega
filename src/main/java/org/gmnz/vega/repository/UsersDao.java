package org.gmnz.vega.repository;


import java.util.List;

import org.gmnz.vega.domain.User;


public interface UsersDao extends ConnectionOrientedDao {

	List<User> findAll() throws DaoException;


	User findById(String userId) throws DaoException;


	List<String> findAllRoles() throws DaoException;

}
