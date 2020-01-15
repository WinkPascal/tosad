package domain.businessRuleGenerator;

import java.util.List;

import domain.BusinessRule.Attribute;

public interface BusinessRuleFactoryStrategy {
	public AttributeRuleInterface defineBusinessRule(String operator, List<Attribute> attributes);
}
