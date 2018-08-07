package org.gmnz.vega.repository;


import java.util.Collection;

import org.gmnz.vega.domain.Report;


public interface ReportDao extends ConnectionOrientedDao {

	Collection<Report> findAll() throws DaoException;



	void createReport(Report r) throws DaoException;
	

}
