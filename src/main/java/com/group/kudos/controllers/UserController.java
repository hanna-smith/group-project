package com.group.kudos.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.group.kudos.models.User;
import com.group.kudos.services.UserService;
import com.group.kudos.validators.UserValidator;



		@Controller
		public class UserController {
		
		@Autowired
		private UserService uService; 
		@Autowired
		private UserValidator uValidator; 
		
		
		
		@GetMapping("/registration")
		public String registerForm(@ModelAttribute("user") User user, Model model) {
		    return "registrationPage.jsp";
		}
		
		@PostMapping("/registration")
		public String registration(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
		    uValidator.validate(user, result);
			if (result.hasErrors()) {
		        return "registrationPage.jsp";
		    }
		    this.uService.saveWithUserRole(user);
		    return "redirect:/login";
		}
		
		@PostMapping("/businessRegistration")
		public String createBusinessUser(@Valid @ModelAttribute("user")User user, BindingResult result, Model model) {
			 uValidator.validate(user, result);
		        if (result.hasErrors()) {
		            return "registrationPage.jsp";
		        }
		        uService.saveUserWithBusinessRole(user);
		        return "redirect:/login";
		}
		
		@GetMapping("/login")
		public String login(@RequestParam(value="error", required=false) String error, @RequestParam(value="logout", required=false) String logout, Model model) {
			if(error != null) {
		        model.addAttribute("errorMessage", "Invalid Credentials, Please try again.");
				}
		    if(logout != null) {
		        model.addAttribute("logoutMessage", "Logout Successful!");
		    	}
		    return "login.jsp";
			}
		
		
		@GetMapping("/user/{id}")
		public String showUser(@PathVariable("id") Long id, Model viewModel) {
			viewModel.addAttribute("user", this.uService.findUserById(id));
			return "/user/profile.jsp";
		}
		
		
		
		}
		
		
