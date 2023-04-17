package com.nkhurshid.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nkhurshid.daos.BookStoreDao;
import com.nkhurshid.models.Book;
import com.nkhurshid.models.ShoppingBasket;
import com.nkhurshid.models.User;
import com.nkhurshid.models.UserOrder;

/**
 * Implementation class for BookStore service
 * 
 * @author Nabeeha
 *
 */
public class BookStoreServiceImpl implements BookStoreService {
	
	private BookStoreDao bookStoreDao;
	
	public BookStoreServiceImpl(BookStoreDao bookStoreDao) {
		this.bookStoreDao = bookStoreDao;
	}

	@Override
	public List<Book> getAllBooks() {
		return bookStoreDao.getAllBooks();
	}

	@Override
	public List<Book> getAllBooksByID(int isbn) {
		return bookStoreDao.getAllBooksByID(isbn);
	}
	
	@Override 
	public List<Book> getAllBooksByAuthor(String author) {
		return bookStoreDao.getAllBooksByAuthor(author);
	}
	
	@Override
	public Map<Integer, Book> getIsbnBookMap(List<Book> books) {
		Map<Integer, Book> map = new HashMap<>();
		for(Book book: books) {
			map.put(book.getIsbn(), book);
		}
		return map;
	}

	@Override
	 public int registerUser(String firstName, String lastName, String email, String password) {
		return bookStoreDao.registerUser(firstName, lastName, email, password);
	}

	@Override
	public User loginUser(String email, String password) {
		return bookStoreDao.loginUser(email, password);
	}
	
	@Override
	public User getUpdatedUser(User user) {
		return bookStoreDao.getUpdatedUser(user);
	}

	@Override
	public List<UserOrder> getAllOrdersByUser(User user) {
		return bookStoreDao.getAllOrdersByUser(user);
	}

	@Override
	public int updateUserFirstName(User user, String newFirstName) {
		return bookStoreDao.updateUserFirstName(user, newFirstName);
	}

	@Override
	public int updateUserLastName(User user, String newLastName) {
		return bookStoreDao.updateUserLastName(user, newLastName);
	}

	@Override
	public int updateUserPassword(User user, String newPassword) {
		return bookStoreDao.updateUserPassword(user, newPassword);
	}

	@Override
	public int updateUserEmail(User user, String newEmail) {
		return bookStoreDao.updateUserEmail(user, newEmail);
	}

	@Override
	public int checkOut(ShoppingBasket basket, User user) {
		return bookStoreDao.checkOut(basket, user);
	}

}
