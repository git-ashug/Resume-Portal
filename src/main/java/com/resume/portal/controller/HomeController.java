package com.resume.portal.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.resume.portal.models.UserEducation;
import com.resume.portal.models.UserJob;
import com.resume.portal.models.UserProfile;
import com.resume.portal.repository.UserProfileRepository;

//@RestController
@Controller
@Transactional
public class HomeController {

	private final UserProfileRepository userProfileRepository;
	
	public HomeController(UserProfileRepository userProfileRepository) {	//constructor injection, preferred way
		super();
		this.userProfileRepository = userProfileRepository;
	}

	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	//TO DO: Move this repetitive logic of fetching user from DB to service layer and then inject bean of service layer
	@GetMapping("/edit")
	public String edit(Principal principal, Model model) {	//Principal object given by java.security that contains info about currently logged in user
		String userName = principal.getName();
		Optional<UserProfile> userProfileOptional = userProfileRepository.findByUserName(userName);
		//userProfileOptional.orElseThrow(()-> new RuntimeException("Username not found: "+userName));	// should not throw UsernameNotFoundException as this exception is related to Spring Security. For this case, we should have our own custom exception.
		UserProfile userProfile = null;
			if(!userProfileOptional.isPresent()) {
				userProfile = new UserProfile();
				userProfile.setUserName(userName);
			}else {
				userProfile = userProfileOptional.get();
			}
		
//		userProfile.getJobs().add(new UserJob());
//		if("experience".equalsIgnoreCase(add)) {
//			userProfile.getJobs().add(new UserJob());
//		}else if("education".equalsIgnoreCase(add)) {
//			userProfile.getEducations().add(new UserEducation());
//		}else if("skill".equalsIgnoreCase(add)) {
//			userProfile.getSkills().add("");
//		}
		userProfileRepository.save(userProfile);
		model.addAttribute("userProfile",userProfile);
		return "profile-edit";
	}
	
//	@GetMapping("/delete")
//	public String delete(Principal principal, Model model, @RequestParam String type, @RequestParam int index) {
//		String userName = principal.getName();
//		Optional<UserProfile> userProfileOptional = userProfileRepository.findByUserName(userName);
//		userProfileOptional.orElseThrow(()-> new RuntimeException("Username not found: "+userName));	// should not throw UsernameNotFoundException as this exception is related to Spring Security. For this case, we should have our own custom exception.
//		UserProfile userProfile = userProfileOptional.get();
//		if("experience".equalsIgnoreCase(type)) {
//			userProfile.getJobs().remove(index);
//		}else if("education".equalsIgnoreCase(type)) {
//			userProfile.getEducations().remove(index);
//		}else if("skill".equalsIgnoreCase(type)) {
//			userProfile.getSkills().remove(index);
//		}
//		
//		userProfileRepository.save(userProfile);
//		model.addAttribute("userProfile",userProfile);
//		return "profile-edit";
//		//return "redirect:/edit/" + userName;
//	}
	
