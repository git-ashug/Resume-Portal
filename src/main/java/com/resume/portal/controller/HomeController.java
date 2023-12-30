package com.resume.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

//@RestController
@Controller
public class HomeController {

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
		model.addAttribute("userName",userName);
		return "profile";
	}
}
