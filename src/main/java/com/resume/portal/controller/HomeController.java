package com.resume.portal.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping("/")
	public String home() {
		return "Hello";
	}
	
	@GetMapping("/edit")
	public String edit() {
		return "Edit Page";
	}
}
