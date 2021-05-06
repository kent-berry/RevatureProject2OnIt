package com.revature.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionUtil {


	private static final String URL = "jdbc:postgresql://onit.csdlvyc8g6b3.us-west-2.rds.amazonaws.com/ers";
	private static final String USERNAME = "postgres";
	private static final String PASSWORD = "password123";
	
	private static Connection connection = null;
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			System.out.println("established connection  successfully");
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Issues with establishing a connection.");
			e.printStackTrace();
		} 
		return connection;
	}
	
	
}
