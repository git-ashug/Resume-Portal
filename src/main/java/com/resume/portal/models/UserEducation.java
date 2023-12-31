package com.resume.portal.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
	private LocalDate startDate;
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
		return startDate.format(DateTimeFormatter.ofPattern("MMM yyyy"));
	}
	
	public String getFormattedEndDate() {
		return endDate.format(DateTimeFormatter.ofPattern("MMM yyyy"));
	}
}
