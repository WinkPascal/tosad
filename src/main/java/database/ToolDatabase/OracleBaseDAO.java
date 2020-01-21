package database.ToolDatabase;

import java.sql.*;
import oracle.jdbc.driver.*;
import oracle.sql.*;

public class OracleBaseDAO {

	public Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection myConn =  DriverManager.getConnection("jdbc:mysql://192.100.0.000:1521/ALMAR", "ALMAR", "admin");
				 
			return myConn;
			}catch(SQLException exc) {
				exc.printStackTrace();
				return null;}
		catch (ClassNotFoundException e) {
				e.printStackTrace();
				return null;
			}
	}
}
