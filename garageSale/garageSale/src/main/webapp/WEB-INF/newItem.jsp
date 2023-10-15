<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ page isErrorPage="true" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<h1>Garage Sale</h1>
		<div class="links">
			<a href="http://localhost:8080/garagesale">Home</a>
			<a href="http://localhost:8080/logout">Logout</a>
		</div>
		<div class="form">
			<h3>What Would You Like To Sell Today?</h3>
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
				<button type="submit" class="btn btn-dark">Create</button>
			</form:form>
		</div>
	</div>
</body>
</html>