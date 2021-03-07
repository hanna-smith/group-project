package com.group.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.models.Review;
import com.group.repositories.ReviewRepository;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository rRepo;
	
	
			//create a review
	public void createReview(Review newReview) {
		this.rRepo.save(newReview);
	}
	
	public Review findReviewById(Long id) {
		return this.rRepo.findById(id).orElse(null);
	}
	
			//delete a review
	public void deleteReview(Long id) {
		this.rRepo.deleteById(id);
	}

	public List<Review> getReviews() {
		return this.rRepo.findAll();
	}
	

}