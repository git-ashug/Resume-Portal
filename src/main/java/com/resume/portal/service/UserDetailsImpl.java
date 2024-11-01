package com.resume.portal.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.resume.portal.models.PortalUser;

public class UserDetailsImpl implements UserDetails{
	
	private String userName;
	private String password;
	private boolean isActive;
	
	public UserDetailsImpl(PortalUser user) {		//copying details from User class to UserDetailsImpl class, so that it can be returned by loadUserByUsername.
		this.userName = user.getUserName();
		this.password = user.getPassword();
		this.isActive = user.isActive();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return new ArrayList<>();
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return isActive;
	}

}
