package com.nkhurshid.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.nkhurshid.models.Author;
import com.nkhurshid.models.Book;
import com.nkhurshid.models.ShoppingBasket;
import com.nkhurshid.models.User;
import com.nkhurshid.models.UserOrder;
import com.nkhurshid.util.DatabaseConnector;

/**
 * Implementation class for database interaction
 * 
 * @author Nabeeha
 *
 */
public class BookStoreDaoImpl implements BookStoreDao {
	
	private List<Book> books;
	private List<Author> authors;
    private Connection con = DatabaseConnector.getDatabaseConnection();;
    private PreparedStatement pst;
    private ResultSet rs;

	/**
	 * Returns all {@link Book} stored in database
	 * @return list of {@link Book}
	 *
	 */
	@Override
	public List<Book> getAllBooks() {
		
		books = new LinkedList<Book>();
		
		try {
			
			PreparedStatement bookPst = con.prepareStatement("SELECT * FROM book");
			ResultSet bookRs = bookPst.executeQuery();
            
            while(bookRs.next()) {
                int book_isbn = bookRs.getInt("book_isbn");
                Book book = new Book(book_isbn, bookRs.getString("book_name"), bookRs.getString("overview"), bookRs.getDouble("price"));
                
                pst = con.prepareStatement("SELECT * FROM book_author JOIN author "
                		+ "ON author.author_id = book_author.author_id "
                		+ "WHERE book_author.book_isbn = ?");
                pst.setInt(1, book_isbn);
                rs = pst.executeQuery();
                
                authors = new LinkedList<>();
                while(rs.next()) {
                	authors.add(new Author(rs.getInt("author_id"), rs.getString("first_name"), 
                			rs.getString("last_name")));
                }
                book.setAuthor(authors);
                books.add(book);
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return books;
	}

	/**
	 * Returns all {@link Book} stored in database based on given {@link Book#getIsbn()}
	 * 
	 * @param isbn unique book identifier
	 * @return list of {@link Book}
	 *
	 */
	@Override
	public List<Book> getAllBooksByID(int isbn) {
	
		books = new LinkedList<Book>();
		try {
            
            PreparedStatement bookPst = con.prepareStatement("SELECT * FROM book WHERE book_isbn = ?");
            bookPst.setInt(1, isbn);
            ResultSet bookRs = bookPst.executeQuery();
            
            while(bookRs.next()) {
            	int book_isbn = bookRs.getInt("book_isbn");
                Book book = new Book(book_isbn, bookRs.getString("book_name"), bookRs.getString("overview"), bookRs.getDouble("price"));
                
                pst = con.prepareStatement("SELECT * FROM book_author JOIN author "
                		+ "ON author.author_id = book_author.author_id "
                		+ "WHERE book_author.book_isbn = ?");
                pst.setInt(1, book_isbn);
                rs = pst.executeQuery();
                
                authors = new LinkedList<>();
                while(rs.next()) {
                	authors.add(new Author(rs.getInt("author_id"), rs.getString("first_name"), 
                			rs.getString("last_name")));
                }
                book.setAuthor(authors);
                books.add(book);
                
            }
            
        }
        catch(SQLException e){
            System.out.println(e);
        }
		
        return books;
	}
	
	/**
	 * Returns all {@link Book} stored in database based on given {@link Author} name
	 * 
	 * @param author
	 * @return list of {@link Book}
	 *
	 */
	@Override
	public List<Book> getAllBooksByAuthor(String author) {
		
		books = new LinkedList<Book>();
		try {
            
			String[] splitAuthor = author.split(" ");
            pst = con.prepareStatement("SELECT * FROM book b "
            		+ "INNER JOIN book_author ba "
            		+ "ON b.book_isbn = ba.book_isbn "
            		+ "INNER JOIN author a "
            		+ "ON ba.author_id = a.author_id "
            		+ "WHERE a.first_name LIKE ? OR a.last_name LIKE ?");
            pst.setString(1, "%" + splitAuthor[0] + "%");
            pst.setString(2, "%" + splitAuthor[0] + "%");
            rs = pst.executeQuery();
            
            while(rs.next()) {
            	int book_isbn = rs.getInt("book_isbn");
                Book book = new Book(book_isbn, rs.getString("book_name"), rs.getString("overview"), rs.getDouble("price"));
                
                pst = con.prepareStatement("SELECT * FROM book_author JOIN author "
                		+ "ON author.author_id = book_author.author_id "
                		+ "WHERE book_author.book_isbn = ?");
                pst.setInt(1, book_isbn);
                rs = pst.executeQuery();
                
                authors = new LinkedList<>();
                while(rs.next()) {
                	authors.add(new Author(rs.getInt("author_id"), rs.getString("first_name"), 
                			rs.getString("last_name")));
                }
                book.setAuthor(authors);
                books.add(book);
                
            }
            
        }
        catch(SQLException e){
            System.out.println(e);
        }
		
        return books;
		
	}

	/**
	 * Create a new {@link User} in the database
	 * 
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param password
	 * 
	 * @return number of rows affected in database
	 *
	 */
	@Override
	public int registerUser(String firstName, String lastName, String email, String password) {
		
		int i = 0;
        try {
            
            pst = con.prepareStatement("INSERT INTO user (first_name, last_name, email, password) VALUES (?, ?, ?, ?)");
            
            pst.setString(1, firstName);
            pst.setString(2, lastName);
            pst.setString(3, email);
            pst.setString(4, password);
           
            i = pst.executeUpdate();
            
        }
        catch(SQLException e){
            System.out.println(e);
        }
        
        return i;
	}

	/**
	 * Verifies that {@link User} exists in the database and returns an instance of user
	 * 
	 * @param firstName
	 * @param lastName
	 * 
	 * @return instance of logged-in {@link User}
	 *
	 */
	@Override
	public User loginUser(String email, String password) {
		
		User u = null;
        try {
            
            pst = con.prepareStatement("SELECT * FROM user WHERE email = ? AND password = ?");
            pst.setString(1, email);
            pst.setString(2, password);
            
            rs = pst.executeQuery();
            if(rs.next()) { 
                u = new User(rs.getInt("user_id"), rs.getString("first_name"), rs.getString("last_name"),
                		rs.getInt("door_number"), rs.getString("street_name"), rs.getString("postcode"),
                		rs.getString("email"));
                
            } 
        }
        catch(SQLException ex){
            System.out.println(ex);
        }
        return u;
	}
	
	/**
	 * Updates instance of {@link User} by retrieving user information for database
	 * 
	 * @param user
	 * @return updated {@link User} instance
	 *
	 */
	@Override
	public User getUpdatedUser(User user) {
        try {
            
            pst = con.prepareStatement("SELECT * FROM user WHERE user_id = ?");
            pst.setInt(1, user.getUserId());
            
            rs = pst.executeQuery();
            if(rs.next()) {
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
            } 
        }
        catch(SQLException ex){
            System.out.println(ex);
        }
        return user;
	}

	/**
	 * Retrieves full order history of a chosen {@link User}
	 * 
	 * @param user
	 * @return list of {@link UserOrder}
	 *
	 */
	@Override
	public List<UserOrder> getAllOrdersByUser(User user) {
		
		List<UserOrder> userOrders = new LinkedList<>();
		PreparedStatement pst2;
		ResultSet rs2;
        try {
            pst = con.prepareStatement("SELECT user_order_id, order_date, total_price FROM user_order WHERE user_id = ?");
            pst.setInt(1, user.getUserId());
            
            rs = pst.executeQuery();
            while(rs.next()) {
            	int userOrderId = rs.getInt("user_order_id");
            	pst2 = con.prepareStatement("SELECT * FROM book_order INNER JOIN book ON book.book_isbn = book_order.book_isbn WHERE user_order_id = ?");
            	pst2.setInt(1, userOrderId);
            	rs2 = pst2.executeQuery();
        		List<Book> books = new LinkedList<>();
            	while(rs2.next()) {
            		books.add(new Book(rs2.getInt("book_isbn"), rs2.getString("book_name"), rs2.getString("overview"), rs2.getDouble("price")));
            	}
                UserOrder order = new UserOrder(userOrderId, rs.getTimestamp("order_date"), rs.getDouble("total_price"), books);
                userOrders.add(order); 
            }   
        }
        catch(SQLException e){
            System.out.println(e);
        }

        return userOrders;
	}

	/**
	 * Updates first name of a chosen {@link User}
	 * 
	 * @param firstName
	 * @return number of rows affected in database
	 *
	 */
	@Override
	public int updateUserFirstName(User user, String newFirstName) {
		try {
			pst = con.prepareStatement("UPDATE user SET first_name = ? WHERE user_id = ?");
	        pst.setString(1, newFirstName);
	        pst.setInt(2, user.getUserId());
	        return pst.executeUpdate();
	    } catch(SQLException e) {
	        System.out.println(e);
	        return 0;
	    }
	}

	/**
	 * Updates last name of a chosen {@link User}
	 * 
	 * @param lastName
	 * @return number of rows affected in database
	 *
	 */
	@Override
	public int updateUserLastName(User user, String newLastName) {
		try {
			pst = con.prepareStatement("UPDATE user SET last_name = ? WHERE user_id = ?");
	        pst.setString(1, newLastName);
	        pst.setInt(2, user.getUserId());
	        return pst.executeUpdate();
	    } catch(SQLException e) {
	        System.out.println(e);
	        return 0;
	    }
	}

	/**
	 * Updates password of a chosen {@link User}
	 * 
	 * @param password
	 * @return number of rows affected in database
	 *
	 */
	@Override
	public int updateUserPassword(User user, String newPassword) {
		try {
			pst = con.prepareStatement("UPDATE user SET password = ? WHERE user_id = ?");
	        pst.setString(1, newPassword);
	        pst.setInt(2, user.getUserId());
	        return pst.executeUpdate();
	    } catch(SQLException e) {
	        System.out.println(e);
	        return 0;
	    }
	}

	/**
	 * Updates email of a chosen {@link User}
	 * 
	 * @param email
	 * @return number of rows affected in database
	 *
	 */
	@Override
	public int updateUserEmail(User user, String newEmail) {
		try {
			pst = con.prepareStatement("UPDATE user SET email = ? WHERE user_id = ?");
	        pst.setString(1, newEmail);
	        pst.setInt(2, user.getUserId());
	        return pst.executeUpdate();
	    } catch(SQLException e) {
	        System.out.println(e); 
	        return 0;
	    }
	}

	/**
	 * Stores {@link ShoppingBasket} under chosen {@link User} account as an {@link UserOrder}
	 * 
	 * @param user
	 * @param basket
	 * @return number of rows affected in database
	 *
	 */
	@Override
	public int checkOut(ShoppingBasket basket, User user) {
		
		int userOrderId = 0;
		int update = 0;
		try{
			pst = con.prepareStatement("INSERT INTO user_order(user_id, total_price) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, user.getUserId());
            pst.setDouble(2, basket.getTotalPrice());
            update = pst.executeUpdate();
            rs = pst.getGeneratedKeys();
            
            if(rs.next()) {
            	userOrderId = rs.getInt(1);
	            Map<Book, Integer> bookMap = basket.getBasket();
	            for(Map.Entry<Book, Integer> entry: bookMap.entrySet()) {
	            	pst = con.prepareStatement("INSERT INTO book_order(book_isbn, user_order_id, quantity) VALUES (?, ?, ?)");
	                pst.setInt(1, entry.getKey().getIsbn());
	                pst.setInt(2, userOrderId);
	                pst.setInt(3, entry.getValue());
	                update = pst.executeUpdate();
	            }
            }
            
        } catch(SQLException e){
            System.out.println(e);
        
        }
		return (update == 0) ? -1 : userOrderId;
	}

}
