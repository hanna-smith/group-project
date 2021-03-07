<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
    <meta charset="UTF-8">
    <title>Register</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
	<link href="/css/style.css" rel="stylesheet">
	<link href="/css/login.css" rel="stylesheet">
	<link rel="preconnect" href="https://fonts.gstatic.com">
	<link href="https://fonts.googleapis.com/css2?family=Oswald:wght@700&display=swap" rel="stylesheet">
	<script src="https://code.jquery.com/jquery-3.6.0.slim.min.js" integrity="sha256-u7e5khyithlIdTpu22PHhENmPcRdFiHRjhAuHcs05RI=" crossorigin="anonymous"></script>
	<script>
		$(function() {
			$('select').removeAttr('multiple');
			});
	</script>
	</head>
    <body>
    	<nav class="navbar navbar-expand-lg">
		  	<div class="container-fluid">
		    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
		      <span class="navbar-toggler-icon"></span>
		    </button>
		    <div class="collapse navbar-collapse" id="navbarText">
		      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
		      	<form class="d-flex" method="POST" action="/search">
		      		<input class="form-control me-2 home-search search-term" name="searchTerm" placeholder="Search Term">
		      		<input class="form-control me-2 home-search" name="location" placeholder="Location">
		      		<button class="btn search-btn" type="submit">Search</button>
		      	</form>
		      </ul>
		      <div class="dropdown">
			      <ul class="nav navbar-nav navbar-right">
			         	<c:choose>
			      		<c:when test="${pageContext.request.userPrincipal.name == null}">
			      			<a href="/login">LOG IN</a>
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
			  </div>
		    </div>
		  </div>
		</nav>
    	<div id="home-search">
			<img src="/img/header.jpg">
			<h1>Register</h1>
		
    <form:form style="width: 20%; margin: auto; margin-top: 30px;" method="POST" action="/registration" modelAttribute="user">
        <div class="form-group row justify-content-center">
        	<span style="color: #45A29E; margin-bottom: 5px;">Register As:</span>
        	<form:errors path="roles"/>
        	<form:select class="form-select home-search" path="roles">
        		<form:option value="1">Client</form:option>
        		<form:option value="2">Business</form:option>
        	</form:select>
        </div>
        <br>
        <div class="form-group row justify-content-center">
            <form:errors path="username"/>
            <form:input placeholder="Username" class="form-control home-search" type="text" id="username" name="username" path="username"/>
        </div>
        <br>
          <div class="form-group row justify-content-center">
            <form:errors path="email"/>
            <form:input placeholder="Email Address" class="form-control home-search" type="text" id="email" name="email" path="email"/>
        </div>
        <br>
        <div class="form-group row justify-content-center">
            <form:errors path="password"/>
            <form:password placeholder="Password" class="form-control home-search" id="password" name="password" path="password"/>
        </div>
        <br>
        <div class="form-group row justify-content-center">
            <form:errors path="confirmPassword"/>
            <form:password placeholder="Confirm Password" class="form-control home-search" id="confirmPassword" name="confirmPassword" path="confirmPassword"/>
        </div>
        <br>
        <input type="submit" value="Register"/>
    </form:form>
    </div>
</body>
</html>
