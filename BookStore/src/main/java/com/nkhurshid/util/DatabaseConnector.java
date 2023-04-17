package com.nkhurshid.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class to obtain database connector 
 * 
 * @author Nabeeha
 *
 */
public class DatabaseConnector {
	
	private static Connection con;
	
	private DatabaseConnector() {}
		
	/**
	 * Returns static instance of database connector
	 * 
	 * @return database connector
	 */
	public static Connection getDatabaseConnection() {
		
		try
		{	
			Class.forName("com.mysql.jdbc.Driver");
			if(con == null) {
				con = DriverManager.getConnection("jdbc:mysql://localhost/bookstore","root","Welikemartinez1!");
			}
		} catch(ClassNotFoundException e){
				System.out.println(e);
		} catch(SQLException e){
	            System.out.println(e);
		}
		return con;
	}

}
