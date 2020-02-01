package domain.definer;

import database.ToolDatabase.ToolDatabaseDaoOracleImpl;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class Rule {
	
	public ArrayList<Attribute> attributes;
	public String code;
	public String description;
	public int categoryId;
	public String SQLCode;
	public String operator;
	public String status;
	public int dbId;
	
	
	public Rule(ArrayList<Attribute> attributes, String code, String description, int categoryId, String SQLCode, String operator, String status) {
		this.attributes = attributes;
		this.code = code;
		this.description = description;
		this.categoryId = 21;
		this.SQLCode = SQLCode;
		this.operator = operator;
		this.status = status;
	}
	
	public Rule(ArrayList<Attribute> attributes, String code, String description, int categoryId, String SQLCode, String operator, String status, int dbId) {
		this.attributes = attributes;
		this.code = code;
		this.description = description;
		this.categoryId = 21;
		this.SQLCode = SQLCode;
		this.operator = operator;
		this.status = status;
		this.dbId = dbId;
	}
	
	public int save() {
		ToolDatabaseDaoOracleImpl tdb = ToolDatabaseDaoOracleImpl.getInstance();
		dbId = tdb.addRule(this.code, this.SQLCode, this.categoryId, this.operator, this.description, this.status);
		int i;
		int length = attributes.size();
		for(i = 0; i<length;i++) {
			Attribute currentAttribute = attributes.get(i);
			currentAttribute.save(dbId);
		}
		return dbId;
	}

	public int update(int ruleId) {
		ToolDatabaseDaoOracleImpl tdb = ToolDatabaseDaoOracleImpl.getInstance();
		tdb.updateRuleById(ruleId, this.operator, this.status);
		tdb.removeAttributesByRuleId(ruleId);

		int i;
		int length = attributes.size();
		for(i = 0; i < length; i++) {
			Attribute currentAttribute = attributes.get(i);
			currentAttribute.save(ruleId);
		}
		return ruleId;
	}

	public static List<String> getIdsOfSetTriggersByRuleCode(String code) {
		ToolDatabaseDaoOracleImpl tdb = ToolDatabaseDaoOracleImpl.getInstance();
		return tdb.getIdsOfSetTriggersByRuleCode(code);
	}

		public void addAttribute(Attribute attr) {
		attributes.add(attr);
	}
	
	public String toJSONString() {
		JSONObject ruleJSONObject = new JSONObject();

		JSONArray attributeJSONArray = new JSONArray();

		for(Attribute attribute : attributes){

			JSONObject nameJSONObject = new JSONObject();
			JSONObject valueJSONObject = new JSONObject();
			JSONObject entityJSONObject = new JSONObject();

			nameJSONObject.put("name", attribute.getName());
			valueJSONObject.put("value", attribute.getValue());
			entityJSONObject.put("entity", attribute.getEntity());

			attributeJSONArray.add(nameJSONObject);
			attributeJSONArray.add(valueJSONObject);
			attributeJSONArray.add(entityJSONObject);


		}

		ruleJSONObject.put("code", code);
		ruleJSONObject.put("description", description);
		ruleJSONObject.put("catagory", categoryId);
		ruleJSONObject.put("status", status);
		ruleJSONObject.put("attribute",attributeJSONArray);

		return ruleJSONObject.toJSONString();
	}
	
	public String toString() {
		if (SQLCode != null) {
			return "("+attributes +", "+ code +", "+ description +", "+ SQLCode.length() +", "+ categoryId +", "+ operator +", "+ status +", "+ dbId+")";
		}
		return "("+attributes +", "+ code +", "+ description +", "+ categoryId +", "+ operator +", "+ status +", "+ dbId+")";
		}

	public ArrayList<Attribute> getAttributes() {
		return attributes;
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public String getSQLCode() {
		return SQLCode;
	}

	public String getOperator() {
		return operator;
	}

	public String getStatus() {
		return status;
	}

	public int getDbId() {
		return dbId;
	}


}
