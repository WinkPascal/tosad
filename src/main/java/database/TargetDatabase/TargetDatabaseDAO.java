package database.TargetDatabase;

import java.util.ArrayList;

import domain.definer.Relation;

public interface TargetDatabaseDAO {
	public ArrayList<String> getTables();
	public ArrayList<String> getAllColumns(String tableName);
	public ArrayList<String> getIntColumns(String tableName);
	public ArrayList<String> getStringColumns(String tableName);
	public ArrayList<Relation> getRelationalTables();
}
