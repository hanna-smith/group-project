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
	<nav class="navbar navbar-expand-sm bg-light navbar-light">
	  <ul class="navbar-nav ml-auto">
	  	<li class="container p-3 bg-dark text-white">
	  		<p>Welcome "user.name"</p>
	    <li class="nav-item active">
	      <a class="nav-link" href="/main">Home</a>
	    </li>
	    <li class="nav-item active">
	      <a class="nav-link" href="/logout">Log Out</a>
	    </li>
	  </ul>
	</nav>
	<h3><a href="/business/${business.id}">${business.name}</a></h3>
	<h5>Review</h5>
	<p>${business.review}</p>
	<h6>Comments</h6>
	<c:forEach items="${review.comment}" var="comment">
		<p>${comment}</p>
	</c:forEach>
</div>
</body>
</html>