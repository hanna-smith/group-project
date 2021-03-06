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

import com.group.kudos.models.Comment;
import com.group.kudos.services.CommentService;
import com.group.kudos.services.ReviewService;
import com.group.kudos.services.UserService;


@Controller
@RequestMapping("/review/{id}/comments")
public class CommentController {
	@Autowired
	private CommentService cServ;
	
	@Autowired
	private UserService uServ; 
	
	@Autowired
	private ReviewService rServ; 
	
	
		
	@GetMapping("")
	public String reviewComments(@PathVariable("id")Long id, HttpSession session, Model viewModel) {
		Long userId = (Long)session.getAttribute("user_id");
		viewModel.addAttribute("comments", this.cServ.getComments());
		viewModel.addAttribute("user", this.uServ.findUserById(userId));
		viewModel.addAttribute("review", this.rServ.findReviewById(id));
		
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
		if(comment == null || comment.getCommentCreator().getId().equals(userId)) {
			return "redirect:/main"; 
		}
		
		viewModel.addAttribute("comment", comment);
		viewModel.addAttribute("user_id", userId);
		return "commentview.jsp"; 
	}
	
	@PostMapping("/comment/edit/{id}")
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
	
	@GetMapping("/comment/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		this.cServ.deleteComment(id);
		return "redirect:/";
		}
}