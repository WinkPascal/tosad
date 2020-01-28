package database.TargetDatabase;

import java.util.ArrayList;

public interface TargetDatabaseDAO {
	public ArrayList<String> getTables();
	public ArrayList<String> getAllColumns(String tableName);
	public ArrayList<String> getIntColumns(String tableName);
	public ArrayList<String> getStringColumns(String tableName);
}
