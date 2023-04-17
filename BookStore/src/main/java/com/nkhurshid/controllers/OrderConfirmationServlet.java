package com.nkhurshid.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet confirms that order has been placed
 * 
 * @author Nabeeha
 *
 */
@WebServlet("/OrderConfirmationServlet")
public class OrderConfirmationServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    public OrderConfirmationServlet() {}

	/**
	 * Gets delivery date of order, resets basket and passes through request parameter
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		LocalDate localDate = LocalDate.now();
		localDate = localDate.plusDays(3);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String formattedDate = localDate.format(formatter);
		request.setAttribute("delivery-date", formattedDate);
		request.getSession().removeAttribute("shopping-basket");
		System.out.println("In order confirm");
		request.getRequestDispatcher("order_confirmation.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

}
