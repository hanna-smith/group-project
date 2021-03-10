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
	public String businessPage(@PathVariable("id")Long id, @ModelAttribute("business")Business business,@ModelAttribute("user")User user, @ModelAttribute("review")Review review, Model model) {
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
		String username = user.getName();
		review.setReviewer(uService.findByUsername(username));
		review.setId(null);
		this.rService.createReview(review);
		return "redirect:/busDetails/" + id;
	}
	
	@GetMapping("/busDetails/{id}/claimBusiness")
	public String claimBusiness(Principal user, @PathVariable("id")Long id, Model model) {
		Business business = this.bService.getBusiness(id);
		String username = user.getName();
		business.setOwner(uService.findByUsername(username));
		this.bService.updateBusiness(business);
		return "redirect:/business/dashboard";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		this.rService.deleteReview(id);
		return "redirect:/userDashboard";
		}
	
	@GetMapping("/review/{id}/edit")
	public String editReview(@PathVariable("id") Long id, Model model) {
		Review review = rService.findReviewById(id);
		model.addAttribute("review", review);
		return "editReview.jsp";
	}
	
	@PostMapping("/review/{id}/edit")
	public String updateReview(@PathVariable("id") Long id, Model model, @ModelAttribute Review review) {
		Review currentReview = rService.findReviewById(id);
		currentReview.setStars(review.getStars());
		currentReview.setTitle(review.getTitle());
		currentReview.setContent(review.getContent());
		rService.updateReview(currentReview);
		return "redirect:/userDashboard";
	}
}
