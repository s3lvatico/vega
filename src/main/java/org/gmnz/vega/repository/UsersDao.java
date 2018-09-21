package org.gmnz.vega.repository;


import org.gmnz.vega.domain.Category;
import org.gmnz.vega.domain.User;

import java.util.List;


public interface UsersDao extends ConnectionOrientedDao {

	/*
	select u.user_name, u.full_name, group_concat(r.role_name SEPARATOR ',')
from vega_user u
       join vega_role r on u.user_name = r.user_name
group by u.user_name
	 */

	List<User> findAll() throws DaoException;
}
