package database.TargetDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import database.ToolDatabase.OracleBaseDAO;
import domain.definer.Attribute;

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

}
