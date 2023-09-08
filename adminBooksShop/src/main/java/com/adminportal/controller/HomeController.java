package com.adminportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/")
public class HomeController {

	@GetMapping("/")
	public String index(){
		return "redirect:/home";
	}
	
	@GetMapping("/home")
	public String home(){
		return "home";
	}
	
	@GetMapping("/loginAdmin")
	public String login(){
		return "loginAdmin";
	}
}

