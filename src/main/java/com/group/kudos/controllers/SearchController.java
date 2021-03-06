package com.group.kudos.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {
	
	@RequestMapping("/")
	public String HomeScreen() {
		return "home.jsp";
	}
	
	@RequestMapping(value = "/search")
	public String search(HttpSession session, @RequestParam("searchTerm") String searchTerm, @RequestParam("location") String location) {
		System.out.println("SEARCHING");
		System.out.println(searchTerm + " in " + location);
		return "searchResults.jsp";
	}
}
