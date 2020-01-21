package database.ToolDatabase;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ToolDatabaseDaoOracleImpl extends OracleBaseDAO implements ToolDatabaseDao {
	
	@Override
	public void updateStatus(int id, String status) {
		try {
			Connection myConn = super.getConnection();
			Statement stm = myConn.createStatement();
			
//			String status = rule.getStatus();
//			int id=  rule.getId();
			
//			stm.executeQuery("UPDATE rule SET =  '" + status + "' WHERE id = " + id);

		}catch(SQLException exc){
			exc.printStackTrace();	
		}
		
	}

	@Override
	public void addRule() {
		try {
			Connection myConn = super.getConnection();
			Statement stm = myConn.createStatement();
			stm.executeQuery("INSERT INTO RULE(code, typeId, categoryId, description, status) VALUES ('TUP', 1, 1, 'boi', 'created')");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}


}
