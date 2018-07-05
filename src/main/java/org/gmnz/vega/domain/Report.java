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



	public void addRating(String category, ToxicityRating rating) {
		if (reportData.get(category) == null) {
			reportData.put(category, new ArrayList<>());
		}
		reportData.get(category).add(rating);
	}



	public Set<String> getCategories() {
		return Collections.unmodifiableSet(reportData.keySet());
	}



	public List<ToxicityRating> getRatings(String category) {
		List<ToxicityRating> toxicityRatingList = reportData.get(category);
		return toxicityRatingList == null ? new ArrayList<>() : toxicityRatingList;
	}



	@Override
	public String toString() {
		return "Report{" + "subjectName='" + subjectName + '\'' + ", createdOn=" + createdOn + ", reportData="
				+ reportData + '}';
	}
}
