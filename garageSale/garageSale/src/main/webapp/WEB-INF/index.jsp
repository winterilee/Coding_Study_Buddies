<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Garage Sale Login and Registration</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css"/>
</head>
<body>
	<div class="container d-flex flex-column mt-3">
		<h1>Welcome to Garage Sale!</h1>
		<hr />
		<div class="d-flex flex-row mt-3 justify-content-around">
			<div class="d-flex flex-column">
				<h3>Register</h3>
				<form:form action="/register" method="post" modelAttribute="newUser">
					<div>
						<form:errors path="firstName" class="text-danger"/>
						<p class="d-flex flex-row justify-content-between align-items-center">
							<form:label path="firstName">First Name:</form:label>
							<form:input type="text" path="firstName"/>
						</p>
					</div>
					<div>
						<form:errors path="lastName" class="text-danger"/>
						<p class="d-flex flex-row justify-content-between align-items-center">
							<form:label path="lastName">Last Name:</form:label>
							<form:input type="text" path="lastName"/>
						</p>
					</div>
					<div>
						<form:errors path="email" class="text-danger"/>
						<p class="d-flex flex-row justify-content-between align-items-center">
							<form:label path="email">Email:</form:label>
							<form:input type="email" path="email"/>
						</p>
					</div>
					<div>
						<form:errors path="password" class="text-danger"/>
						<p class="d-flex flex-row justify-content-between align-items-center">
							<form:label path="password">Password:</form:label>
							<form:input type="password" path="password"/>
						</p>
					</div>
					<div>
						<form:errors path="confirm" class="text-danger"/>
						<p class="d-flex flex-row justify-content-between align-items-center">
							<form:label path="confirm">Confirm Password:</form:label>
							<form:input type="password" path="confirm"/>
						</p>
					</div>
					<button type="submit" class="btn btn-dark">Submit</button>
				</form:form>
			</div>
			<div class="d-flex flex-column">
				<h3>Login</h3>
				<form:form action="/login" method="post" modelAttribute="loginUser">
					<div>
						<form:errors path="email" class="text-danger"/>
						<p class="d-flex flex-row justify-content-between align-items-center">
							<form:label path="email">Email:</form:label>
							<form:input type="email" path="email"/>
						</p>
					</div>
					<div>
						<form:errors path="password" class="text-danger"/>
						<p class="d-flex flex-row justify-content-between align-items-center">
							<form:label path="password">Password:</form:label>
							<form:input type="password" path="password"/>
						</p>
					</div>
					<button type="submit" class="btn btn-dark">Submit</button>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>