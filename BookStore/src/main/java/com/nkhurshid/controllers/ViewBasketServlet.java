package com.nkhurshid.controllers;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import com.nkhurshid.models.Book;
import com.nkhurshid.models.ShoppingBasket;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet to display and allow edits to the basket
 * 
 * @author Nabeeha
 *
 */
@WebServlet("/ViewBasketServlet")
public class ViewBasketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ViewBasketServlet() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("view_basket.jsp").forward(request, response);
	}

	/**
	 * Checks request parameter to either decrease item quantity (or fully remove) or increase quantity
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("increase-quantity") != null) {
			
			int bookId = Integer.parseInt(request.getParameter("book-isbn"));
			ShoppingBasket basket = (ShoppingBasket) request.getSession().getAttribute("shopping-basket");
			Map<Book, Integer> basketBooks = basket.getBasket();
			for(Map.Entry<Book, Integer> book: basketBooks.entrySet()) { 
	  			if(book.getKey().getIsbn() == bookId) {
	  				basket.add(book.getKey());
	  			}
	  		}
			request.getSession().setAttribute("shopping-basket", basket);
		}
		
		if(request.getParameter("decrease-quantity") != null) {
			
			int bookId = Integer.parseInt(request.getParameter("book-isbn"));
			ShoppingBasket basket = (ShoppingBasket) request.getSession().getAttribute("shopping-basket");
			Map<Book, Integer> basketBooks = basket.getBasket();
			Iterator<Map.Entry<Book, Integer>> iterator = basketBooks.entrySet().iterator();
		    while (iterator.hasNext()) {
		        Map.Entry<Book, Integer> entry = iterator.next();
		        if(entry.getKey().getIsbn() == bookId) {
	  				if(entry.getValue() == 1) { 
	  					iterator.remove();
	  				} else {
	  					basket.remove(entry.getKey());
	  				}
	  			}
		    }	
			request.getSession().setAttribute("shopping-basket", basket);
			
		}
		
		doGet(request, response);
	}

}
