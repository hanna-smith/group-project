package com.group.kudos.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.group.kudos.models.Review;
import com.group.kudos.models.User;
import com.group.kudos.services.BusinessService;
import com.group.kudos.services.ReviewService;
import com.group.kudos.services.UserService;

@Controller
@RequestMapping("/review")
public class ReviewController {

	@Autowired
	private ReviewService rService;
	
	@Autowired
	private UserService uService;
	
	@Autowired
	private BusinessService bService;
	
	
						//write a new review
	
	@GetMapping("/new")
	private String newReview(@Valid @ModelAttribute("review") Review review, Model viewModel) {
		viewModel.addAttribute("reviews", this.rService.getReviews());
		viewModel.addAttribute("businesses", this.bService.getAll());
		return "/review/new.jsp";	
	}
	
	@PostMapping("/new")
	private String createReview(@Valid @ModelAttribute("review") Review review, Model viewModel, BindingResult result, HttpSession session) {
		if(result.hasErrors()) {
			return "/review/new.jsp";
		}
		
		Long userId = (Long)session.getAttribute("user_id");
		User userCreatedReview = this.uService.findUserById(userId);
		review.setBusReviewed(viewModel.getAttribute("business"));
		review.setReviewer(userCreatedReview);
		this.rService.createReview(review);
		return "redirect:/business";
	}
	
	

	
				
	
	
	
}
