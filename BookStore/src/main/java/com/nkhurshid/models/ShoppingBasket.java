package com.nkhurshid.models;

import java.util.HashMap;
import java.util.Map;

/**
 * Class to represent shopping basket
 * 
 * @author Nabeeha
 *
 */
public class ShoppingBasket {
	
	private Map<Book, Integer> basket;
	
	public ShoppingBasket() {
		basket = new HashMap<Book, Integer>();
	}
	   
	public Map<Book, Integer> getBasket() {
		return basket;
	}
	
	/**
	 * Adds book to basket- if book already exists, will increase its quantity by one
	 * 
	 * @param b
	 */
	public void add(Book b) {
		
		if(basket.containsKey(b)) {
			int quantity = basket.get(b);
			basket.put(b, quantity + 1);
		} else {
			basket.put(b, 1);
		}
	
	}
	
	/**
	 * Decreases the quantity of a book by one
	 * 
	 * @param b
	 */
	public void remove(Book b) {
		
		if(basket.containsKey(b)) {
			int quantity = basket.get(b);
				basket.put(b, quantity - 1);
			}
	
	}
	
		
	/**
	 * Calculates total price of books in basket
	 * 
	 * @return total price of basket
	 */
	public double getTotalPrice() {   
		double total = 0.0;
		Book currentBook;
		for(Map.Entry<Book, Integer> book: basket.entrySet()) {
			currentBook = book.getKey();
			total += currentBook.getPrice() * book.getValue();
		}
		
		return total;
	}
	
	/**
	 * Calculates size of basket
	 * 
	 * @return size of basket
	 */
	public int getSize() {
		int size = 0;
		for(Map.Entry<Book, Integer> book: basket.entrySet()) {
			size += book.getValue();
		}
		return size;
	}
	
}
