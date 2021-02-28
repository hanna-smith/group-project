package com.group.Kudos.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.Kudos.Models.Review;
import com.group.Kudos.Repositories.ReviewRepository;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository rRepo;
	
	
			//create a review
	public void createReview(Review newReview) {
		this.rRepo.save(newReview);
	}
	
			//delete a review
	public void deleteReview(Long id) {
		this.rRepo.deleteById(id);
	}
	

}
