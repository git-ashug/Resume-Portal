package com.resume.portal.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.resume.portal.models.UserJob;
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
	
	//Just for testing purpose
	@GetMapping("/save")
	public String save() {
		UserProfile userProfile = new UserProfile();
//		userProfile.setId(1);
		userProfile.setUserName("ashutosh");
		userProfile.setEmail("abc@gmail.com");
		userProfile.setFirstName("Ashutosh");
		userProfile.setLastName("Gupta");
		userProfile.setSummary("An IT Learner");
		userProfile.setTheme(2);
		userProfile.setEmail("xyz@gmail.com");
		userProfile.setPhone("22-12-5467585858");
		userProfile.setDesignation("Software Engineer");
		
		UserJob userJob1 = new UserJob();
//		userJob1.setId(1);
		userJob1.setCompany("Accenture");
		userJob1.setDesignation("Software Engineer 1");
		userJob1.setStartDate(LocalDate.of(2021, 06, 14));
		userJob1.setEndDate(LocalDate.of(2023, 06, 14));
		UserJob userJob2 = new UserJob();
//		userJob2.setId(2);
		userJob2.setCompany("Google");
		userJob2.setDesignation("Software Engineer 2");
		userJob2.setStartDate(LocalDate.of(2023, 06, 15));
		userJob2.setEndDate(LocalDate.of(2040, 06, 14));
		userJob2.setCurrentJob(true);
		userProfile.setJobs(List.of(userJob1,userJob2));
		
		userProfileRepository.save(userProfile);
		
		return "profile";
	}
}
