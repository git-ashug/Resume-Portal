package com.resume.portal.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.resume.portal.models.UserProfile;
import com.resume.portal.service.HomeService;

@Controller
public class HomeController {

	private final HomeService homeService;
	
	public HomeController(HomeService homeService) {
		this.homeService = homeService;
	}

	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	@GetMapping("/edit")
	public String edit(Principal principal, Model model) {	//Principal object is given by java.security that contains info about currently logged in user
		String userName = principal.getName();
		UserProfile userProfile = homeService.getUserProfile(userName);
		homeService.saveUserProfile(userProfile);
		model.addAttribute("userProfile",userProfile);
		return "profile-edit";
	}
	
	@PostMapping("/edit")
	public String postEdit(Principal principal, @ModelAttribute UserProfile userProfile,
						   @RequestParam("action") String action, @RequestParam("itemType") String itemType,
						   @RequestParam(value = "index", required = false) Integer index) {
		String userName = principal.getName();
		UserProfile savedUserProfile = homeService.getSavedUserProfile(userName);
		userProfile.setId(savedUserProfile.getId());  // as we are not exposing ID and username in the form as we don't want users to changes their ID/userName, we need to first find IS and userName and then use it while saving so that it gets updated in DB and should not create a new record.
		userProfile.setUserName(userName);
		System.out.println("Here: action = "+ action+ " ;itemType = "+itemType+ " ;index = "+ index);
		if("add".equals(action)) {
			homeService.addItem(userProfile, itemType);
		}else if ("delete".equals(action) && index != null) {
			homeService.deleteItem(userProfile, itemType, index);
        }else if("submit".equals(action)) {
        	homeService.saveUserProfile(userProfile);
        	return "redirect:/view/"+ userName;
        }
		homeService.saveUserProfile(userProfile);
		return "redirect:/edit";
	}
	
	
	@GetMapping("/view/{userName}")
	public String view(@PathVariable String userName, Model model, Principal principal) {
		if(principal != null && principal.getName() != null && principal.getName() != "") {	// if current user is logged in and is seeing his/her own profile, show them Edit Link on their profile
			boolean currentUsersProfile = principal.getName().equalsIgnoreCase(userName);
			model.addAttribute("currentUsersProfile",currentUsersProfile);
		}
		UserProfile userProfile = homeService.getSavedUserProfile(userName);
		model.addAttribute("userProfile",userProfile);
		return "resume-templates/"+userProfile.getTheme()+"/index";
	}
}