<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.nkhurshid.models.Book"%>
<%@ page import="com.nkhurshid.models.ShoppingBasket"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.HashMap"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Shopping Basket</title>
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
				<%
				if (request.getSession().getAttribute("user-id") != null) {
				%>
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
				<%
				} else {
				%>
				<li class="nav-item"><a class="nav-link" href="LoginServlet">Login</a>
				</li>
				<%
				}
				%>
			</ul>
		</div>
	</nav>

	<div class="d-flex flex-column justify-content-center w-100">
		<div class="d-flex flex-column justify-content-center align-items-center w-80 mt-4">
			<h1 class="display-3">Shopping Basket</h1>

			<%
			ShoppingBasket basket = (ShoppingBasket) request.getSession().getAttribute("shopping-basket");
			if (basket != null && basket.getBasket().size() != 0) {
				Map<Book, Integer> bookList = (HashMap<Book, Integer>) basket.getBasket();
				for (Map.Entry<Book, Integer> b : bookList.entrySet()) {
			%>
			<div style="display: flex; flex-direction: row; width: 80vw; margin: 10px; justify-content: space-between;">
				<div class="d-flex flex-column">
					<h5><%=b.getKey().getBookName()%></h5>
					<h6>by <%=b.getKey().getAuthorString()%></h6>
					<br>
				</div>
				<div class="d-flex align-items-center">
					<div>
						£<%=String.format("%.2f", b.getKey().getPrice())%>
					</div>
					<form method="POST" style="margin: 5px;">
						<input type="text" name="book-isbn"
							value="<%=b.getKey().getIsbn()%>" style="display: none">
						<input type="submit" class="btn btn-secondary" name="decrease-quantity" value="-">
					</form>
					QTY: <%=b.getValue()%>
					<form method="POST" style="margin: 5px;">
						<input type="text" name="book-isbn"
							value="<%=b.getKey().getIsbn()%>" style="display: none">
						<input type="submit" class="btn btn-secondary" name="increase-quantity" value="+">
					</form>
				</div>
			</div>
			<%
			}
			%>
			<div style="width: 80vw; text-align: right;">
				TOTAL: £<%=String.format("%.2f", basket.getTotalPrice())%>
			</div>

			<%
			if (request.getSession().getAttribute("user-id") != null) {
			%>
			<a class="btn btn-secondary mr-5" href="PaymentDetailsServlet" role="button">Proceed
				to Payment</a>
			<%
			} else {
			%>
			<a class="btn btn-secondary mr-5" href="LoginServlet" role="button">Login to
				Checkout</a>
			<%
			}
			} else {
			%>
			<p class="lead" style="text-align: center;">There is
				currently nothing in your basket.</p>
			<%
			}
			%>
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