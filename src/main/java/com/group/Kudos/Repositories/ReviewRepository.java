package com.group.Kudos.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.group.Kudos.Models.Review;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long>{

	List<Review> findAll();

}
