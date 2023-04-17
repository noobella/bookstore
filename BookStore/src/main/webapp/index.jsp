<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 	<%@ page import="com.nkhurshid.models.Book" %>
 	<%@ page import="java.util.List" %>
 	<%@ page import="java.util.Map" %>
  	<%@ page import="java.util.HashMap" %>
  	<%@ page import="com.nkhurshid.models.ShoppingBasket"%>
  	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book Store Home</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light px-2">
	 	<a class="navbar-brand" href="HomeServlet">Books.com</a>
		<div class="d-flex flex-row justify-content-end w-100" id="navbarSupportedContent">
		    <ul class="navbar-nav mr-auto">
	        <li class="nav-item">
	        	<% ShoppingBasket basket = (ShoppingBasket) request.getSession().getAttribute("shopping-basket");
				if (basket != null) { %>
	        		<a class="nav-link" href="ViewBasketServlet">View Basket <small>(<%= basket.getSize() %>)</small></a>
	        	<% } %>
	        </li>
	      <% if(request.getSession().getAttribute("user-id") != null) { %>
		     <li class="nav-item dropdown">
		     	<a class="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			   	 Account
		         </a>
			     <div class="dropdown-menu" aria-labelledby="navbarDropdown">
				      <a class="dropdown-item" href="AccountDetailsServlet">Account Details</a>
				      <a class="dropdown-item" href="OrderHistoryServlet">Order History</a>
			     </div>
			 </li>
			 <li class="nav-item">
	             <a class="nav-link" href="LogoutServlet">Logout</a>
	      	 </li>
			<% } else { %>
			 <li class="nav-item">
	       		<a class="nav-link" href="LoginServlet">Login</a>
	      	 </li>
			<% } %>	      
	    </ul>
	  </div>
	</nav>
	
	<form class="form-inline d-flex flex-row justify-content-center mt-4" method="POST"> 
	  	<input type="text" class="form-control mb-2 mr-sm-2 w-50" name="book-isbn" id="search-form" placeholder="Enter a Book ISBN or Author Name" required>
		<button type="submit" class="btn btn-secondary mb-2 mx-2" name="search" value="Search">Search</button>
	</form>
	<div style="display:flex; flex-direction:column; justify-content:center; align-items:center; width:100vw;">
		<% Map<Integer, Book> bookList;
		if(request.getAttribute("search-book-list") != null) {
			bookList =  (HashMap<Integer, Book>) request.getAttribute("search-book-list"); 
		} else {
			bookList =  (HashMap<Integer, Book>) request.getSession().getAttribute("book-list"); 
		}
		if(bookList.size() == 0) { %>
			<p class="lead mt-4" style="text-align: center;">Nothing could be found for: <%= request.getParameter("book-isbn") %></p>
			
		<% }
		for(Map.Entry<Integer, Book> b  : bookList.entrySet()) {
		%>
		
			<div style="display:flex; flex-direction:row; width:80vw; margin:10px; justify-content: space-between;">
				<div style="width:65vw">
					<h5><%= b.getValue().getBookName() %>
					<h6>by <%= b.getValue().getAuthorString() %></h6>
					<small class="text-muted"><i><%= b.getValue().getOverview() %></i></small><br>
					£<%= String.format("%.2f", b.getValue().getPrice()) %>
					<p><small>ISBN: <%= b.getValue().getIsbn() %></small></p>
					</h5>
				</div>
				<div class="d-flex align-items-center">
					<form method="POST"> 
						<input type="text" name="isbn" value="<%= b.getValue().getIsbn()%>" style="display:none">
						<button type="submit" class="btn btn-secondary mb-2 mx-2" name="add-to-cart" value="Add to basket">Add to basket</button>
					</form>
				</div>
			</div>
		<% } %>	
	</div>
	
	<script src="index.js"></script>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>