package org.gmnz.vega.repository;


import org.gmnz.vega.domain.Report;

import java.util.Collection;


public interface ReportDao extends ConnectionOrientedDao {

	Collection<Report> findAll() throws DaoException;



	void createReport(Report r) throws DaoException;



	Report findById(String id) throws DaoException;



	Report getSummaryById(String id) throws DaoException;



	void remove(String id) throws DaoException;

}
