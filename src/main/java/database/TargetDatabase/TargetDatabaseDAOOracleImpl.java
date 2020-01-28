package database.TargetDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import database.ToolDatabase.OracleBaseDAO;
import domain.definer.Attribute;
import domain.definer.Relation;

public class TargetDatabaseDAOOracleImpl extends OracleBaseDAO implements TargetDatabaseDAO{
	
	private static final TargetDatabaseDAOOracleImpl instance = new TargetDatabaseDAOOracleImpl();
	
	private TargetDatabaseDAOOracleImpl() {};
	
    public static TargetDatabaseDAOOracleImpl getInstance(){
        return instance;
    }

	
	@Override
	public ArrayList<String> getTables() {
		try {
			ArrayList<String> tableList = new ArrayList<String>();
			Connection myConn = super.getConnectionManually("jdbc:oracle:thin:@//ondora04.hu.nl:8521/EDUC22","TARGET", "TARGET");
			Statement stm = myConn.createStatement();
			ResultSet rs = stm.executeQuery("SELECT TABLE_NAME FROM USER_TABLES");
			
			while(rs.next()) {
				tableList.add(rs.getString(1));
			}
			
			return tableList;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	@Override
	public ArrayList<Relation> getRelationalTables() {
		ArrayList<Relation> relationList = new ArrayList<Relation>();
		try {
			Connection myConn = super.getConnectionManually("jdbc:oracle:thin:@//ondora04.hu.nl:8521/EDUC22","TARGET", "TARGET");
			Statement stm = myConn.createStatement();
			ResultSet rs = stm.executeQuery("SELECT distinct a.table_name, c_pk.table_name r_table_name "
					+ "FROM all_cons_columns a "
					+ "JOIN all_constraints c ON a.owner = c.owner "
					+ "AND a.constraint_name = c.constraint_name "
					+ "JOIN all_constraints c_pk ON c.r_owner = c_pk.owner "
					+ "AND c.r_constraint_name = c_pk.constraint_name "
					+ "WHERE c.constraint_type = 'R' "
					+ "AND a.table_name in (SELECT TABLE_NAME FROM USER_TABLES)");
			
			while(rs.next()) {
				relationList.add(new Relation(rs.getString(1), rs.getString(2)));
			}
			return relationList;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ArrayList<String> getAllColumns(String tableName) {
		try {
			ArrayList<String> columnList = new ArrayList<String>();
			Connection myConn = super.getConnectionManually("jdbc:oracle:thin:@//ondora04.hu.nl:8521/EDUC22","TARGET", "TARGET");
			Statement stm = myConn.createStatement();
			ResultSet rs = stm.executeQuery("SELECT column_name	FROM USER_TAB_COLUMNS WHERE table_name = \'"+tableName+"\'");
			
			while(rs.next()) {
				columnList.add(rs.getString(1));
			}
			
			return columnList;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ArrayList<String> getIntColumns(String tableName) {
		try {
			ArrayList<String> columnList = new ArrayList<String>();
			Connection myConn = super.getConnectionManually("jdbc:oracle:thin:@//ondora04.hu.nl:8521/EDUC22","TARGET", "TARGET");
			Statement stm = myConn.createStatement();
			ResultSet rs = stm.executeQuery("SELECT column_name	FROM USER_TAB_COLUMNS WHERE table_name = \'"+tableName+"\' AND DATA_TYPE = \'NUMBER\'");
			
			while(rs.next()) {
				columnList.add(rs.getString(1));
			}
			
			return columnList;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ArrayList<String> getStringColumns(String tableName) {
		try {
			ArrayList<String> columnList = new ArrayList<String>();
			Connection myConn = super.getConnectionManually("jdbc:oracle:thin:@//ondora04.hu.nl:8521/EDUC22","TARGET", "TARGET");
			Statement stm = myConn.createStatement();
			ResultSet rs = stm.executeQuery("SELECT column_name	FROM USER_TAB_COLUMNS WHERE table_name = \'"+tableName+"\' AND (DATA_TYPE = \'VARCHAR\' OR DATA_TYPE = \'VARCHAR2\')");
			
			while(rs.next()) {
				columnList.add(rs.getString(1));
			}
			
			return columnList;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
