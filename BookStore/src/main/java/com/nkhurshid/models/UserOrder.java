package com.nkhurshid.models;

import java.sql.Timestamp;
import java.util.List;

/**
 * Class to represent order of a {@link User}
 * 
 * @author Nabeeha
 *
 */
public class UserOrder {
	
    private int orderID, userId;
    private Timestamp orderDate;
    private double totalPrice;
    private List<Book> books;
	
    public UserOrder(int orderID, Timestamp orderDate, double totalPrice, List<Book> books) {
		this.orderID = orderID;
		this.orderDate = orderDate;
		this.totalPrice = totalPrice;
		this.books = books;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Timestamp getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
}
