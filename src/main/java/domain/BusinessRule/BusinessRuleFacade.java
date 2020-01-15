package domain.BusinessRule;

import java.util.List;

import domain.businessRuleGenerator.AttributeRuleInterface;
import domain.businessRuleGenerator.BusinessRuleFactoryStrategy;
import domain.businessRuleGenerator.AttributeRule.AttributeRuleFactory;

public class BusinessRuleFacade {
	private List<Attribute> attributes;
	private Error error;
	
	private String code;
	private String operator;
	private String catagory;
	
	//als je een nieuwe buesines ruile wilt aanmaken eerst define naarna create
	// BusinessRuleInterfac
	public void getBusinessRule() {
		switch(catagory) {
		case "attributeRule":
			BusinessRuleFactoryStrategy factory = new AttributeRuleFactory();
			AttributeRuleInterface rule = factory.defineBusinessRule(operator, attributes);
			break;
		case "tupleRule":
			break;
		case "interEntityRule":
			break;
		case "entityRule":
			break;
		case "changeEventRule":
			
			break;			
		}
	}
}
