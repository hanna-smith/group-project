<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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
	
																					<!--NAVIGATION BAR -->
	
	<nav class="navbar navbar-expand-lg">
		<div class="container-fluid">
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarText"
				aria-controls="navbarText" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarText">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<form:form class="d-flex" method="POST" action="/search">
						<input class="form-control me-2 home-search search-term"
							name="searchTerm" placeholder="Search Term"> <input
							class="form-control me-2 home-search" name="location"
							placeholder="Location">
						<button class="btn search-btn" type="submit">Search</button>
					</form:form>
				</ul>
				<div class="dropdown">
					<ul class="nav navbar-nav navbar-right">
						<a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-bs-toggle="dropdown" aria-expanded="false">${user.username}</a>
						<ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
							<li><a class="dropdown-item" href="/">Search</a></li>
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
					</ul>
				</div>
			</div>
		</div>
	</nav>		
	<div id="home-search">

	<h1>Your KUDOS Profile</h1>
	<div class="container">
		<h3>${user.username}'s KUDOS Posted</h3>
		<table class="table table-dark">
			<thead class="table-dark">
				<tr>
					<th>Business</th>
					<th>Title</th>
					<th>Stars</th>
					<th>Date Posted</th>
					<th>Edit</th>
			</thead>
			<tbody>
				<c:forEach items="${user.reviews}" var="review">												<!-- Collapsible table to conserve space -->
					<tr data-toggle="collapse" data-target="#accordion"	class="clickable">			<!-- Header row displays always  -->
						<td><a class="link-light" href="/busDetails/${ review.business.id }">${ review.business.name }</a></td>																	<!-- First row of data displays, is clickable -->
						<td>${ review.title }</td>																	<!-- Review content is hidden -->
						<td class="mt-5"><c:choose><c:when test="${ review.stars <= 4 }">⭐⭐⭐⭐</c:when><c:otherwise>⭐⭐⭐⭐⭐</c:otherwise></c:choose></td>						<!-- Picture data is hidden -->
						<td><fmt:formatDate value="${ review.createdAt }" pattern="MMM dd, yyyy"/></td>
						<td><a class="link-secondary" href="/review/${ review.id }/edit">Edit</a> <a class="link-secondary" href="/delete/${ review.id }">Delete</a>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	</div>
</body>
</html>