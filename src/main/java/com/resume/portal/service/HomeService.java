package com.resume.portal.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.resume.portal.models.UserEducation;
import com.resume.portal.models.UserJob;
import com.resume.portal.models.UserProfile;
import com.resume.portal.repository.UserProfileRepository;

@Service
@Transactional
public class HomeService {

	private final UserProfileRepository userProfileRepository;

	public HomeService(UserProfileRepository userProfileRepository) {
		this.userProfileRepository = userProfileRepository;
	}
	
	public UserProfile getSavedUserProfile(String userName) {
		Optional<UserProfile> userProfileOptional = userProfileRepository.findByUserName(userName);
		userProfileOptional.orElseThrow(()-> new UsernameNotFoundException("Username not found: "+userName));
		UserProfile savedUserProfile = userProfileOptional.get();
		return savedUserProfile;
	}
	
	public UserProfile getUserProfile(String userName) {
		UserProfile userProfile = null;
		Optional<UserProfile> userProfileOptional = userProfileRepository.findByUserName(userName);
		if(!userProfileOptional.isPresent()) {
			userProfile = new UserProfile();
			userProfile.setUserName(userName);
		}else {
			userProfile = userProfileOptional.get();
		}
		return userProfile;
	}
	
	public void addItem(UserProfile userProfile, String itemType) {
		if("experience".equalsIgnoreCase(itemType)) {
			userProfile.getJobs().add(new UserJob());
		}else if("education".equalsIgnoreCase(itemType)) {
			userProfile.getEducations().add(new UserEducation());
		}else if("skill".equalsIgnoreCase(itemType)) {
			userProfile.getSkills().add("");
		}
	}
	
	public void deleteItem(UserProfile userProfile, String itemType, Integer index) {
		if ("experience".equals(itemType)) {
        	userProfile.getJobs().remove((int) index);
        }else if ("education".equals(itemType)) {
        	userProfile.getEducations().remove((int) index);
        }else if("skill".equalsIgnoreCase(itemType)) {
			userProfile.getSkills().remove((int) index);
		}
	}
	
	public void saveUserProfile(UserProfile userProfile) {
		userProfileRepository.save(userProfile);
	}
	
	
	
	
}
