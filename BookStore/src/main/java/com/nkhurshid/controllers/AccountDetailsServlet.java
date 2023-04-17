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

@WebServlet("/AccountDetailsServlet")
public class AccountDetailsServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    public AccountDetailsServlet() {}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// User user = (User) request.getSession().getAttribute("user-id");
		request.getRequestDispatcher("account_details.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BookStoreService bookStoreService = BookStoreFactory.getBookStoreService();
		
		if(request.getParameter("update") != null) {
			String firstName = (String) request.getParameter("first-name");
			String lastName = (String) request.getParameter("last-name");
			String email = (String) request.getParameter("email");
			String pass = (String) request.getParameter("pass");
			System.out.println("lname " + lastName);
			User user = (User) request.getSession().getAttribute("user-id");
			int updateSuccess;
			boolean error = false;
			if(!(user.getFirstName().equals(firstName))) {
				updateSuccess = bookStoreService.updateUserFirstName(user, firstName);
				if(updateSuccess == 0) {
					error = true;
				}
			}
			
			updateSuccess = bookStoreService.updateUserLastName(user, lastName);
			if(!(user.getLastName().equals(lastName))) {
				if(updateSuccess == 0) {
						error = true;
				}
			}
			
			if(!(user.getEmail().equals(email))) {
				updateSuccess = bookStoreService.updateUserEmail(user, email);
				if(updateSuccess == 0) {
					error = true;
				}
			}
			if(!(pass.equals(""))) {
				updateSuccess = bookStoreService.updateUserPassword(user, pass);
				if(updateSuccess == 0) {
					error = true;
				}
			}
			System.out.println("error: " + error);
			if(error) {
				request.setAttribute("error", "There was an error trying to update, please try again later");
			} else {
				request.setAttribute("success", "Update was successful");
			}
			
			request.getSession().setAttribute("user-id", bookStoreService.getUpdatedUser(user));
			
		}
		doGet(request, response);
	}

}
