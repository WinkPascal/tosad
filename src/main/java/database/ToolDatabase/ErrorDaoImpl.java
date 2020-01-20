package database.ToolDatabase;

import oracle.sql.*;

public class ErrorDaoImpl implements ErrorDao {

	@Override
	public void save(Error error) {
		try {
		
		 OracleConnection con = getConnection(); Statement stm =con.createStatement();
			  String errors = errors.getError();
			  
			  myStmt.executeQuery("INSERT INTO error ruleCode, name, type, description");
			 
			
		}catch(Exception exc) {
			exc.printStackTrace();
		}
		
	}

}
