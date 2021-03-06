package com.group.kudos.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.group.kudos.models.Business;
import com.group.kudos.services.BusinessService;

@Controller
public class SearchController {
	
	private BusinessService bService;
	
	public SearchController(BusinessService bService) {
		this.bService = bService;
	}
	
	@RequestMapping("/")
	public String HomeScreen() {
		return "home.jsp";
	}
	
	@RequestMapping(value = "/search")
	public String search(@ModelAttribute("business") Business business, HttpSession session, @RequestParam("searchTerm") String searchTerm, @RequestParam("location") String location, Model model) {
		List<Business> businesses = bService.bingSearch(searchTerm, location);
		model.addAttribute("searchTerm", searchTerm);
		model.addAttribute("location", location);
		model.addAttribute("businesses", businesses);
		return "searchResults.jsp";
	}
	
	@PostMapping("/business/new")
	public String newBusiness(HttpSession session, @Valid @ModelAttribute("business") Business business) {
		if (business.getId() == null) {
			business = bService.addBusiness(business);
		}
		return "redirect:/business/" + business.getId().toString();
	}
}
