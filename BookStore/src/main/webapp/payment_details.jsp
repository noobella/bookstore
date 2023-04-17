<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ page import="com.nkhurshid.models.User" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Payment Details</title>
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
			<h1 class="display-3"> Payment Details </h1>
			
			<% User user = (User) request.getSession().getAttribute("user-id"); %>
			<form method="POST" style="display:flex; justify-content:center; flex-direction:column;">
				Name on Card<input type="text" style="margin: 5px;"> <br>
				Card Number<input type="text"style="margin: 5px;"> <br>
				CVC<input type="password" style="margin: 5px;"> <br>
				Door Number<input type="text" name="door-no" value="<%= user.getDoorNo() %>" style="margin: 5px;"> <br>
				Street Name<input type="text" name="street-name" value="<%= user.getStreetName() %>" style="margin: 5px;"> <br>
				Postcode<input type="text" name="postcode" value="<%= user.getPostcode() %>" style="margin: 5px;"> <br>
				<button type="submit" class="btn btn-secondary mb-4" name="checkout" value="Make payment" style="margin: 5px;">Make payment</button>
			</form>
			<c:if test="${requestScope.error != null}">
			    <c:out value="${requestScope.error}" />
			</c:if>
		</div>
	</div>
	
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>