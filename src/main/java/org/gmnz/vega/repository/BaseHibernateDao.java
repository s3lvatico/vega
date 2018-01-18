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



	protected <R> R wrapInTransaction(TxManagedExecutor<R> tme) throws DaoException {
		session = sessionFactory.openSession();
		tme.session = session;

		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			R result = tme.execute();

			tx.commit();

			return result;
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			if (e instanceof DaoException) {
				throw e;
			} else {
				throw new DaoException(e);
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

}
