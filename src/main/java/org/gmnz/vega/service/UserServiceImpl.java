package org.gmnz.vega.service;


import org.gmnz.vega.VegaException;
import org.gmnz.vega.domain.User;
import org.gmnz.vega.repository.DaoException;
import org.gmnz.vega.repository.DaoFactory;
import org.gmnz.vega.repository.UsersDao;

import java.util.List;


public class UserServiceImpl extends BasicServiceBean implements UserService {

	@Override
	public List<User> getAllUsers() throws VegaException {
		UsersDao dao = null;
		try {
			dao = DaoFactory.getInstance().createUsersDao();
			List<User> users = dao.findAll();
			return users;
		}
		catch (DaoException e) {
			e.printStackTrace();
			throw new VegaException("getAllUsers service error", e);
		}
		finally {
			finalizeDao(dao);
		}
	}



	@Override
	public User getUserById(String userId) throws VegaException {
		UsersDao dao = null;
		try {
			dao = DaoFactory.getInstance().createUsersDao();
			User user = dao.findById(userId);
			return user;
		}
		catch (DaoException e) {
			e.printStackTrace();
			throw new VegaException("getUserById service error", e);
		}
		finally {
			finalizeDao(dao);
		}
	}



	public List<String> getAllRoles() throws VegaException {
		UsersDao dao = null;
		try {
			dao = DaoFactory.getInstance().createUsersDao();
			List<String> roles = dao.findAllRoles();
			return roles;
		}
		catch (DaoException e) {
			e.printStackTrace();
			throw new VegaException("getAllRoles service error", e);
		}
		finally {
			finalizeDao(dao);
		}
	}



	@Override
	public void updateUser(User user) throws VegaException {
		UsersDao dao = null;
		try {
			dao = DaoFactory.getInstance().createUsersDao();
			dao.updateUser(user, null);
		}
		catch (DaoException e) {
			e.printStackTrace();
			throw new VegaException("updateUser service error", e);
		}
		finally {
			finalizeDao(dao);
		}
	}



	@Override
	public void updateUser(User user, String password) throws VegaException {
		UsersDao dao = null;
		try {
			dao = DaoFactory.getInstance().createUsersDao();
			dao.updateUser(user, password);
		}
		catch (DaoException e) {
			e.printStackTrace();
			throw new VegaException("updateUser service error", e);
		}
		finally {
			finalizeDao(dao);
		}
	}

}
