package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionUtil {


	private static final String URL = System.getenv("onitdb_url");
	private static final String USERNAME = System.getenv("onitdb_username");
	private static final String PASSWORD = System.getenv("onitdb_password");
	
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
