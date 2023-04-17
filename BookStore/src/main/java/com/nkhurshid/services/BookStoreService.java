package com.nkhurshid.services;

import java.util.List;
import java.util.Map;

import com.nkhurshid.models.Book;
import com.nkhurshid.models.ShoppingBasket;
import com.nkhurshid.models.User;
import com.nkhurshid.models.UserOrder;

/**
 * Interface class for BookStore service
 * 
 * @author Nabeeha
 *
 */
public interface BookStoreService {
	
    public List<Book> getAllBooks();
    public List<Book> getAllBooksByID(int isbn);
    public List<Book> getAllBooksByAuthor(String author);
    public Map<Integer, Book> getIsbnBookMap(List<Book> books);
    public int registerUser(String firstName, String lastName, String email, String password);
    public User loginUser(String email, String password);
    public User getUpdatedUser(User user);
    public List<UserOrder> getAllOrdersByUser(User user);
    public int updateUserFirstName(User user, String newFirstName);
    public int updateUserLastName(User user, String newLastName);
    public int updateUserPassword(User user, String newPassword);
    public int updateUserEmail(User user, String newEmail);
    public int checkOut(ShoppingBasket basket, User user);

}
