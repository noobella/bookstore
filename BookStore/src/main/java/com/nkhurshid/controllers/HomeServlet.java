package com.nkhurshid.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.nkhurshid.daos.BookStoreDaoImpl;
import com.nkhurshid.models.Book;
import com.nkhurshid.models.ShoppingBasket;
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
 * Servlet for Home page that displays all books and has a search ability
 * 
 * @author Nabeeha
 *
 */
@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private BookStoreService bookStoreService = BookStoreFactory.getBookStoreService();

    public HomeServlet() {
        super(); 
    }

	/**
	 * Sets session variable for list of books and empty shopping basket
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		
		if(session.getAttribute("book-list") == null) {
			Map<Integer, Book> bookList = bookStoreService.getIsbnBookMap(bookStoreService.getAllBooks());
			session.setAttribute("book-list", bookList);
		}
		
		if(session.getAttribute("shopping-basket") == null) {
			ShoppingBasket basket = new ShoppingBasket();
			session.setAttribute("shopping-basket", basket);
		}
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
	}


	/**
	 * Checks request parameter and will either search for a book or add an item to basket
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("search") != null) {
			
			Map<Integer, Book> searchBookList;
			try {
				int bookIsbn = Integer.parseInt(request.getParameter("book-isbn"));
				searchBookList = (HashMap<Integer, Book>) bookStoreService.getIsbnBookMap(bookStoreService.getAllBooksByID(bookIsbn));
				
			} catch (NumberFormatException e) {
				String authorName = request.getParameter("book-isbn");
				searchBookList = (HashMap<Integer, Book>) bookStoreService.getIsbnBookMap(bookStoreService.getAllBooksByAuthor(authorName));
			}
			
			request.setAttribute("search-book-list", searchBookList);
		}
		
		if(request.getParameter("add-to-cart") != null) {
			int bookId = Integer.parseInt(request.getParameter("isbn"));
			Map<Integer, Book> bookList = (HashMap<Integer, Book>) request.getSession().getAttribute("book-list");
			Book book = bookList.get(bookId);
			ShoppingBasket basket = (ShoppingBasket) request.getSession().getAttribute("shopping-basket");
			basket.add(book);
			request.getSession().setAttribute("shopping-basket", basket);
		}
		
		doGet(request, response);
		
	}

}
