package domain.businessRuleGenerator.AttributeRule;

import domain.businessRuleGenerator.BusinessRuleStrategy;

public class AttributeListRule implements BusinessRuleStrategy {

	
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
		return null;
	}

}
