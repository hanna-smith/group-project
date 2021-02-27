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
	<form action="/new">
  		<div class="form-group">
    		<label for="comment">Add Comment:</label>
    		<input type="comment" class="form-control" placeholder="Type comments here" id="comment">
  		</div>
  		<button type="submit" class="btn btn-primary">Submit</button>
  		</form>
	<h6>Comments</h6>
	<c:forEach items="${reviewComments.comments}" var="comment">
		<p>${comment}</p>
		<c:choose>
			<c:when test="${ comment.commentCreator.id == user.id }">
				<a href="/comment/edit/${ comment.id }">Edit</a> |
				<form class="delete-form" action="/comment/delete/${ comment.id }" method="post">
					<input type="hidden" name="_method" value="delete" />
					<button>Delete</button>
				</form>
			</c:when>
		</c:choose>
	</c:forEach>
</div>
</body>
</html>