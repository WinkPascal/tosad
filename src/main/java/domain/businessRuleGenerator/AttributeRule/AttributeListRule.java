package domain.businessRuleGenerator.AttributeRule;


import java.util.List;

import domain.BusinessRule.Attribute;
import domain.businessRuleGenerator.BusinessRuleStrategy;

public class AttributeListRule implements BusinessRuleStrategy {

	private int id;
	private Attribute attribute;
	private List<String> values;
	
	
	//create or replace trigger "id"
	//AFTER insert or update 
	//ON "table name"."attribute name"
	//DECLARE
	//variable
	//BEGIN 
	//CASE variable
		//WHEN "list value" THEN
			//sql
		//WHEN "list value" THEN
			//sql
		//WHEN "list value" THEN
			//sql
		//WHEN "list value" THEN
			//sql
		//else
			//error
	//end
	
	
	@Override
	public String createBusinessRule() {
		String querie = "CREATE OR REPLACE TRIGGER "+id+ " \n"
				+ "AFTER INSERT, UPDATE \n"
				+ "ON "+attribute.getEntiteit +"."+attribute.getNaam()+" \n"
				+ "DECLARE \n "
				+ "attribute varchar2(255) new."+attribute.getNaam()+" \n"
				+ "";
	}

}
