package com.resume.portal.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
public class PortalUser {	// as User is a keyword, it cannot be used as table name in latest version of H2 Database. Reference: http://www.h2database.com/html/advanced.html#keywords

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(unique = true)
	private String userName;
	private String password;
	private boolean isActive;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
}
