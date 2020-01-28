package database.ToolDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domain.definer.Attribute;
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
	public int addRule(String code, String SQLCode, int categoryId, String operator, String description, String status) {
		try {
			
			int newId = 0;
			Connection myConn = super.getConnection();
			String SQL_INSERT = "INSERT INTO RULE(code, sqlcode, categoryId, operator, description, status) VALUES (?, ?, ?, ?, ?, ?)";
			String SQL_CURRVAL = "SELECT RULE_AUTO_INCREMENT_SEQ.CURRVAL FROM dual";


			myConn.setAutoCommit(false);
			PreparedStatement stm = myConn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
			stm.setString(1, code);
			stm.setString(2, SQLCode);
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
	public int addAttribute(int ruleId, String name, String entity) {
		try {
			int newId = 0;
			Connection myConn = super.getConnection();
			String SQL_INSERT = "INSERT INTO attribute(ruleId, name, entity) VALUES (?, ?, ?)";
			String SQL_CURRVAL = "SELECT ATTRIBUTE_AUTO_INCREMENT_SEQ.CURRVAL FROM dual";


			myConn.setAutoCommit(false);
			PreparedStatement stm = myConn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
			stm.setLong(1, ruleId);
			stm.setString(2, name);
			stm.setString(3, entity);
			
			stm.executeUpdate();
			
		    Statement currvalStatement = myConn.createStatement();
		    ResultSet currvalResultSet = currvalStatement.executeQuery(SQL_CURRVAL);
		    
		    if (currvalResultSet.next()) {
		        newId = currvalResultSet.getInt(1);
		    }
		    myConn.commit();
		    myConn.close();
		    return newId;
		    
		}catch(SQLException exc){
			exc.printStackTrace();
			return 0;
		}
		
	}
	
	@Override
	public void addValue(int attributeId, String value) {
		Connection myConn = super.getConnection();	
		Statement stm;
		try {
			stm = myConn.createStatement();
			stm.executeQuery("INSERT INTO VALUE(attributeId, value) VALUES ("+attributeId+", \'"+value+"\')");		
			
			myConn.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Attribute> getAttributesByRule(int ruleId) {
		 ArrayList<Attribute> attributes = new ArrayList<Attribute>();
		try {
			Connection myConn = super.getConnection();
			Statement stm = myConn.createStatement();
			ResultSet rs = stm.executeQuery("SELECT * FROM ATTRIBUTE WHERE ruleId = "+ruleId);
			while(rs.next()) {
				ArrayList<String> values = new ArrayList<String>();
				int attId = Integer.parseInt(rs.getString(1));
				values = this.getValuesByAttribute(attId);
				attributes.add(new Attribute(rs.getString(3), values, rs.getString(4), attId));
			}
		}catch(Exception exc){
			exc.printStackTrace();
		}
		 return attributes;
	}
	
	public ArrayList<String> getValuesByAttribute(int attributeId) {
		ArrayList<String> values = new ArrayList<String>();
		try {
			Connection myConn = super.getConnection();
			Statement stm = myConn.createStatement();
			ResultSet rs = stm.executeQuery("SELECT value FROM VALUE WHERE attributeId = "+attributeId);
			while(rs.next()) {
				values.add(rs.getString(1));
			}
		}catch(Exception exc){
			exc.printStackTrace();
		}
		 return values;
	}

	@Override
	public ArrayList<Rule> getAllRules() {
		try {
			ArrayList<Rule> ruleList = new ArrayList<Rule>();
			
			Connection myConn = super.getConnection();
			Statement stm = myConn.createStatement();
			ResultSet rs = stm.executeQuery("SELECT * FROM RULE");
			
			while (rs.next()) {
		        int i = 1;
		        
		        List<String> tempList = new ArrayList<String>();
		        
		        while(i <= 7) {
		            tempList.add(rs.getString(i));
		            if (i == 7) {
		            	ruleList.add(new Rule(this.getAttributesByRule(Integer.parseInt(tempList.get(0))), 
		            			tempList.get(1), 
		            			tempList.get(3), 
		            			Integer.parseInt(tempList.get(2)), 
		            			tempList.get(6), 
		            			tempList.get(5), tempList.get(4), 
		            			Integer.parseInt(tempList.get(0))));
		            }
		            i++;
		        }
			}
			
			
			myConn.close();
			return ruleList;
			
		}catch(SQLException exc){
			exc.printStackTrace();	
			return null;
		}
	}

}

