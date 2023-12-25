package com.resume.portal.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.resume.portal.models.PortalUser;
import com.resume.portal.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<PortalUser> user = userRepository.findByUserName(username);
		user.orElseThrow(()-> new UsernameNotFoundException("Username not found: "+username));
		UserDetailsImpl userDetailsImpl = user.map(UserDetailsImpl::new).get();		//user.map(x -> new UserDetailsImpl(x));
		return userDetailsImpl;
	}

}
