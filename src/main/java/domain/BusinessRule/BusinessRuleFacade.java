package domain.BusinessRule;

import java.util.List;

import domain.businessRuleGenerator.BusinessRuleStrategy;
import domain.businessRuleGenerator.AttributeRule.AttributeCompareRule;
import domain.businessRuleGenerator.AttributeRule.AttributeListRule;
import domain.businessRuleGenerator.AttributeRule.AttributeOtherRule;
import domain.businessRuleGenerator.AttributeRule.AttributeRangeRule;
import domain.businessRuleGenerator.EntityRule.EntityOtherRule;
import domain.businessRuleGenerator.InterEntityRule.InterEntityCompareRule;
import domain.businessRuleGenerator.TupleRule.TupleCompareRule;
import domain.businessRuleGenerator.TupleRule.TupleOtherRule;

public class BusinessRuleFacade {
	private List<Attribute> attributes;
	private Error error;
	
	private String code;
	private String operator;
	private String catagory;
	

	private BusinessRuleStrategy getBusinessRule() {
		BusinessRuleStrategy rule = null;
		switch(code) {
		//attribute rules
		case "ARNG":
			rule = new AttributeRangeRule();
			break;
		case "ACMP":
			rule = new AttributeCompareRule();
			break;
		case "ALIS":
			rule = new AttributeListRule();
			break;
		case "AOTH":
			rule = new AttributeOtherRule();
			break;
		//tuple rules
		case "TCMP":
			rule = new TupleCompareRule();
			break;
		case "TOTH":
			rule = new TupleOtherRule();
			break;
		// inter entity rule
		case "ICMP":
			rule = new InterEntityCompareRule();
			break;
		//entity rule
		case "EOTH":
			rule = new EntityOtherRule();
			break;
		//mofiy rule
		case "MODI":
			rule = new AttributeListRule();
			break;		
		}
		return rule;
	}
}
