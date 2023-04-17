package com.nkhurshid.controllers;

import java.io.IOException;

import com.nkhurshid.daos.BookStoreDaoImpl;
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
 * Servlet to let user log into account
 * 
 * @author Nabeeha
 *
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    public LoginServlet() {}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	/**
	 * Gets user details and verifies if account exists - if exists, logs user in else an error message is set
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BookStoreService bookStoreService = BookStoreFactory.getBookStoreService();
		
		if(request.getParameter("login") != null) {
			String email = (String) request.getParameter("email");
			String pass = (String) request.getParameter("pass");
			User user = bookStoreService.loginUser(email, pass);
			if(user != null) {
				System.out.println("user identified");
				HttpSession session = request.getSession(true);
				session.setAttribute("user-id", user);
				response.sendRedirect("/BookStore/HomeServlet");
			} else {
				System.out.println("user not identified");
				request.setAttribute("error", "The email or password you entered was incorrect, please try again");
				doGet(request, response);
			}
		}
		
		// doGet(request, response);
	}

}
