package database.TargetDatabase;

import java.util.ArrayList;

public interface TargetDatabaseDAO {
	public ArrayList<String> getTables();
	public ArrayList<String> getColumns(String tableName);
}
