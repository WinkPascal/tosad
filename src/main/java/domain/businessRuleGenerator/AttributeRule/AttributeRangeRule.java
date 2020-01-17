package domain.businessRuleGenerator.AttributeRule;

import domain.BusinessRule.Attribute;
import domain.businessRuleGenerator.BusinessRuleStrategy;

public class AttributeRangeRule implements BusinessRuleStrategy{

	private int ruleId;
	private String operator;
	private Attribute attribuut;
	private String value1;
	private String value2;
	
	//vooorbeeld
	
	//create or replace trigger "id"
	//AFTER insert or update 
	//ON "table name"."attribute name"
	//DECLARE
	//attribute varchar2(255);
	//value1 = "";
	//value2 = "";
	//BEGIN
	//IF attribute < value1 AND attribute > value2 THEN
	//	INSERT ERROR;
	//	ROLLBACK;
	//END IF;
	//end "id"
	
	@Override
	public String createBusinessRule() {
		// TODO Auto-generated method stub
		return null;
	}

}
