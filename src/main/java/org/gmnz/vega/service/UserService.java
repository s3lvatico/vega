package org.gmnz.vega.service;


import org.gmnz.vega.VegaException;
import org.gmnz.vega.domain.User;

import java.util.Collection;
import java.util.List;


/**
 * creato da simone in data 07/07/2018.
 */
public interface UserService {

	List<User> getAllUsers() throws VegaException;

	User getUserById(String userId) throws VegaException;

	void createUser(String userId, String fullName, String password, Collection<String> roles) throws VegaException;

	List<String> getAllRoles() throws VegaException;

	void updateUser(User user) throws VegaException;

	void updateUser(User user, String password) throws VegaException;

	void removeUser(String userId) throws VegaException;

}
