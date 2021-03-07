package com.group.kudos.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.group.kudos.services.UserService;

@Controller
public class HomeController {
	
	@Autowired
	private UserService uService; 
	
	@RequestMapping(value = {"/business/dashboard"})
	public String businessDashboard(Principal principal, Model model) {
		String username = principal.getName();
		model.addAttribute("user", uService.findByUsername(username));
		return "businessDashboard.jsp"; 
	}
	
	@RequestMapping(value = {"/userDashboard"})
	public String userDashboard(Principal principal, Model model) {
		String username = principal.getName();
		model.addAttribute("user", uService.findByUsername(username));
		return "user/profile.jsp"; 
	}
	
	
}
