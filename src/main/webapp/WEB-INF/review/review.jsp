<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome to the Comments view page</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
</head>
<body>
<div class="container">
		<nav class="navbar navbar-expand-lg">
		  <div class="container-fluid">
		    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
		      <span class="navbar-toggler-icon"></span>
		    </button>
		    <div class="collapse navbar-collapse" id="navbarText">
		      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
<%-- 		      	<form class="d-flex" method="GET" action="/search">
		      		<input class="form-control me-2 home-search search-term" name="searchTerm" placeholder="Search Term">
		      		<input class="form-control me-2 home-search" name="location" placeholder="Location">
		      		<button class="btn search-btn" type="submit">Search</button>
		      	</form> --%>
		      </ul>
		     	<div class="dropdown">
					<ul class="nav navbar-nav navbar-right">
						<a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-bs-toggle="dropdown" aria-expanded="false">${user.username}</a>
						<ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
						
							<li><a class="dropdown-item" href="/">Search</a></li>
							<li><a class="dropdown-item" href="/login?logout">Log Out</a></li>
							
							<sec:authorize url="/business/dashboard">
								<li><a class="dropdown-item" href="/business/dashboard">My Business Page</a></li>
							</sec:authorize>
							<sec:authorize url="/userDashboard">
								<li><a class="dropdown-item" href="/userDashboard">My Reviews</a></li>
							</sec:authorize>
						</ul>
					</ul>
				</div>
		    </div>
		  </div>
		</nav>
	<h3>${business.name}</h3>
	<h6>${business.type}</h6>
	<h6>${business.websiteUrl}</h6>
	<h5>Reviews</h5>
	<p>${business.review}</p>
	<form action="/new">
  		<div class="form-group">
    		<label for="review">Add Review:</label>
    		<input type="review" class="form-control" placeholder="Type your review here" id="review">
  		</div>
  		<button type="submit" class="btn btn-primary">Submit</button>
  	</form>
	<c:forEach items="${business.reviews}" var="review">
		<p>${review}</p>
		<c:choose>
			<c:when test="${ review.reviewer.id == user.id }">
				<a href="/review/edit/${ review.id }">Edit</a> |
				<form class="delete-form" action="/review/delete/${ review.id }" method="post">
					<input type="hidden" name="_method" value="delete" />
					<button>Delete</button>
				</form>
			</c:when>
		</c:choose>
	</c:forEach>
</div>
</body>
</html>