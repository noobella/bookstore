package com.nkhurshid.controllers;

import java.io.IOException;
import java.util.List;

import com.nkhurshid.daos.BookStoreDaoImpl;
import com.nkhurshid.models.User;
import com.nkhurshid.models.UserOrder;
import com.nkhurshid.services.BookStoreService;
import com.nkhurshid.services.BookStoreServiceImpl;
import com.nkhurshid.util.BookStoreFactory;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet for displaying user order history
 * 
 * @author Nabeeha
 *
 */
@WebServlet("/OrderHistoryServlet")
public class OrderHistoryServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    public OrderHistoryServlet() {}

	/**
	 * Obtains and passes order history list through request parameter
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BookStoreService bookStoreService = BookStoreFactory.getBookStoreService();
		List<UserOrder> userOrders = bookStoreService.getAllOrdersByUser((User) request.getSession().getAttribute("user-id"));
		request.setAttribute("user-orders", userOrders);
		request.getRequestDispatcher("order_history.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
