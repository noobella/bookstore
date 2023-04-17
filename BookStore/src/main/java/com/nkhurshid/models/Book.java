package com.nkhurshid.models;

import java.util.List;

/**
 * Class to represent books
 * 
 * @author Nabeeha
 *
 */
public class Book {
	
	private int isbn;
    private String bookName, overview;
    private double price;
    private List<Author> authors;
    
    public Book(int isbn, String bookName, String overview, double price) {
        this.isbn = isbn;
    	this.price = price;
        this.bookName = bookName;
        this.overview = overview;
    }
    
    public Book(int isbn, String bookName, List<Author> authors, String overview, double price) {
        this.isbn = isbn;
    	this.price = price;
        this.bookName = bookName;
        this.authors = authors;
        this.overview = overview;
    }

	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public List<Author> getAuthor() {
		return authors;
	}

	public void setAuthor(List<Author>  authors) {
		this.authors = authors;
	}
	
	public String getAuthorString() {
		StringBuilder sb = new StringBuilder();
		for(Author a: this.authors) {
			if(authors.indexOf(a) == authors.size() - 1) {
				sb.append(a.getFirstName())
				.append(" ")
				.append(a.getLastName());
			} else {
				sb.append(a.getFirstName())
				.append(" ")
				.append(a.getLastName())
				.append(", ");
			}
		}
		return sb.toString();
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
