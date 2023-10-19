<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ page isErrorPage="true" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Item</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="/css/addItem.css">
	<style>
		body {
			background-image:url(/images/sale.jpg); 
    		background-repeat: no-repeat; 
   		 	background-attachment: fixed;  
    		background-size: 100% 120%; 
		}
	</style>
</head>
<body>
	<div class="container">
		<h1><img src="/images/garagesale.png" alt="Garage Sale" style="width: 150px"></h1>
		<div class="links">
			<a href="http://localhost:8080/home">Home</a>
			<a href="http://localhost:8080/logout">Logout</a>
		</div>
		<div class="form">
			<h3>What Would You Like To </h3>
			<h3>Sell Today?</h3>
			<form:form action="/garagesale/new/process" method="post" modelAttribute="item">
				<div style="color: red"><form:errors path="title"/></div>
				<div style="color: red"><form:errors path="price"/></div>
				<div style="color: red"><form:errors path="status"/></div>
				<div style="color: red"><form:errors path="description"/></div>
				<p>
					<form:input path="title" placeholder="Item Title"/>
				</p>
				<p>
					<form:input type="number" step="0.01" path="price" placeholder="Asking Price"/>
				</p>
				<p>
					<form:label path="status">Item Sold?</form:label>
					<form:radiobutton path="isBidAccepted" value="true" label="yes"/>
					<form:radiobutton path="isBidAccepted" value="false" label="no" checked="true"/>
				<p>
					
					<form:textarea rows="4" path="description" placeholder="Description"/>
				</p>
				<p>
					<form:input type="hidden" path="user" value="${user.id}"/>
				</p>
				<button type="submit" class="btn btn-light">+ Create</button>
			</form:form>
		</div>
	</div>
</body>
</html>