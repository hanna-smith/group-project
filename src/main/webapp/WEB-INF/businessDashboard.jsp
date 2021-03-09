<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
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
		<div id="home-search">
			<img src="/img/header.jpg">
			<h1>${user.username}'s Business Account</h1>

			<div id="search-bar">
				<form class="d-flex" method="GET" action="/search">
					<input class="form-control me-2 home-search search-term" name="searchTerm" placeholder="Search and claim your business">
					<h2 style="color: #66FCF1; margin-left: 0px; margin-right: 7px;">IN</h2>
					<input class="form-control me-2 home-search" name="location" placeholder="Location">
					<button class="btn search-btn" type="submit">Search</button>
				</form>
			</div>
		<div class="businesses" style="color:white">
			<c:forEach items="${user.businesses}" var="business">
				<h2>${business.name}</h2>
				<h3>${business.address}</h3>
						<table class="table">
							<thead class="table-dark">
								<tr>
									<th>Title</th>
									<th>Stars</th>
									<th>Kudos</th>
									<th>Date Posted</th>
								</tr>
							</thead>
				<c:forEach items="${business.reviews}" var="review">
							<tbody>											
									<tr>																
										<td>${review.title}</td>																	
										<td><strong>${review.stars} stars</strong></td>						
										<td>${review.createdAt}</td>
										<td>${review.content}</td>
									</tr>
							</tbody>
				</c:forEach>
						</table>
				
					
			</c:forEach>
			</div>
			
		</div>
	</body>

</html>
