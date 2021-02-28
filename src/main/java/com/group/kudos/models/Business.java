package com.group.kudos.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
@Table(name="businesses")
public class Business {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@Size(min=4, message="Username must be at least 4 characters.")
	@Column(unique=true)
	private String username;
	
	// Identifier to match to Bing Entity Search API results. Is a 
	// concatenation of <name>|<postalCode>|<url>
	@Column(columnDefinition="LONGTEXT")
	private String bingId;
	
	private String type;
	private String websiteUrl;
	
	@Email
	@Column(unique=true)
	private String email;
	
	private String password;
	@Transient
	private String passwordConfirmation;
	
	@Column(updatable=false)
	private Date createdAt;
	private Date updatedAt;
	
//	@OneToMany(mappedBy="review", fetch=FetchType.LAZY)
//	private List<Review> reviews;
	
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
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}
	public void setPasswordConfirmation(String password) {
		this.passwordConfirmation = password;
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
}
