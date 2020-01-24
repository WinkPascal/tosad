package domain.definer;

import database.ToolDatabase.ToolDatabaseDaoOracleImpl;

public class Attribute {
	private String name;
	private String value;
	private String entity;
	private int dbId;
	
	public Attribute(String name, String value, String entity) {
		this.name = name;
		this.value = value;
		this.entity = entity;
	}
	
	public Attribute(String name, String value, String entity, int dbId) {
		this.name = name;
		this.value = value;
		this.entity = entity;
		this.dbId = dbId;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}
	
	public String getEntity() {
		return entity;
	}
	
	public void save(int ruleId) {
		ToolDatabaseDaoOracleImpl tdb = ToolDatabaseDaoOracleImpl.getInstance();
		tdb.addAttribute(ruleId, name, value, entity);
	}
	
	public String toString() {
		return name;
	}
}




