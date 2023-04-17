package com.nkhurshid.models;

/**
 * Class to represent book authors
 * 
 * @author Nabeeha
 *
 */
public class Author {
	
	private int authorId;
	private String firstName, lastName;
	
	public Author(int authorId, String firstName, String lastName) {
		super();
		this.authorId = authorId;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public int getAuthorId() {
		return authorId;
	}
	
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
