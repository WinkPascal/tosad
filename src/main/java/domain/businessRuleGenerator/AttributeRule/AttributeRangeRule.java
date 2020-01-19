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
	
//	create or replace trigger ARNG
//	    AFTER insert or update 
//	    ON entiteit1 FOR EACH ROW
//	DECLARE
//	    minLimit int := 5;
//	    maxLimit int := 10;
//	    custumAttribute int := :NEW.integerAttribute;
//	BEGIN
//	    if custumAttribute > maxLimit then
//	         Raise_Application_Error (-20343, 'ARNG te groot');
//	         ROLLBACK;
//	    end if;
//	    if custumAttribute < minLimit then
//	         Raise_Application_Error (-20343, 'tARNG te klein .');
//	         ROLLBACK;
//	    end if;
//	END ARNG;
	
	@Override
	public String createBusinessRule() {
		// TODO Auto-generated method stub
		return null;
	}

}
