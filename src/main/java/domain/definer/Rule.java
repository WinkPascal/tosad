package domain.definer;

import database.ToolDatabase.ToolDatabaseDaoOracleImpl;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class Rule {
	public Rule(ArrayList<Attribute> attributes, String code, String description, int categoryId, int typeId, String operator, String status) {
		this.attributes = attributes;
		this.code = code;
		this.description = description;
		this.categoryId = categoryId;
		this.typeId = typeId;
		this.operator = operator;
		this.status = status;
	}

	private ArrayList<Attribute> attributes;
	private String code;
	private String description;
	private int categoryId;
	private int typeId;
	private String operator;
	private String status;
	private int dbId;
	
	public void save() {
		ToolDatabaseDaoOracleImpl tdb = ToolDatabaseDaoOracleImpl.getInstance();
		dbId = tdb.addRule(this.code, this.typeId, this.categoryId, this.operator, this.description, this.status);
		int i;
		int length = attributes.size();
		for(i = 0; i<length;i++) {
			Attribute currentAttribute = attributes.get(i);
			currentAttribute.save(dbId);
		}
		
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
}
