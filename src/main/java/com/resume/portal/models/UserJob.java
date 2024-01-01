package com.resume.portal.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.ElementCollection;
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
	private LocalDate startDate;
	private LocalDate endDate;
	private Boolean currentJob;
	@ElementCollection(targetClass = String.class)
	private List<String> responsibilities = new ArrayList<>();
	
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
	public List<String> getResponsibilities() {
		return responsibilities;
	}
	public void setResponsibilities(List<String> responsibilities) {
		this.responsibilities = responsibilities;
	}
	
	public String getFormattedStartDate() {
		return startDate.format(DateTimeFormatter.ofPattern("MMM yyyy"));
	}
	
	public String getFormattedEndDate() {
		return endDate.format(DateTimeFormatter.ofPattern("MMM yyyy"));
	}
}
