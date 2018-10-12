package org.gmnz.vega.service;


import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.gmnz.vega.VegaException;
import org.gmnz.vega.domain.User;
import org.gmnz.vega.repository.DaoException;
import org.gmnz.vega.repository.DaoFactory;
import org.gmnz.vega.repository.UsersDao;


public class UserServiceImpl extends BasicServiceBean implements UserService {

	@Override
	public List<User> getAllUsers() throws VegaException {
		UsersDao dao = null;
		try {
			dao = DaoFactory.getInstance().createUsersDao();
			List<User> users = dao.findAll();
			return users;
		} catch (DaoException e) {
			e.printStackTrace();
			throw new VegaException("getAllUsers service error", e);
		} finally {
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
		} catch (DaoException e) {
			e.printStackTrace();
			throw new VegaException("getUserById service error", e);
		} finally {
			finalizeDao(dao);
		}
	}



	public List<String> getAllRoles() throws VegaException {
		UsersDao dao = null;
		try {
			dao = DaoFactory.getInstance().createUsersDao();
			List<String> roles = dao.findAllRoles();
			return roles;
		} catch (DaoException e) {
			e.printStackTrace();
			throw new VegaException("getAllRoles service error", e);
		} finally {
			finalizeDao(dao);
		}
	}



	private void checkRolesConsistency(User user, UsersDao dao) throws VegaException {
		try {
			Set<String> otherRoles = dao.findRolesForUsersOtherThan(user.getUserId());
			boolean otherAdminsPresent = otherRoles.contains("v-admin");
			boolean userIsAdmin = user.getRoles().contains("v-admin");
			if (!userIsAdmin && !otherAdminsPresent) {
				throw new VegaException("Control must be mantained. There must always be a Lich King.");
			}
		} catch (DaoException e) {
			e.printStackTrace();
			throw new VegaException("checkRolesConsistency service error", e);
		}
	}



	@Override
	public void updateUser(User user) throws VegaException {
		updateUser(user, null);
	}



	@Override
	public void updateUser(User user, String password) throws VegaException {
		UsersDao dao = null;
		try {
			dao = DaoFactory.getInstance().createUsersDao();
			checkRolesConsistency(user, dao);
			dao.updateUser(user, password);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new VegaException("updateUser service error", e);
		} finally {
			finalizeDao(dao);
		}
	}



	@Override
	public void createUser(String userId, String fullName, String password, Collection<String> roles) throws VegaException {
		// salto di proposito la validazione sull'input
		UsersDao dao = null;
		try {
			dao = DaoFactory.getInstance().createUsersDao();
			if (dao.findById(userId) != null) {
				String errorMessage = String.format("user with id [%s] already exists", userId);
				throw new VegaException(errorMessage);
			}
			dao.createUser(userId, fullName, password, roles);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new VegaException("createUser service error", e);
		} finally {
			finalizeDao(dao);
		}

	}

}
