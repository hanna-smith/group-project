<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="author" content="Jessica LaPlante">
<title>KUDOS</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0"
	crossorigin="anonymous"></script>
<link href="src/main/resources/static/css/style.css" rel="stylesheet">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Oswald:wght@700&display=swap"
	rel="stylesheet">
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
						<a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-bs-toggle="dropdown" aria-expanded="false">${USER.NAME}</a>
						<ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
							<li><a class="dropdown-item" href="/profile">My Profile</a></li>
							<li><a class="dropdown-item" href="/logout">Log Out</a></li>
							<li><a class="dropdown-item" href="/mybusiness">My
									Business</a></li>
							<!-- IF STATEMENT -->
							<li><a class="dropdown-item" href="/myreviews">My
									Reviews</a></li>
							<!-- IF STATEMENT -->
						</ul>
					</ul>
				</div>
			</div>
		</div>
	</nav>		
	<div id="home-search">
		<h1>KUDOS</h1>
	</div>
	
																			<!-- PAGE CONTENT -->

	<h1>KUDOS Profile</h1>
	<div class="container">

		<h2>${user.username}KUDOS Posted</h2>

																			<!-- Display User Reviews -->

		<table class="table">
			<thead class="table-dark">
				<tr>
					<th>Business</th>
					<th>Title</th>
					<th>Stars</th>
					<th>Kudos</th>
					<th>Pictures</th>
					<th>Date Posted</th>
			</thead>

			<tbody>
				<c:forEach items="${reviews}" var="review">												<!-- Collapsible table to conserve space -->
					<tr data-toggle="collapse" data-target="#accordion"	class="clickable">			<!-- Header row displays always  -->
						<td>Business Name</td>																	<!-- First row of data displays, is clickable -->
						<td>Kudos Title Here</td>																	<!-- Review content is hidden -->
						<td class="mt-5"><strong>${review.stars}</strong></td>						<!-- Picture data is hidden -->
						<td>Date</td>
					</tr>
					<tr id="accordion" class="collapse">
						<td colspan="2">${review.content}</td>
						<td colspan="2">display picture thumbnails</td>

					</tr>

				</c:forEach>

			</tbody>

		</table>




	</div>

</body>
</html>