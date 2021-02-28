package com.group.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.models.Comment;
import com.group.repositories.CommentRepository;

@Service
public class CommentService {
	@Autowired
	private CommentRepository cRepo;
	
	public List<Comment> getComments() {
		return this.cRepo.findAll();
	}
	
	public Comment findById(Long id) {
		return this.cRepo.findById(id).orElse(null);
	}
	
	public Comment create(Comment comment) {
		return this.cRepo.save(comment);
	}

	public Comment updateComment(Comment updatedComment) {
		return this.cRepo.save(updatedComment);
	}
	
	public void deleteComment(Long id) {
		this.cRepo.deleteById(id);
	}
}
