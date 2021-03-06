<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
		      	<form class="d-flex" method="GET" action="/search">
		      		<input class="form-control me-2 home-search search-term" name="searchTerm" placeholder="Search Term">
		      		<input class="form-control me-2 home-search" name="location" placeholder="Location">
		      		<button class="btn search-btn" type="submit">Search</button>
		      	</form>
		      </ul>
		      <div class="dropdown">
			      <ul class="nav navbar-nav navbar-right">
			      	<c:choose>
			      		<c:when test="${ user == null }">
			      			<a href="/login">LOG IN</a>
			      		</c:when>
			      		<c:otherwise>
					      	<a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-bs-toggle="dropdown" aria-expanded="false">${USER.NAME}</a>
					      	<ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
					      		<li><a class="dropdown-item" href="/profile">My Profile</a></li>
					      		<li><a class="dropdown-item" href="/logout">Log Out</a></li>
					      		<li><a class="dropdown-item" href="/mybusiness">My Business</a></li> <!-- IF STATEMENT -->
					      		<li><a class="dropdown-item" href="/myreviews">My Reviews</a></li>	 <!-- IF STATEMENT -->
					      	</ul>
				      	</c:otherwise>
			      	</c:choose>
			      </ul>
			  </div>
		    </div>
		  </div>
		</nav>
		<!--  PAGE CONTENT -->
		<div style="padding: 50px;">
			<h1 class="text-secondary">Searching for <span class="text-light">${ searchTerm }</span> in <span class="text-light">${ location }</span></h1>
			<div class="row row-cols-1 row-cols-md-3 g-4">
				<c:forEach items="${ businesses }" var="business">
					<div class="col">
						<div class="card text-light bg-dark mb-3">
							<div class="card-header">
								${ business.type }
							</div>
							<div class="card-body">
								<h5 class="card-title">${ business.name }</h5>
								<p class="card-text">${ business.address }</p>
								<a class="card-link" href="tel://${ business.telephone }">${ business.telephone }</a>
								<c:choose>
									<c:when test="${ business.id != null }">
										<a class="card-link" href="#">More Details</a>
									</c:when>
									<c:otherwise>
										<form:form style="display: inline-block;" method="POST" action="/business/new" modelAttribute="business">
											<form:input type="hidden" path="name" name="name" value="${ business.name }"/>
											<form:input type="hidden" path="bingId" name="bingId" value="${ business.bingId }"/>
											<form:input type="hidden" path="websiteUrl" name="websiteUrl" value="${ business.websiteUrl }"/>
											<form:input type="hidden" path="type" name="type" value="${ business.type }"/>
											<form:input type="hidden" path="address" name="address" value="${ business.address }"/>
											<form:input type="hidden" path="telephone" name="telephone" value="${ business.telephone }"/>
											<button class="btn btn-link card-link" type="submit">More Details</button>
										</form:form>
									</c:otherwise>
								</c:choose>
							</div>
							<div class="card-footer">
								<a class="card-link" href="${ business.websiteUrl }">Go to website</a>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
			
			<c:forEach items="${ businesses }" var="business">
				${ business.name } ${ business.bingId } ${ business.websiteUrl } ${ business.type }
			</c:forEach>
		</div>
	</body>
</html>