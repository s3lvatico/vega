package org.gmnz.vega.domain;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.gmnz.vega.VegaUtil;


public class Report {

	private String id;

	private String subjectName;

	private Date createdOn;

	private Map<String, List<ToxicityRating>> reportData;



	public Report(String subjectName, Date createdOn) {
		this.subjectName = subjectName;
		this.createdOn = createdOn;
		reportData = new LinkedHashMap<>();
		id = VegaUtil.getSha256Digest(this.subjectName, this.createdOn);
	}



	public Report(String id, String subjectName, Date creationDate) {
		this.id = id;
		this.subjectName = subjectName;
		this.createdOn = creationDate;
		reportData = new LinkedHashMap<>();
	}



	public void addRating(ToxicityRating rating) {
		String categoryName = rating.getAllergen().getCategory().getName();
//		if (reportData.get(categoryName) == null) {
		if (!reportData.containsKey(categoryName)) {
			reportData.put(categoryName, new ArrayList<>());
		}
		reportData.get(categoryName).add(rating);
	}



	public Set<String> getCategories() {
		return Collections.unmodifiableSet(reportData.keySet());
	}



	public List<ToxicityRating> getRatings(String categoryName) {
		List<ToxicityRating> ratings = reportData.get(categoryName);
		return ratings == null ? new ArrayList<>() : ratings;
	}



	public String getSubjectName() {
		return subjectName;
	}



	public Date getCreationDate() {
		return createdOn;
	}



	public String getId() {
		return id;
	}



	@Override
	public String toString() {
		return "Report{" + "subjectName='" + subjectName + '\'' + ", createdOn=" + createdOn + ", reportData="
				+ reportData + '}';
	}



	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Report report = (Report) o;
		return Objects.equals(id, report.id);
	}



	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
