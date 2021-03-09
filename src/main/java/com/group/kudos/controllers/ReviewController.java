package com.group.kudos.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

	
	@GetMapping("")
	public String reviewComments(HttpSession session, Model viewModel) {
		Long userId = (Long)session.getAttribute("user_id");
		viewModel.addAttribute("user", this.uService.findUserById(userId));
		viewModel.addAttribute("review", this.rService.getReviews());
		
		return "commentview.jsp";
	}
	
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
	
	@GetMapping("/edit/{id}")
	public String updateReview(@PathVariable("id") Long id, HttpSession session, Model viewModel) {
		Long userId = (Long)session.getAttribute("user_id");
		Review review = this.rService.findReviewById(id);
		if(userId == null) {
			return "redirect:/"; 
		}
		if(review == null || review.getThisUser().getId().equals(userId)) {
			return "redirect:/main"; 
		}
		viewModel.addAttribute("review", review);
		viewModel.addAttribute("user_id", userId);
		return "review.jsp"; 
	}
	
	@PostMapping("/edit/{id}")
	public String update(@Valid @ModelAttribute("review") Review review, BindingResult result, @PathVariable("id")Long id, HttpSession session, Model viewModel) {
		if(result.hasErrors()) {
			Long userId = (Long)session.getAttribute("user_id");
			viewModel.addAttribute("review", review);
			viewModel.addAttribute("user_id", userId);
			return "review.jsp"; 
			
		}
		this.rService.updateReview(review);
		return "redirect:/"; 
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		this.rService.deleteReview(id);
		return "redirect:/main";
		}
	
	
}
