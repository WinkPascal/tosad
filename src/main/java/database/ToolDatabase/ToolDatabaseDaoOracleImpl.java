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
	public void updateRuleById(int id, String operator, String status) {
		Connection myConn = super.getConnection();
		Statement stm;
		try {
			stm = myConn.createStatement();
			if(operator == null){
				stm.executeQuery("UPDATE RULE " +
						"SET operator = "+operator+", " +
						"status = '"+status+"' " +
						"WHERE id = "+id);
			} else{
				stm.executeQuery("UPDATE RULE " +
						"SET operator = '"+operator+"', " +
						"status = '"+status+"' " +
						"WHERE id = "+id);
			}

			System.out.println("dsadasdsakjdskjadkjsakjdkasjdasda");
			myConn.close();

		} catch (SQLException e) {
			e.printStackTrace();
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
			
			stm.executeUpdate();
			
		    Statement currvalStatement = myConn.createStatement();
		    ResultSet currvalResultSet = currvalStatement.executeQuery(SQL_CURRVAL);
		    
		    
		    if (currvalResultSet.next()) {
		        newId = currvalResultSet.getInt(1);
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
		return this.getRuleList("SELECT * FROM RULE");
	}

	@Override
	public ArrayList<Rule> getRulesByCode(String code) {
		return this.getRuleList("SELECT * FROM rule "
					+ "WHERE code LIKE \'%"+code+"%\'");
	}

	@Override
	public ArrayList<Rule> getRulesByEntity(String entity) {
		return (this.getRuleList("SELECT r.id, r.code, r.categoryId, r.description, r.status, r.operator, r.SQLCode"
					+ " FROM rule r, attribute a "
					+ "WHERE (a.RULEID = r.ID) AND (a.ENTITY LIKE \'%"+entity+"%\')"));
	}
	
	@Override
	public List<String> getIdsOfSetTriggersByRuleCode(String code) {
		ArrayList<String> ruleList = new ArrayList<String>();
		try {
			Connection myConn = super.getConnection();
			Statement stm = myConn.createStatement();
			ResultSet rs = stm.executeQuery("SELECT id FROM RULE where status = 'executed' AND code = '"+code+"'");


			while (rs.next()) {
				ruleList.add(rs.getString("id"));
			}
		} catch (SQLException exc) {
			exc.printStackTrace();
		}
		return ruleList;
	}

	public void removeAttributesByRuleId(int ruleId) {
    	for(String id : getAttributesByRuleId(ruleId)){
			removeValuesByAttributeId(id);
		}

		try {
			Connection myConn = super.getConnection();
			Statement stm;
			stm = myConn.createStatement();
			stm.executeQuery("DELETE FROM attribute WHERE ruleid = "+ruleId);
			myConn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private void removeValuesByAttributeId(String attributeId){
		try {
			Connection myConn = super.getConnection();
			Statement stm;
			stm = myConn.createStatement();
			stm.executeQuery("DELETE FROM value WHERE attributeid = "+attributeId);
			myConn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private List<String> getAttributesByRuleId(int ruleId){
		ArrayList<String> atributeList = new ArrayList<String>();
		try {
			Connection myConn = super.getConnection();
			Statement stm = myConn.createStatement();
			ResultSet rs = stm.executeQuery("SELECT id FROM attribute where ruleid = "+ruleId);

			while (rs.next()) {
				atributeList.add(rs.getString("id"));
			}
		} catch (SQLException exc) {
			exc.printStackTrace();
		}
		return atributeList;
	}


	public ArrayList<Rule> getRuleList(String query) {
		try {
			ArrayList<Rule> ruleList = new ArrayList<Rule>();
			
			Connection myConn = super.getConnection();
			Statement stm = myConn.createStatement();
			ResultSet rs = stm.executeQuery(query);
			
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

