package com.resume.portal.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.resume.portal.models.UserProfile;
import com.resume.portal.repository.UserProfileRepository;

//@RestController
@Controller
public class HomeController {

	private final UserProfileRepository userProfileRepository;
	
	public HomeController(UserProfileRepository userProfileRepository) {	//constructor injection, preferred way
		super();
		this.userProfileRepository = userProfileRepository;
	}

	@GetMapping("/")
	public String home() {
		return "Hello";
	}
	
	@GetMapping("/edit")
	public String edit() {
		return "Edit Page";
	}
	
	@GetMapping("/test1")
	public String test1() {
		return "resume-templates/1/index";		//no need to append .html
	}
	
	@GetMapping("/test2")
	public String test2() {
		return "resume-templates/2/index.html";
	}
	
	
	@GetMapping("/view/{userName}")
	public String view(@PathVariable String userName, Model model) {
		Optional<UserProfile> userProfileOptional = userProfileRepository.findByUserName(userName);
		userProfileOptional.orElseThrow(()-> new RuntimeException("Username not found: "+userName));	// should not throw UsernameNotFoundException as this exception is related to Spring Security. For this case, we should have our own custom exception.
		UserProfile userProfile = userProfileOptional.get();
		model.addAttribute("userProfile",userProfile);
		return "resume-templates/"+userProfile.getTheme()+"/index";
	}
}
