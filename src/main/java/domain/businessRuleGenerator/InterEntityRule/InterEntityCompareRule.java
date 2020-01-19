package domain.businessRuleGenerator.InterEntityRule;

import domain.businessRuleGenerator.BusinessRuleStrategy;

public class InterEntityCompareRule implements BusinessRuleStrategy {

	//new attribute
	//entietit2
	//foreign key
	
	//entietit1
	//pk
	// compare attribute


	//note
	//kan alleen voor een fk op een pk
	
//	
//	create or replace trigger ICMP
//	   AFTER insert or update 
//	   ON entiteit2 FOR EACH ROW    
//	DECLARE
//	    newAttribute int := :NEW.integerAttribute;
//	    newAttributeForeignKey int := :NEW.integerAttribute;
//	    
//	    compareAttribute int default 0;
//	BEGIN
//	    select decimalAttribute INTO compareAttribute
//	    from entiteit1 
//	    where INTEGERATTRIBUTE = newAttributeForeignKey;
//
//	    IF  newAttribute > compareAttribute THEN
//	        Raise_Application_Error (-20343, 'ICMP newAttribute > compareAttribute');
//	        ROLLBACK;
//	    END IF;
//	END ICMP;
	
	@Override
	public String createBusinessRule() {
		// TODO Auto-generated method stub
		return null;
	}
}
