<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
    <%@ page isErrorPage="true" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Offer</title>
<link rel="stylesheet" type="text/css" href="/css/newOffer.css">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<h1><img src="/images/garagesale.png" alt="Garage Sale" style="width: 150px"></h1>
			<div class="links">
				<a href="/home">Home</a> 
				<a href="/logout">Log out</a>
			</div>
			<h3>Hello, <c:out value="${currentUser.firstName}"/>!</h3>
		<h3>Edit Your Offer of the <c:out value="${thisOffer.itemWithOffers.title}"/>:</h3>
		<form:form action="/offers/${thisOffer.id }/editOffer" method="POST" modelAttribute="thisOffer">
			<input type="hidden" name="_method" value="PUT"/>
			<p>
				<form:errors path="amount"/>
				<form:errors path="offerRemarks"/>
			</p>
			<p>
				<form:label path="amount">Offer Amount:</form:label>
				<form:input type="number" step=".01" path="amount"/>
			</p>
			<p>
				<form:label path="offerRemarks">Offer Remarks:</form:label>
				<form:textarea rows="4" path="offerRemarks"/>
			</p>
			<form:input type="hidden" path="offerer" value="${currentUser.id}"/>
			<form:input type="hidden" path="itemWithOffers" value="${thisOffer.itemWithOffers.id}"/>
			<button type="submit" class="btn btn-dark">+ Add Offer</button>
	</form:form>
	</div>
</body>
</html>