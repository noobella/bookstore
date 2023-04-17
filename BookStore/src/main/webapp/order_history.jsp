<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.nkhurshid.models.Book"%>
<%@ page import="com.nkhurshid.models.UserOrder"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.LinkedList"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.HashMap"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order History</title>
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
	<div class="d-flex flex-column justify-content-center align-items-center w-100 mt-4">
		<div class="text-center">
			<h1 class="display-3">Order History</h1>
		</div>
		<div style="width: 60vw;">
			<%
			if (request.getAttribute("user-orders") != null) {
				List<UserOrder> userOrders = (LinkedList<UserOrder>) request.getAttribute("user-orders");
				for (UserOrder order : userOrders) {
			%>
			<div
				style="display: flex; flex-direction: row; margin: 15px; justify-content: space-between; align-items:center;">
				<div>
					<small>Order number #<%=order.getOrderID()%></small> 
					<br>
					<%=order.getOrderDate()%>
				</div>
				<div
					style="width: 30vw; display: flex; flex-direction: row; justify-content: space-between;">
					<div>
						<%
						List<Book> books = (LinkedList<Book>) order.getBooks();
						for (Book book : books) {
						%>
						<%=book.getBookName()%>
						<br>
						<%
						}
						%>
					</div>
					<div>
						£<%=String.format("%.2f", order.getTotalPrice())%>
						<br>
					</div>
				</div>
			</div>
			<%
			}
			} else {
			%>
			<h1 class="display-6" style="text-align: center;">You have not
				made any orders.</h1>
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