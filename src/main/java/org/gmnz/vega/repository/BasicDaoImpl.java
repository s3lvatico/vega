package org.gmnz.vega.repository;


import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;


class BasicDaoImpl {

	protected JdbcTemplate jdbcTemplate;

	protected TransactionTemplate transactionTemplate;



	protected BasicDaoImpl(DataSource dataSource, PlatformTransactionManager transactionManager) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		transactionTemplate = new TransactionTemplate(transactionManager);
	}

}
