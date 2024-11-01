package com.resume.portal.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UserJob {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String company;
	private String designation;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate endDate;
	private Boolean currentJob;
//	@ElementCollection(targetClass = String.class)
//	private List<String> responsibilities = new ArrayList<>()
	private String responsibilities;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
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
	public Boolean getCurrentJob() {
		return currentJob;
	}
	public void setCurrentJob(Boolean currentJob) {
		this.currentJob = currentJob;
	}
	public String getResponsibilities() {
		return responsibilities;
	}
	public void setResponsibilities(String responsibilities) {
		this.responsibilities = responsibilities;
	}
	public String getFormattedStartDate() {
		String startDateStr = null;
		try {
			startDateStr = startDate.format(DateTimeFormatter.ofPattern("MMM yyyy"));  //if from form user adds empty job without entering date, startDate will be null and fail here. To protect, try-catch is applied.
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
