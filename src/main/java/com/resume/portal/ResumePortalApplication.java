package com.resume.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.resume.portal.repository.UserRepository;

@SpringBootApplication
//@EnableJpaRepositories(basePackages = "com.resume.portal.repository")	//optional as we have all the repositories in sub package.
public class ResumePortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResumePortalApplication.class, args);
	}

}
