package com.group.kudos.controllers;

import java.util.LinkedHashMap;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SearchController {
	
	@RequestMapping("/")
	public String HomeScreen() {
		return "home.jsp";
	}
	
	@PostMapping("/search")
	public String search(HttpSession session, @RequestBody LinkedHashMap<String, String> body) {
		System.out.println(body);
		return "searchResults.jsp";
	}
}
