package domain.businessRuleGenerator.AttributeRule;

import domain.businessRuleGenerator.BusinessRuleStrategy;

public class AttributeCompareRule implements BusinessRuleStrategy{

	//voorbeeld
	//create or replace trigger "id"
	//BEFORE insert or update of "attribute naam"
	//ON "table name"
	//DECLARE
	//attribute varchar2(255) 
	//

	
	@Override
	public String createBusinessRule() {
		// TODO Auto-generated method stub
		return null;
	}

}
