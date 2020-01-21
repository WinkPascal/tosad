package domain.definer;

import java.util.ArrayList;

public class RuleBuilder {
    private ArrayList<Attribute> attributes;
    private String code;
    private String description;
    private String category;
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

    public RuleBuilder setCategory(String category) {
        this.category = category;
        return this;
    }

    public RuleBuilder setStatus(String status) {
        this.status = status;
        return this;
    }

    public Rule createRule() {
        return new Rule(attributes, code, description, category, status);
    }
}