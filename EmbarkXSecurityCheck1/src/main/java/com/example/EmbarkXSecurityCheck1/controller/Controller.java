package com.example.EmbarkXSecurityCheck1.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	@GetMapping("/hello")
	public String hello() {
		return "Welcome";
	}
	
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/user")
	public String userEndpoint() {
		return "Hello, User!";
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/admin")
	public String adminEndpoiint() {
		return "Hello, Admin!";
	}
}