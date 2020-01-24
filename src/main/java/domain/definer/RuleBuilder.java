package domain.definer;

import java.util.ArrayList;

public class RuleBuilder {
    private ArrayList<Attribute> attributes;
    private String code;
    private String description;
    private int categoryId;
    private int typeId;
    private String operator;
    private String status;

    public RuleBuilder setAttributes(ArrayList<Attribute> attributes) {
        this.attributes = attributes;
        return this;
    }

    public RuleBuilder setCode(String code) {
        this.code = code;
        return this;
    }

    public RuleBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public RuleBuilder setCategoryId(int categoryId) {
        this.categoryId = categoryId;
        return this;
    }
    
    public RuleBuilder setTypeId(int typeId) {
        this.typeId = typeId;
        return this;
    }

    public RuleBuilder setStatus(String status) {
        this.status = status;
        return this;
    }
    
    public RuleBuilder setOperator(String operator) {
        this.operator = operator;
        return this;
    }

    public Rule createRule() {
        return new Rule(attributes, code, description, categoryId, typeId, operator, status);
    }
}