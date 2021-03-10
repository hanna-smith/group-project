package com.group.kudos.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Email;

@Entity
@Table(name="businesses")
public class Business {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	// Identifier to match to Bing Entity Search API results. Is a 
	// concatenation of <name>|<postalCode>|<url>
	@Column(columnDefinition="LONGTEXT")
	private String bingId;
	
	private String type;
	private String websiteUrl;
	
	@Email
	@Column(unique=true)
	private String email;
	
	@Column(updatable=false)
	private Date createdAt;
	private Date updatedAt;
	
	@OneToMany(mappedBy="business", fetch=FetchType.LAZY)
	private List<Review> reviews;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User owner;
	
	@Column(columnDefinition="LONGTEXT")
	private String address;
	
	private String telephone;
	
	public Business() {}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Date getCreatedAt() {
		return createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	
	@PrePersist
	protected void beforeCreate() {
		this.createdAt = new Date();
	}
	@PreUpdate
	protected void beforeUpdate() {
		this.updatedAt = new Date();
	}
	
	public String getBingId() {
		return bingId;
	}
	public void setBingId(String id) {
		this.bingId = id;
	}
	public void setBingId(String name, String postalCode, String url) {
		this.bingId = name + "|" + postalCode + "|" + url;
	}
	
	public String getWebsiteUrl() {
		return websiteUrl;
	}
	public void setWebsiteUrl(String url) {
		this.websiteUrl = url;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
		public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
}
