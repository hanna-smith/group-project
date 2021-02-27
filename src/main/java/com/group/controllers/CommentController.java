package com.group.controllers;

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

import com.goup.models.Comment;
import com.group.services.CommentService;

@Controller
@RequestMapping("/review/{id}")
public class CommentController {
	@Autowired
	private CommentService cServ;
	
	//Autowire for user
	
	//Autowire for business
	
	//Autowire for review
	
	@GetMapping("")
	public String reviewComments(HttpSession session, Model viewModel) {
		Long userId = (Long)session.getAttribute("user_id");
		viewModel.addAttribute("comments", this.cServ.getComments());
		//viewModel.addAttribute("user", this.uServ.findUserById(userId));
		//viewModel.addAttribute("review", this.rServ.findReviewById(reviewId));
		
		return "commentview.jsp";
	}
	
	@GetMapping("/new")
	public String newComment(@ModelAttribute("comment") Comment comment, HttpSession session, Model viewModel) {
		Long userId = (Long)session.getAttribute("user_id");
		viewModel.addAttribute("user_id", userId);
		return "commentview.jsp";
	}
	
	@PostMapping("/new")
	public String create(@Valid @ModelAttribute("comment") Comment comment, BindingResult result, HttpSession session, Model viewModel) {
		if(result.hasErrors()) {
			Long userId = (Long)session.getAttribute("user_id");
			viewModel.addAttribute("user_id", userId);
			return "commentview.jsp";
		}
		this.cServ.create(comment);
		return "commentview.jsp";
	}
	
	@GetMapping("/comment/edit/{id}")
	public String updateComment(@PathVariable("id") Long id, HttpSession session, Model viewModel) {
		Long userId = (Long)session.getAttribute("user_id");
		Comment comment = this.cServ.findById(id);
		if(userId == null) {
			return "redirect:/"; 
		}
		if(comment == null || comment.getThisUser().getId().equals(userId)) {
			return "redirect:/main"; 
		}
		viewModel.addAttribute("comment", comment);
		viewModel.addAttribute("user_id", userId);
		return "commentview.jsp"; 
	}
	
	@PostMapping("edit/{id}")
	public String update(@Valid @ModelAttribute("comment") Comment comment, BindingResult result, @PathVariable("id")Long id, HttpSession session, Model viewModel) {
		if(result.hasErrors()) {
			Long userId = (Long)session.getAttribute("user_id");
			viewModel.addAttribute("comment", comment);
			viewModel.addAttribute("user_id", userId);
			return "commentview.jsp"; 
			
		}
		this.cServ.updateComment(comment);
		return "redirect:/"; 
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		this.cServ.deleteComment(id);
		return "redirect:/main";
		}
}
