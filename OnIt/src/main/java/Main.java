import java.sql.SQLException;

import connection.ConnectionUtil;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		ConnectionUtil connectionUtil = new ConnectionUtil();
		connectionUtil.getConnection();

	}

}
