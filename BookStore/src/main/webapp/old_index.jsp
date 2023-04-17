<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 	<%@ page import="com.nkhurshid.models.Book" %>
 	<%@ page import="java.util.List" %>
 	<%@ page import="java.util.Map" %>
  	<%@ page import="java.util.HashMap" %>
  	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book Store Home</title>
</head>
<body>
	<nav style="display:flex; flex-direction:row; justify-content:space-between;">
		<a>Book Shop</a>
		<a href="CheckOutServlet">Basket</a>
		<% if(request.getSession().getAttribute("user-id") == null) { %>
		<a href="LoginServlet">Login</a>
		<% } else { %>
   		<a href="LogoutServlet">Logout</a> 	
		<% } %>
	</nav>
	<div style="width:40vw; display:flex; flex-direction:row; justify-content:space-between;">
		<form method="POST">
			<input type="text" name="book-search">
			<input type="submit" name="search" value="Search">
		</form>
	</div>
	<div>
		<table>	
			<tbody>
				<tr>
					<th scope="col"> Book Name </th>
					<th scope="col"> Book Author(s) </th>
					<th scope="col"> Description </th>
					<th scope="col"> Price </th>
					<th scope="col"> </th>			
				</tr>
				<% Map<Integer, Book> bookList;
					if(request.getAttribute("search-book-list") != null) {
						bookList =  (HashMap<Integer, Book>) request.getAttribute("search-book-list"); 
					} else {
						bookList =  (HashMap<Integer, Book>) request.getSession().getAttribute("book-list"); 
					}
					for(Map.Entry<Integer, Book> b  : bookList.entrySet()) {
				%>
				<tr>
					<td><%= b.getValue().getBookName() %></td>
					<td>Authors </td>
					<td><%= b.getValue().getOverview() %></td>
					<td><%= b.getValue().getPrice() %></td>
					<td>
						<form method="POST"> 
							<input type="text" name="isbn" value="<%= b.getValue().getIsbn()%>" style="display:none">
							<input type="submit" name="add-to-cart" value="Add to cart">
						</form>
					</td>
				</tr>
				<% } %>
			</tbody>
		</table>
	</div>
</body>
</html>