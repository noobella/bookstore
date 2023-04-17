<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order Confirmation</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
</head>
<body>
		<nav class="navbar navbar-expand-lg navbar-light bg-light px-2">
		<a class="navbar-brand" href="HomeServlet">Books.com</a>
		<div class="d-flex flex-row justify-content-end w-100"
			id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item"><a class="nav-link"
					href="ViewBasketServlet">View Basket</a></li>

				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" id="navbarDropdown" role="button"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						Account </a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdown">
						<a class="dropdown-item" href="AccountDetailsServlet">Account
							Details</a> <a class="dropdown-item" href="OrderHistoryServlet">Order
							History</a>
					</div></li>
				<li class="nav-item"><a class="nav-link" href="LogoutServlet">Logout</a>
				</li>
			</ul>
		</div>
	</nav>
	<div class="d-flex flex-column justify-content-center w-100">
		<div class="d-flex flex-column justify-content-center align-items-center w-80 mt-4">
			<h1 class="display-3"> Order #<%= request.getSession().getAttribute("order-id") %> </h1>
			<p class="lead" style="text-align: center;"> Your payment has been completed. 
			<br> You will receive your order by <%= request.getAttribute("delivery-date") %>. </p> 
			<br>
			<a class="btn btn-secondary mt-2" href="HomeServlet" style="text-decoration:none;">Return to Home</a>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>