package database.TargetDatabase;

import java.sql.*;
import oracle.jdbc.driver.*;
import oracle.sql.*;

public class OracleBaseDAO {

	protected static Connection conn;
	private static final String DB_DRIV = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_URL = "jdbc:oracle:thin:@//ondora04.hu.nl:8521/EDUC22";
	private static final String DB_USER = "TARGET";
	private static final String DB_PASS = "TARGET";

	public Connection getConnection() {
		
	try {
		Class.forName(DB_DRIV).newInstance();
		Connection myConn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
		return myConn;
	} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1) {
		e1.printStackTrace();
		return null;
	} catch (SQLException e) {
		e.printStackTrace();
		return null;
	}
	}

	public static void closeConnection() throws SQLException {
		conn.close();
	}
}

