package database.ToolDatabase;
import java.util.ArrayList;

import domain.definer.Attribute;
import domain.definer.Rule;

public interface ToolDatabaseDao {
	public void updateRuleById(int id, String code, int typeId, int categoryId, String operator, String description, String status);
	public ArrayList<Attribute> getAttributesByRule(int ruleId);
	public ArrayList<String> getValuesByAttribute(int attributeId);
	public void addAttribute(int ruleId, String name, ArrayList<String> value, String entity);
	public ArrayList<Rule> getAllRules();
	public int addRule(String code, String SQLCode, int categoryId, String operator, String description, String status);
}
