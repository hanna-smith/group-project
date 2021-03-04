<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

<title>Registration Page</title>
</head>
<body>
    <h1>Register</h1>
    
<div class="custom-control custom-radio custom-control-inline">
  <input type="radio" id="customRadioInline1" name="customRadioInline1" class="custom-control-input"checked>
  <label class="custom-control-label" for="customRadioInline1">Register as Client</label>
  </div>
<div class="custom-control custom-radio custom-control-inline">
  <input type="radio" id="customRadioInline2" name="customRadioInline1" class="custom-control-input">
  <label class="custom-control-label" for="customRadioInline2">Register as Business</label>
</div>

    <form:form method="POST" action="/registration" modelAttribute="user">
     <h3>Register as a Client</h3>
        <div class="form-group">
            <form:label path="username"/>Username:
            <form:errors path="username"/>
            <form:input path="username"/>
        </div>
          <div class="form-group">
            <form:label path="email"/>Email:
            <form:errors path="email"/>
            <form:input path="email"/>
        </div>
        <div class="form-group">
            <form:label path="password"/>Password:
            <form:errors path="password"/>
            <form:password path="password"/>
        </div>
        <div class="form-group">
            <form:label path="confirmPassword"/>Password Confirmation:
            <form:errors path="confirmPassword"/>
            <form:password path="confirmPassword"/>
        </div>
        <input type="submit" value="Register as Client"/>
    </form:form>
    
    <form:form method="POST" action="/businessRegistration" modelAttribute="user">
        <h3>Register as a Business</h3>
        <div class="form-group">
            <form:label path="username"/>Username:
            <form:errors path="username"/>
            <form:input path="username"/>
        </div>
          <div class="form-group">
            <form:label path="email"/>Email:
            <form:errors path="email"/>
            <form:input path="email"/>
        </div>
        <div class="form-group">
            <form:label path="password"/>Password:
            <form:errors path="password"/>
            <form:password path="password"/>
        </div>
        <div class="form-group">
            <form:label path="confirmPassword"/>Password Confirmation:
            <form:errors path="confirmPassword"/>
            <form:password path="confirmPassword"/>
        </div>
        <input type="submit" value="Register as Business"/>
    </form:form>
</body>
</html>