package database.ToolDatabase;

import java.sql.*;
import oracle.jdbc.driver.*;
import oracle.sql.*;

public class OracleBaseDAO {

	public Connection getConnection() {
		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			 Connection con = getConnection(); Statement stm =con.createStatement();
				  String errors = errors.getError();
				  
				  myStmt.executeQuery("INSERT INTO error ruleCode, name, type, description")
				 
				
			}catch(Exception exc) {
				exc.printStackTrace();
			}
	}
}
