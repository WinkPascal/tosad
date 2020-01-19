package domain.businessRuleGenerator.TupleRule;

import domain.businessRuleGenerator.BusinessRuleStrategy;
import domain.BusinessRule.Attribute;

public class TupleCompareRule implements BusinessRuleStrategy {
	private int ruleId;
	private String operator;
	private Attribute attribuut1;
	private Attribute attribuut2;

	//voorbeeld
	//CREATE or REPLACE trigger TupleCompareRule
		//AFTER insert or update
		//ON "table name"
	//DECLARE
		//attribuut1 date := :NEW.attribuut1;
		//attribuut2 date := :NEW.attribuut2;
	//BEGIN
		//IF attribuut1 > atribuut2 THEN
	 		//Raise_Application_Error (-20343, Attribuut1 || ' mag niet groter zijn dan' || attribuut2);
	        //ROLLBACK;
		//END IF;
	//END TupleCompareRule;


	//voorbeeld



	@Override
	public String createBusinessRule() {
		String trigger =
				"CREATE OR REPLACE trigger" + ruleId + "\n"
					+"AFTER insert or update \n"
					+"ON 'table name' \n"
				+"DECLARE /n"
					+"attribuut1 tabelnaam.attribuut1%type := :NEW.attribuut1; /n"
					+"attribuut2 tabelnaam.attribuut2%type := :NEW.attribuut2; /n"
				+"BEGIN /n"
					+"IF attribuut1 > attribuut2 THEN /n"
				       +"Raise_Application_Error (-20343, Attribuut1 || ' mag niet groter zijn dan' || attribuut2); /n"
					   +"ROLLBACK;"
			        +"END IF;"
				+"END" +ruleId;
		return trigger;

	}

}
