package com.resume.portal.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UserEducation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String collegeName;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate endDate;
	private String summary;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCollegeName() {
		return collegeName;
	}
	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	public String getFormattedStartDate() {
		String startDateStr = null;
		try {
			startDateStr = startDate.format(DateTimeFormatter.ofPattern("MMM yyyy"));  //if from form user adds empty education without entering date, startDate will be null and fail here. To protect, try-catch is applied.
		} catch (Exception e) {
			startDate = null;
			startDateStr = "";
		}
		return startDateStr;
	}
	
	public String getFormattedEndDate() {
		String endDateStr = null;
		try {
			endDateStr = endDate.format(DateTimeFormatter.ofPattern("MMM yyyy"));
		} catch (Exception e) {
			endDate = null;
			endDateStr = "";
		}
		return endDateStr;
	}
}
