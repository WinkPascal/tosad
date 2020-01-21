package definer;

import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class Rule {
	public Rule(ArrayList<Attribute> attributes, String code, String description, String category, String status) {
		this.attributes = attributes;
		this.code = code;
		this.description = description;
		this.category = category;
		this.status = status;
	}

	private ArrayList<Attribute> attributes;
	private String code;
	private String description;
	private String category;
	private String status;
	
	public void save() {

		
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
		ruleJSONObject.put("catagory", category);
		ruleJSONObject.put("status", status);
		ruleJSONObject.put("attribute",attributeJSONArray);

		return ruleJSONObject.toJSONString();



	}
}
