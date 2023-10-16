<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
    <%@ page isErrorPage="true" %>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Item</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
	<h1>Garage Sale</h1>
		<div class="links">
			<a href="http://localhost:8080/home">Home</a>
			<a href="http://localhost:8080/logout">Logout</a>
		</div>
		<div class="form">
			<h3>What Would You Like To Change?</h3>
				<form:form action="/garagesale/${item.id}/edit/process" method="post" modelAttribute="item">
				 <input type="hidden" name="_method" value="put">
					<div style="color: red;"><form:errors path="title"/></div>
					<div style="color: red;"><form:errors path="price"/></div>
					<div style="color: red;"><form:errors path="status"/></div>
					<div style="color: red;"><form:errors path="description"/></div>
					<p>
						<form:input path="title" placeholder="Item Title"/>
					</p>
					<p>
						<form:input type="number" step="0.01" path="price"/>
					</p>
					<p>
						<form:label path="status">Item Sold?</form:label>
						<form:radiobutton path="status" value="true" label="yes"/>
						<form:radiobutton path="status" value="false" label="no" checked="true"/>
					<p>
						<form:textarea rows="4" path="description"/>
					</p>
				    <form:input type="hidden" path="user" value="${user.id}"/>
				    <form:input type="hidden" path="id" value="${item.id}"/>    
				    <button type="submit" class="btn btn-dark">Create</button>
				</form:form>
			</div>
		</div>
</body>
</html>