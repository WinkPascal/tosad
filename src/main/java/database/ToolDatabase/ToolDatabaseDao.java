package database.ToolDatabase;
import java.util.ArrayList;
import domain.definer.Rule;

public interface ToolDatabaseDao {
	public int addRule(String code, int typeId, int categoryId, String operator, String description, String status);
	public void updateRuleById(int id, String code, int typeId, int categoryId, String operator, String description, String status);
	public void addAttribute(int ruleId, String name, String value, String entity);
	public ArrayList<Rule> getAllRules();
}
