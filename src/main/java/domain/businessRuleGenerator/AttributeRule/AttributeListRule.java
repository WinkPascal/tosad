package domain.businessRuleGenerator.AttributeRule;


import java.util.List;

import domain.BusinessRule.Attribute;
import domain.businessRuleGenerator.BusinessRuleStrategy;

public class AttributeListRule implements BusinessRuleStrategy {

	private int id;
	private Attribute attribute;
	private List<String> values;
	
	//voorbeeld
	//deze is getest

	//ervoor nodig:
	//trigger id
	//entiteit 
	//attribuut uit ddb
	//costumValue lijst
	
//	create or replace trigger ALIS
	//    AFTER insert or update 
	//    ON entiteit1 FOR EACH ROW
	//DECLARE
	//    custumAttribute varchar2(255) := :NEW.varcharAttribute;
	//BEGIN 
	//    IF custumAttribute not in ('almar', 'pascal', 'anka') THEN
	//        Raise_Application_Error(-20343, 'deze is fout G');
	//        ROLLBACK;
	//    END IF;
	//END ALIS;
	
	
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
