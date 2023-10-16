<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>

	<div class="container">
		<div class="banner">
			<div class="title">
				<h1>Garage Sale</h1>
				<h3>Welcome, <c:out value="${currentUser.firstName}"/>!</h3>
				<h4>Current Items For Sale:</h4>
			</div>
			<div class="links">
				<a href="http://localhost:8080/garagesale/new">+ Add a Item</a> |
				<a href="http://localhost:8080/logout">Logout</a>
			</div>
		</div>
		<div class="tables">
			<table class="table table-striped">
			  <thead>
			    <tr>
			      <th scope="col">Item</th>
			      <th scope="col">Asking Price</th>
			      <th scope="col">Posted By</th>
			      <th scope="col">Actions</th>
			    </tr>
			  </thead>
			  <tbody>
			  	<c:forEach var="item" items="${items}">
			  		<c:if test="${item.status == false }">
					    <tr>
					      <td><c:out value="${item.title}"/></td>
					      <td>$<fmt:formatNumber type="number" minFractionDigits="2" value="${item.price}"/></td>
					      <td><c:out value="${item.user.firstName}"/></td>
					      <td>
					      	<div class="actions">
						      	<a href="http://localhost:8080/garagesale/${item.id}">View</a>
						      	<c:if test="${currentUser.id == item.user.id }">
						      		<a href="/garagesale/${item.id}/edit">Edit</a>
						      		<div class="delete">
						      		<form action="/${item.id}/delete" method="POST">
						      			<input type="hidden" name="_method" value="Delete"/>
						      			<button>Delete</button>
						      		</form>
						      		</div>
						      	</c:if>
					      	</div>
					    </tr>
				    </c:if>
				</c:forEach>
			  </tbody>
			</table>
			<h4>Sold Items:</h4>
			<div class="table">
			<table class="table table-striped">
			  <thead>
			    <tr>
			      <th scope="col">Item</th>
			      <th scope="col">Asking Price</th>
			      <th scope="col">Posted By</th>
			      <th scope="col">Actions</th>
			    </tr>
			  </thead>
			  <tbody>
			  	<c:forEach var="item" items="${items}">
			  		<c:if test="${item.status == true }">
					    <tr>
					      <td><c:out value="${item.title}"/></td>
					      <td>$<fmt:formatNumber type="number" minFractionDigits="2" value="${item.price}"/></td>
					      <td><c:out value="${item.user.firstName}"/></td>
					      <td>
					      	<div class="actions">
						      	<a href="http://localhost:8080/garagesale/${item.id}">View</a>
						      	<c:if test="${currentUser.id == item.user.id }">
						      		<a href="/garagesale/${item.id}/edit">Edit</a>
						      		<div class="delete">
						      		<form action="/${item.id}/delete" method="POST">
						      			<input type="hidden" name="_method" value="Delete"/>
						      			<button>Delete</button>
						      		</form>
						      		</div>
						      	</c:if>
					      	</div>
					    </tr>
				    </c:if>
				</c:forEach>
			</tbody>
		</table>
		</div>
	</div>
	</div>
</body>
</html>