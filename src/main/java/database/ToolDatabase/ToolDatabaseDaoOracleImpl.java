package database.ToolDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domain.definer.Rule;

public class ToolDatabaseDaoOracleImpl extends OracleBaseDAO implements ToolDatabaseDao {
	
	private static final ToolDatabaseDaoOracleImpl instance = new ToolDatabaseDaoOracleImpl();
	
	private ToolDatabaseDaoOracleImpl() {};
	
    public static ToolDatabaseDaoOracleImpl getInstance(){
        return instance;
    }
	
	@Override
	public void updateRuleById(int id, String code, int typeId, int categoryId, String operator, String description, String status) {
		try {
			Connection myConn = super.getConnection();
			System.out.println("Connection returned.");
			
			Statement stm = myConn.createStatement();
			System.out.println("Statement created.");
			
			stm.executeQuery("UPDATE RULE SET code = \'"+code+"\', typeId = "+typeId+", categoryId = "+categoryId+", operator = \'"+operator+"\' description = \'"+description+"\', status = \'"+status+"\' WHERE id = "+id);
			System.out.println("Rule updated.");
			
			myConn.close();
		}catch(SQLException exc){
			exc.printStackTrace();	
		}
		
	}

	@Override
	public int addRule(String code, int typeId, int categoryId, String operator, String description, String status) {
		try {
			
			int newId = 0;
			Connection myConn = super.getConnection();
			String SQL_INSERT = "INSERT INTO RULE(code, typeId, categoryId, operator, description, status) VALUES (?, ?, ?, ?, ?, ?)";
			String SQL_CURRVAL = "SELECT RULE_AUTO_INCREMENT_SEQ.CURRVAL FROM dual";


			myConn.setAutoCommit(false);
			PreparedStatement stm = myConn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
			stm.setString(1, code);
			stm.setLong(2, typeId);
			stm.setLong(3, categoryId);
			stm.setString(4, operator);
			stm.setString(5, description);
			stm.setString(6, status);
			System.out.println("Statement prepared.");
			
			stm.executeUpdate();
			System.out.println("Rule inserted.");
			
		    Statement currvalStatement = myConn.createStatement();
		    ResultSet currvalResultSet = currvalStatement.executeQuery(SQL_CURRVAL);
		    System.out.println("Curvall resultset retrieved.");
		    
		    
		    if (currvalResultSet.next()) {
		        newId = currvalResultSet.getInt(1);
		        System.out.println("RuleId retrieved.");
		    }
		    myConn.commit();
		    myConn.close();
		    return newId;
		    
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}			
	
	
	@Override
	public void addAttribute(int ruleId, String name, String value, String entity) {
		try {
			Connection myConn = super.getConnection();
			System.out.println("Connection returned.");
			
			Statement stm = myConn.createStatement();
			System.out.println("Statement created.");
			
			stm.executeQuery("INSERT INTO ATTRIBUTE(ruleId, name, value, entity) VALUES ("+ruleId+", \'"+name+"\', \'"+value+"\', \'"+entity+"\')");
			System.out.println("Attribute inserted.");
			
			myConn.close();
		}catch(SQLException exc){
			exc.printStackTrace();	
		}
		
	}

	@Override
	public ArrayList<Rule> getAllRules() {
			Connection myConn = super.getConnection();
		return null;
	}

}

