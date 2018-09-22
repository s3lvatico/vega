package org.gmnz.vega.service;


import org.gmnz.vega.VegaException;
import org.gmnz.vega.domain.User;
import org.gmnz.vega.repository.DaoException;
import org.gmnz.vega.repository.DaoFactory;
import org.gmnz.vega.repository.UsersDao;

import java.util.List;


/**
 * creato da simone in data 07/07/2018.
 */
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

}
