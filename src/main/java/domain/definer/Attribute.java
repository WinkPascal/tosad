package domain.definer;

import java.util.ArrayList;

import database.ToolDatabase.ToolDatabaseDaoOracleImpl;
import javafx.scene.control.ComboBox;

public class Attribute {
	private String name;
	private ArrayList<String> value;
	private String entity;
	private int dbId;
	
	public Attribute(String name, ArrayList<String> value, String entity) {
		this.name = name;
		this.value = value;
		this.entity = entity;
	}
	
	public Attribute(String name, ArrayList<String> value, String entity, int dbId) {
		this.name = name;
		this.value = value;
		this.entity = entity;
		this.dbId = dbId;
	}



	public String getName() {
		return name;
	}

	public ArrayList<String> getValue() {
		return value;
	}
	
	public String getEntity() {
		return entity;
	}
	
	public void save(int ruleId) {
		ToolDatabaseDaoOracleImpl tdb = ToolDatabaseDaoOracleImpl.getInstance();
		int attributeId = tdb.addAttribute(ruleId, name, entity);
		this.dbId = attributeId;
		this.saveValues(attributeId);
	}
	
	public void saveValues(int attributeId) {
		ToolDatabaseDaoOracleImpl tdb = ToolDatabaseDaoOracleImpl.getInstance();
		int i;
		if(value != null){
			for(i = 0; i < value.size(); i++) {

				tdb.addValue(attributeId, value.get(i));
			}
		}
	}
	
	public String toString() {
		String str = name + "[";
		int i;
		for(i = 0; i < value.size(); i++) {
			str = str + value.get(i);
		}
		str = str + "]";
		return str;
	}
}




