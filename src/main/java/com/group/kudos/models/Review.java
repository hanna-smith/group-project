package com.group.kudos.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="reviews")
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private int stars;
	private String title;
	
	@Column
	@Size(max = 1000)
	private String content;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User reviewer;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="business_id")
	private Business business;
	
	@Column(updatable=false)  //have a date for when it was created, can't be updated
	@DateTimeFormat(pattern = "YYYY-MM-dd HH:mm:ss")
	private Date createdAt;
	
	@PrePersist	//creates before anything else touches the date
	protected void onCreate() {
		this.createdAt = new Date();
	}

	
	
			//Constructors
	
	public Review() {
		super();
	}
	public Review(Long id, int stars, String title, User reviewer, Business busReviewed, Date createdAt) {
		super();
		this.id = id;
		this.stars = stars;
		this.title = title;
		this.reviewer = reviewer;
		this.business = busReviewed;
		this.createdAt = createdAt;

	}
	
	
			//Getters and Setters
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getStars() {
		return stars;
	}
	public void setStars(int stars) {
		this.stars = stars;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public User getReviewer() {
		return reviewer;
	}
	public void setReviewer(User reviewer) {
		this.reviewer = reviewer;
	}
	public Business getBusiness() {
		return business;
	}
	public void setBusiness(Business busReviewed) {
		this.business = busReviewed;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}



	public void setBusReviewed(Object attribute) {
		// TODO Auto-generated method stub
		
	}

	
	
	
}
