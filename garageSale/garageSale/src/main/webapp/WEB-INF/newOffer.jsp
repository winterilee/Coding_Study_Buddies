<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ page isErrorPage="true" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>New Offer</title>
<link rel="stylesheet" type="text/css" href="/css/newOffer.css">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<h1>
			<img src="/images/garagesale.png" alt="Garage Sale" style="width: 150px">
		</h1>
		<div class="links">
			<a href="/home">Home</a> 
			<a href="/logout">Log out</a>
		</div>
		<h3>Hello, <c:out value="${currentUser.firstName}"/>!</h3>
		<h3>Let's Offer on the <c:out value="${itemToOffer.title}"/>:</h3>
		<form:form action="/offers/addOffer" method="POST" modelAttribute="newOffer">
			<p>
				<form:errors style="color: red" path="amount"/>
				<form:errors style="color: red" path="offerRemarks"/>
			</p>
			<p>
				<form:input type="number" step=".01" path="amount" placeholder="Offer Amount"/>
			</p>
			<p>
				<form:textarea rows="4" path="offerRemarks" placeholder="Offer Remarks"/>
			</p>
			<form:input type="hidden" path="offerer" value="${currentUser.id}"/>
			<form:input type="hidden" path="itemWithOffers" value="${itemToOffer.id}"/>
			<button type="submit" class="btn btn-dark">+ Add Offer</button>
		</form:form>
	</div>
</body>
</html>