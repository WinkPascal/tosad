package database.ToolDatabase;

import java.sql.*;
import oracle.jdbc.driver.*;
import oracle.sql.*;

public class OracleBaseDAO {

	protected static Connection conn;
	private static final String DB_DRIV = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_URL = "jdbc:oracle:thin:@//localhost:1521/XEPDB1";
	private static final String DB_USER = "ALMAR";
	private static final String DB_PASS = "admin";

	public Connection getConnection() {
		
	try {
		Class.forName(DB_DRIV).newInstance();
		System.out.println("yes");
	} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1) {
		e1.printStackTrace();
	}
	Connection myConn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS); 
	return myConn;
}

public static void closeConnection() throws SQLException {
	conn.close();
}
	}
}
