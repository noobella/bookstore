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

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    public RegisterServlet() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("register.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BookStoreService bookStoreService = BookStoreFactory.getBookStoreService();
		
		if(request.getParameter("register") != null) {
			
			String firstName = (String) request.getParameter("first-name");
			String lastName = (String) request.getParameter("last-name");
			String email = (String) request.getParameter("email");
			String pass = (String) request.getParameter("pass");

			int success = bookStoreService.registerUser(firstName, lastName, email, pass);
			if(success > 0) {
				request.setAttribute("success", "Registration success, please login in to access account");
				request.getRequestDispatcher("/LoginServlet").forward(request, response);
			} else {
				System.out.println("user not identified");
				request.setAttribute("error", "There was an error trying to register, please try again later");
				doGet(request, response);
			}
		}
		
	}

}
