package org.gmnz.vega.repository;


import org.hibernate.Session;


abstract class TxManagedExecutor<R> {


	protected Session session;



	protected abstract R execute();


}
