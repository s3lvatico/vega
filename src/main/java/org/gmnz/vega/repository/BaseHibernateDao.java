package org.gmnz.vega.repository;


import org.gmnz.vega.base.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


class BaseHibernateDao {

	private SessionFactory sessionFactory;

	private Session session;



	BaseHibernateDao() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}



	protected Session openSession() {
		session = sessionFactory.openSession();
		return session;
	}



	protected void closeSession() {
		if (session != null) {
			session.close();
		}
	}

}
