<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
	crossorigin="anonymous">
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light px-2">
		<a class="navbar-brand" href="HomeServlet">Books.com</a>
		<div class="d-flex flex-row justify-content-end w-100"
			id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item"><a class="nav-link"
					href="ViewBasketServlet">View Basket</a></li>
				<li class="nav-item"><a class="nav-link" href="RegisterServlet">Register</a>
				</li>
			</ul>
		</div>
	</nav>
	<div class="d-flex flex-column justify-content-center w-100">
		<div class="d-flex flex-column justify-content-center align-items-center w-80 mt-4">
			<h1 class="display-3">Login</h1>
			<form method="POST"
				style="display: flex; justify-content: center; flex-direction: column;">
				<input type="text" name="email" placeholder="Enter email"
					style="margin: 5px;" required> <br> <input type="password"
					name="pass" placeholder="Enter password" style="margin: 5px;" required>
				<br> <button type="submit" class="btn btn-secondary my-2" name="login" value="Login"
					style="margin: 5px;">Login</button>
			</form>
			<c:if test="${requestScope.error != null}">
				<c:out value="${requestScope.error}" />
			</c:if>
			<c:if test="${requestScope.success != null}">
				<c:out value="${requestScope.success}" />
			</c:if>
			<a href="RegisterServlet"><small class="text-muted">Don't have an account? Click here.</small></a>
		</div>
	</div>

	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>
</body>
</html>