	@PostMapping("/edit")
	public String postEdit(Principal principal, @ModelAttribute UserProfile userProfile,
			@RequestParam("action") String action,
			@RequestParam("itemType") String itemType,
            @RequestParam(value = "index", required = false) Integer index) {
		//save the model object obtained by edit form here
		String userName = principal.getName();
		Optional<UserProfile> userProfileOptional = userProfileRepository.findByUserName(userName);
		userProfileOptional.orElseThrow(()-> new RuntimeException("Username not found: "+userName));
		UserProfile savedUserProfile = userProfileOptional.get();
		userProfile.setId(savedUserProfile.getId());  // as we are not exposing ID and username in the form as we don't want users to changes their ID/userName, we need to first find IS and userName and then use it while saving so that it gets updated in DB and should not create a new record.
		userProfile.setUserName(userName);
		System.out.println("Here: action = "+ action+ " ;itemType = "+itemType+ " ;index = "+ index);
		if("add".equals(action)) {
			if("experience".equalsIgnoreCase(itemType)) {
				userProfile.getJobs().add(new UserJob());
			}else if("education".equalsIgnoreCase(itemType)) {
				userProfile.getEducations().add(new UserEducation());
			}else if("skill".equalsIgnoreCase(itemType)) {
				userProfile.getSkills().add("");
			}
		}else if ("delete".equals(action) && index != null) {
			System.out.println("In delete: action = "+ action);
            if ("experience".equals(itemType)) {
            	userProfile.getJobs().remove((int) index);
            }else if ("education".equals(itemType)) {
            	userProfile.getEducations().remove((int) index);
            }else if("skill".equalsIgnoreCase(itemType)) {
				userProfile.getSkills().remove((int) index);
			}
        }else if("submit".equals(action)) {
        	userProfileRepository.save(userProfile);
        	return "redirect:/view/"+ userName;
        }
		userProfileRepository.save(userProfile);
		System.out.println("Saved successfully");
//		return "redirect:/view/"+ userName;
		return "redirect:/edit";
	}
	
//	@PostMapping("/submitProfile")
//    public String submitProfile(Principal principal, @ModelAttribute UserProfile userProfile) {
//		String userName = principal.getName();
//		Optional<UserProfile> userProfileOptional = userProfileRepository.findByUserName(userName);
//		userProfileOptional.orElseThrow(()-> new RuntimeException("Username not found: "+userName));
//		UserProfile savedUserProfile = userProfileOptional.get();
//		userProfile.setId(savedUserProfile.getId());  // as we are not exposing ID and username in the form as we don't want users to changes their ID/userName, we need to first find IS and userName and then use it while saving so that it gets updated in DB and should not create a new record.
//		userProfile.setUserName(userName);
//		userProfileRepository.save(userProfile);
//        return "redirect:/view/"+ userName; 
//    }
	
	@GetMapping("/test1")
	public String test1() {
		return "resume-templates/1/index";		//no need to append .html
	}
	
	@GetMapping("/test2")
	public String test2() {
		return "resume-templates/2/index.html";
	}
	
	
	@GetMapping("/view/{userName}")
	public String view(@PathVariable String userName, Model model, Principal principal) {
		if(principal != null && principal.getName() != null && principal.getName() != "") {	// if current user is logged in and is seeing his/her own profile, show them Edit Link on their profile
			boolean currentUsersProfile = principal.getName().equalsIgnoreCase(userName);
			model.addAttribute("currentUsersProfile",currentUsersProfile);
		}
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
		userJob1.getResponsibilities().add("Feature development and prod issue fix");
		userJob1.getResponsibilities().add("Holding brown bag sessions for team mates");
		UserJob userJob2 = new UserJob();
//		userJob2.setId(2);
		userJob2.setCompany("Google");
		userJob2.setDesignation("Software Engineer 2");
		userJob2.setStartDate(LocalDate.of(2023, 06, 15));
		userJob2.setEndDate(LocalDate.of(2040, 06, 14));
		userJob2.setCurrentJob(true);
		userJob2.getResponsibilities().add("Feature development and production issue fix");
		userJob2.getResponsibilities().add("Holding brown bag sessions for team mates and juniors");
		userProfile.setJobs(List.of(userJob1,userJob2));
		
		UserEducation userEd1 = new UserEducation();
		userEd1.setCollegeName("University of Toronto");
		userEd1.setSummary("Currently studying as an undergraduate, and is going to acquire a Bachelor’s degree at some point.Currently on a long leave for jobs.");
		userEd1.setStartDate(LocalDate.of(2019, 06, 15));
		userEd1.setEndDate(LocalDate.of(2021, 06, 14));
		UserEducation userEd2 = new UserEducation();
		userEd2.setCollegeName("School of Science & Technology");
		userEd2.setSummary("Acquired International Baccalaureate Diploma for Secondary School.Studied in India");
		userEd2.setStartDate(LocalDate.of(2017, 06, 15));
		userEd2.setEndDate(LocalDate.of(2019, 06, 14));
		userProfile.setEducations(List.of(userEd1,userEd2));
		
		userProfile.setSkills(List.of("Java", "C" , "C++" , "Spring", "Springboot" , "Backend Development"));
		
		userProfileRepository.save(userProfile);
		
		return "profile";
	}
}
