package domain.businessRuleGenerator.AttributeRule;

import domain.BusinessRule.Attribute;
import domain.businessRuleGenerator.BusinessRuleStrategy;

public class AttributeCompareRule implements BusinessRuleStrategy{
	
	private int ruleId;
	private String operator;
	private Attribute attribuut;
	private String value;
	
	
	
	//voorbeeld
	//deze is getest

	//ervoor nodig:
	//trigger id
	//entiteit 
	//attribuut uit ddb
	//costumValue
	
//	create or replace trigger ACMP
//    AFTER insert or update 
//	   ON entiteit1 FOR EACH ROW
//	DECLARE
//	    costumValue int := 10;
//	BEGIN
//	    if :NEW.integerAttribute > costumValue then
//	         Raise_Application_Error (-20343, 'trigger melding G.');
//	         ROLLBACK;
//	    end if;
//	END ACMP;

	
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
