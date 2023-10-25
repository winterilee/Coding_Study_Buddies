<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
     <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Item</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="/css/itemView.css">
<style>
		body {
			background-image:url(/images/stuff.jpg); 
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
			<a href="http://localhost:8080/offers/${item.id}/new_offer">+ Add an Offer</a>
			<a href="http://localhost:8080/logout">Logout</a>
		</div>
		<div class="itemInfo">
			<h3><c:out value="${item.user.firstName}"/> is trying to sell</h3>
			<h3> a <c:out value="${item.title}"/>!</h3>
			<p>Asking price: $<fmt:formatNumber type="number" minFractionDigits="2" value="${item.price}"/></p>
			<p>Description:</p>
			<p><c:out value="${item.description}"/></p>
		</div>
		<h2>Offers:</h2>
		<c:forEach var="thisOffer" items="${item.allOffers}">
			<div class=offer>
			<p>Added By: <c:out value="${ thisOffer.offerer.firstName}"/> <c:out value="${ thisOffer.offerer.lastName}"/></p>
			<p>Amount: $<fmt:formatNumber type="number" minFractionDigits="2" value="${thisOffer.amount}"/></p>
			<p>Offer Remarks: <c:out value="${thisOffer.offerRemarks}"/></p>
			
			<c:if test="${currentUser.id == thisOffer.offerer.id}">
			<div class="actions">
				<form action="/offers/${thisOffer.id}/edit_offer" method="get">
					<input type="hidden" value="Edit"/>
					<button type="submit" class="btn btn-dark">Edit</button>
				</form>
				<form action="/offers/${thisOffer.id}/deleteOffer" method="post">
			      	<input type="hidden" name="_method" value="delete" />
			      	<button type="submit" class="btn btn-dark">Delete</button>
			    </form>
			</div>
		</c:if>
		<c:if test="${currentUser.id == item.user.id}">
			<div class="accept">
				<form:form action="/garagesale/${item.id}/edit/process" method="post" modelAttribute="item">
				 <input type="hidden" name="_method" value="put">
	
						<form:input type="hidden" path="title"/>
					
						<form:input type="hidden" step="0.01" path="price"/>

						<form:input type="hidden" path="status" value="true" label="yes"/>
					
						<form:input type="hidden" rows="4" path="description"/>
	
				    <form:input type="hidden" path="user" value="${user.id}"/>
				    <form:input type="hidden" path="id" value="${item.id}"/>    
				    <button type="submit" class="btn btn-danger">Accept Offer</button>
				</form:form>
			</div>
		</c:if> 
		</div>
		</c:forEach>
	</div>
</body>
</html>