package domain.businessRuleGenerator.AttributeRule;

import domain.BusinessRule.Attribute;
import domain.businessRuleGenerator.BusinessRuleStrategy;

public class AttributeCompareRule implements BusinessRuleStrategy{
	private int ruleId;
	private String operator;
	private Attribute attribuut;
	private String value;
	
	//voorbeeld
	
	//create or replace trigger "id"
	//BEFORE insert or update of "attribute naam"
	//ON "table name"
	//DECLARE
	//attribute varchar2(255) 
	//BEGIN
	//if attribute >  'losse attribuut' then
	//	insert into error() values();
	//end if;
	//end id;

	
	@Override
	public String createBusinessRule() {
		String trigger = 
					"CREATE OR REPLACE TRIGGER ACMP"+ruleId+ " \n"+
				"BEFORE insert, update OF "+attribuut.getNaam()+ " \n"+
				"ON "+ attribuut.getEntiteit()+ " \n "+
				"DECLARE \n" + 
				"attribute varchar2(255) \n" +
				"BEGIN \n" +
				"";
		
		return trigger;
	}

}
