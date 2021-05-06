package com.revature.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {

	
		
		
	private static final String URL = "jdbc:postgresql://onit.csdlvyc8g6b3.us-west-2.rds.amazonaws.com/ers";
	private static final String USERNAME = "postgres";
	private static final String PASSWORD = "password123";
		
		
		
		public static Connection setUpConnection() 
		{
			
			try {
				Class.forName("org.postgresql.Driver");
				Connection conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
				
				System.out.println(conn);
				return conn;
			} catch (SQLException | ReflectiveOperationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			
			
		

		
	}

}

//
//endpoint
//endpoint: database-1.csd4vggmmxv0.us-east-2.rds.amazonaws.com
//username: postgres
//password: password
//Admin Connection^
//endpoint: database-1.csd4vggmmxv0.us-east-2.rds.amazonaws.com
//username: tktEmployee
//password: password
//Employee connection^ has read/write/update/delete access
