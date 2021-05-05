import java.sql.SQLException;

import connection.ConnectionUtil;


// This main is for testing purposes, this is a WAR and no main class is needed at the end
public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		ConnectionUtil connectionUtil = new ConnectionUtil();
		connectionUtil.getConnection();

	}

}
