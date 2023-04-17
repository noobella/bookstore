package com.nkhurshid.controllers;

import java.io.IOException;

import com.nkhurshid.daos.BookStoreDaoImpl;
import com.nkhurshid.models.ShoppingBasket;
import com.nkhurshid.models.User;
import com.nkhurshid.services.BookStoreService;
import com.nkhurshid.services.BookStoreServiceImpl;
import com.nkhurshid.util.BookStoreFactory;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet to allow user to enter payment details for check out
 * 
 * @author Nabeeha
 *
 */
@WebServlet("/PaymentDetailsServlet")
public class PaymentDetailsServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    public PaymentDetailsServlet() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("payment_details.jsp").forward(request, response);
	}
	
	
	/**
	 * Checks request parameter and will allow user to check out their basket
	 * If unable to checkout out, error message is set to user
	 * 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BookStoreService bookStoreService = BookStoreFactory.getBookStoreService();
		
		if(request.getParameter("checkout") != null) { 
			HttpSession session = request.getSession();
			int orderId = bookStoreService.checkOut((ShoppingBasket) session.getAttribute("shopping-basket"), 
					(User) session.getAttribute("user-id"));
			
			System.out.println("order id: " + orderId);
			if(orderId !=  -1) {
				session.setAttribute("order-id", orderId);
				response.sendRedirect("/BookStore/OrderConfirmationServlet");
			} else {
				request.setAttribute("error", "There was an error trying to make payment, please try again later");
				doGet(request, response);
			}
		}
		
	}
}
