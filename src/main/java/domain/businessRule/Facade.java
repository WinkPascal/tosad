package domain.businessRule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Facade implements FacadeInterface {
	public List<Table> getTables(){
		List<Table> tables = new ArrayList<Table>();
		
		List<String> columnsTable1 = new ArrayList<String>();
		columnsTable1.add("attrinbute1");
		columnsTable1.add("attribute2");
		columnsTable1.add("attribute3");
		Table table1 = new Table("table1", columnsTable1);
		tables.add(table1);
		
		List<String> columnsTable2 = new ArrayList<String>();
		columnsTable2.add("attribute4");
		columnsTable2.add("attribute5");
		columnsTable2.add("attribute6");
		Table table2 = new Table("table2",columnsTable2);
		tables.add(table2);
		
		return tables;
	}
	
	@Override
	public HashMap<String, String> fillGui(String type){
		HashMap<String, String> map = new  HashMap<String, String>();
		
		return null;
		
	}

	@Override
	public void generate() {
		// TODO Auto-generated method stub
		
	}
	
}
