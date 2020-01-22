package domain.businessRule;

import java.util.HashMap;
import java.util.List;

public interface FacadeInterface {
	public List<Table> getTables();

	
	public HashMap<String, String> fillGui(String type);
	
	public void generate();
}
