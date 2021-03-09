package com.group.kudos.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.group.kudos.models.Business;
import com.group.kudos.models.Review;
import com.group.kudos.models.User;
import com.group.kudos.services.BusinessService;
import com.group.kudos.services.ReviewService;
import com.group.kudos.services.UserService;

@Controller
public class ReviewController {

	@Autowired
	private ReviewService rService;
	
	@Autowired
	private UserService uService; 
	
	@Autowired
	private BusinessService bService;
	
	

	@GetMapping("/busDetails/{id}")
	public String businessPage(@PathVariable("id")Long id, @ModelAttribute("business")Business business,@ModelAttribute User user, @ModelAttribute("review")Review review, Model model) {
		model.addAttribute("business", bService.getBusiness(id));
		List<Review> allReviews = this.rService.findByBusinessId(id);
		model.addAttribute("reviews", allReviews);
		model.addAttribute("user", user);
		return "review/review.jsp"; 
	}
	
	@PostMapping("/busDetails/{id}/newReview")
	public String createReview(@PathVariable("id")Long id, @ModelAttribute("business")Business business, @ModelAttribute("review")Review review, Principal user, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("business", bService.getBusiness(id));
			List<Review> allReviews = this.rService.findByBusinessId(id);
			model.addAttribute("reviews", allReviews);
			String username = user.getName();
			model.addAttribute("user", uService.findByUsername(username));
			return "review/review.jsp"; 
		}
		this.rService.createReview(review);
		return "redirect:/busDetails/" + id;
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		this.rService.deleteReview(id);
		return "redirect:/userDashboard";
		}
}
