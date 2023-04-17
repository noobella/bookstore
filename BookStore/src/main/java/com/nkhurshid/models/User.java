package com.nkhurshid.models;

/**
 * Class to represent registered users of BookStore
 * 
 * @author Nabeeha
 *
 */
public class User {

	private int userId, doorNo;
    private String firstName, lastName, streetName, postcode, email, password;
    
    public User(int userId, String firstName, String lastName, String email) {
    	
    }
    
    public User(int userId, String firstName, String lastName, 
    		int doorNo, String streetName, String postcode, String email) {
        this.userId = userId;
    	this.firstName = firstName;
        this.lastName = lastName;
        this.doorNo = doorNo;
        this.streetName = streetName;
        this.postcode = postcode;
        this.email = email;
    }

    public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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
    

	public int getDoorNo() {
		return doorNo;
	}

	public void setDoorNo(int doorNo) {
		this.doorNo = doorNo;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getEmail() {
        return email;
    }
	
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getPassword() {
    	return password;
    }
	
}
