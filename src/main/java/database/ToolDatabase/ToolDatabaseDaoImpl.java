package database.ToolDatabase;

import java.util.List;

import domain.BusinessRule.BusinessRule;

public class ToolDatabaseDaoImpl implements ToolDatabaseDao {

	@Override
	public BusinessRule getRuleById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateStatus(int id, String status) {
		try {
			Connection con = getConnection();
			Stamtement stm = con.creatStatement();
			
			String status = rule.getStatus();
			int id=  rule.getId();
			
			stm.executeQuery("UPDATE rule SET =  '" + status + "' WHERE id = " + id); 
		}catch(Exception exc){
			exc.printStackTrace();	
		}
		
	}


}
