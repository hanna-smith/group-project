<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"><html lang="en">
	<head>
    <meta charset="UTF-8">
    <title>KUDOS</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
	<link href="/css/style.css" rel="stylesheet">
	<link rel="preconnect" href="https://fonts.gstatic.com">
	<link href="https://fonts.googleapis.com/css2?family=Oswald:wght@700&display=swap" rel="stylesheet">
	</head>
<body>
		<nav class="navbar navbar-expand-lg">
		  <div class="container-fluid">
		    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
		      <span class="navbar-toggler-icon"></span>
		    </button>
		    <div class="collapse navbar-collapse" id="navbarText">
		      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
		      	<form class="d-flex" method="GET" action="/search">
		      		<input class="form-control me-2 home-search search-term" name="searchTerm" placeholder="Search Term">
		      		<input class="form-control me-2 home-search" name="location" placeholder="Location">
		      		<button class="btn search-btn" type="submit">Search</button>
		      	</form>
		      </ul>
		     	<div class="dropdown">
			      <ul class="nav navbar-nav navbar-right">
			      	      <ul class="nav navbar-nav navbar-right">
			      	<c:choose>
			      		<c:when test="${pageContext.request.userPrincipal.name == null}">
			      			<a href="/login">LOG IN</a>
			      			<a style="margin-left: 10px;" href="/registration">REGISTER</a>
			      		</c:when>
			      		<c:otherwise>
					      	<a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-bs-toggle="dropdown" aria-expanded="false">${pageContext.request.userPrincipal.name}</a>
					      	<ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
					      	<form id="logoutForm" method="POST" action="/logout">
						        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						        <input class="login dropdown-item" type="submit" value="Logout" />
					      	</form>
							<sec:authorize url="/business/dashboard">
								<li><a class="dropdown-item" href="/business/dashboard">My Business Page</a></li>
							</sec:authorize>
							<sec:authorize url="/userDashboard">
								<li><a class="dropdown-item" href="/userDashboard">My Reviews</a></li>
							</sec:authorize>
					      	</ul>
				      	</c:otherwise>
			      	</c:choose>
			      </ul>
			 </ul>
	
			  </div>
		    </div>
		  </div>
		</nav>
	<div id="home-search">
			<img src="/img/header.jpg">
		<h1 style="font-size: 100px">${business.name}</h1>
		<div>
			<h3>${ business.type }</h3>
			<p class="text-light">${ business.address }</p>
			<a class="link-light" href="tel:/${ business.telephone }">${ business.telephone }</a>
			<c:if test="${ business.websiteUrl.length() > 0 }"><a class="link-light" href="${ business.websiteUrl }">Go to website</a></c:if>
		</div>
		<sec:authorize url="/business/">
			<c:if test="${ business.owner == null }"><a href="/busDetails/${ business.id }/claimBusiness" class="btn search-btn" style="margin-top: 20px; opacity: 75%;" role="button">Claim This Business</a></c:if>
		</sec:authorize>
		<sec:authorize url="/userDashboard">
		<c:choose><c:when test="${ pageContext.request.userPrincipal.name == null }"><a href="/login" class="btn search-btn" style="margin-top: 20px; opacity: 75%" role="button">Log in to leave a review!</a></c:when><c:otherwise><a href="#newReview" data-bs-toggle="collapse" class="btn search-btn" style="margin-top: 20px; opacity: 75%" role="button" aria-expanded="false" aria-controls="newReview">Write a Review!</a></c:otherwise></c:choose>
			<div class="card text-light mb-6 collapse" style="width: 40%; margin: auto; background-color: rgba(255, 255, 255, 0.2);" id="newReview">
				<form:form method="POST" action="/busDetails/${ business.id }/newReview" modelAttribute="review">
					<form:hidden value="${ business.id }" path="business"/>
					<form:hidden value="${ user.id }" path="reviewer"/>
					<div class="form-group mb-3">
						<form:errors path="title"/>
						<form:input class="form-control home-search" placeholder="Title your review" path="title"/>
					</div>
					<div class="form-group mb-3">
						<form:errors path="stars"/>
						<form:select class="form-select home-search" path="stars">
							<option value="4">⭐⭐⭐⭐</option>
							<option value="5">⭐⭐⭐⭐⭐</option>
						</form:select>
					</div>
					<div class="form-group mb-3">
						<form:errors path="content"/>
						<form:textarea path="content" class="form-control home-search" rows="6" placeholder="Tell us about your experience"></form:textarea>
					</div>
					<button type="submit" class="btn search-btn">Submit</button>
				</form:form>
			</div>
		</sec:authorize>	
  	<div class="row row-cols-1 row-cols-md-3 g-4" style="margin-top: 20px;">
	 <c:forEach items="${reviews}" var="review">
	 <div class="col">
	 	<div class="card text-light bg-dark mb-3">
	 		<div class="card-header">
	 			${review.reviewer.username} says:
	 		</div>
	 		<div class="card-body">
	 			<h5 class="card-title">${review.title}</h5>
	 			<p class="card-text">${review.content}</p>
	 			<h6><c:choose><c:when test="${ review.stars <= 4 }">⭐⭐⭐⭐</c:when><c:otherwise>⭐⭐⭐⭐⭐</c:otherwise></c:choose></h6>
	 		</div>
	 	</div>
	 </div>
	</c:forEach>
		</div>
	</div>
</body>
</html>
