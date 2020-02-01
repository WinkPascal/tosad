package database.ToolDatabase;
import java.util.ArrayList;
import java.util.List;

import domain.definer.Attribute;
import domain.definer.Rule;

public interface ToolDatabaseDao {
	public void updateRuleById(int id, String operator, String status);

	public ArrayList<Attribute> getAttributesByRule(int ruleId);

	public ArrayList<String> getValuesByAttribute(int attributeId);

	public int addAttribute(int ruleId, String name, String entity);

	public ArrayList<Rule> getAllRules();

	public int addRule(String code, String SQLCode, int categoryId, String operator, String description, String status);

	public void addValue(int attributeId, String value);

	public ArrayList<Rule> getRulesByCode(String code);

	public ArrayList<Rule> getRulesByEntity(String entity);

	public List<String> getIdsOfSetTriggers();

	}

