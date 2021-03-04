package com.group.kudos.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.group.kudos.models.User;
import com.group.kudos.services.UserService;

@Controller
public class HomeController {
	
	@Autowired
	private UserService uService; 
	
	@RequestMapping("/businessdashboard")
	public String businessDashboard(HttpSession session, Model model) {
		if(session.getAttribute("user_id").equals(null)) {
			return "redirect:/"; 
		}
		Long userId = (Long)session.getAttribute("user_id");
		User user = this.uService.findUserById(userId);
		model.addAttribute("user", user);
		return "businessDashboard.jsp"; 
	}
	
}
