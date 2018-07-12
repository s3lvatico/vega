package org.gmnz.vega.domain;


import java.util.*;


public class Report {

	private String subjectName;

	private Date createdOn;

	private Map<String, List<ToxicityRating>> reportData;



	public Report(String subjectName, Date createdOn) {
		this.subjectName = subjectName;
		this.createdOn = createdOn;
		reportData = new LinkedHashMap<>();
	}



	public void addRating(ToxicityRating rating) {
		String categoryName = rating.getAllergen().getCategory().getName();
		if (reportData.get(categoryName) == null) {
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



	@Override
	public String toString() {
		return "Report{" + "subjectName='" + subjectName + '\'' + ", createdOn=" + createdOn + ", reportData="
				+ reportData + '}';
	}
}
