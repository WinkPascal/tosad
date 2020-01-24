package domain.connection;

import org.json.simple.JSONObject;

import java.text.ParseException;

public class TransportRule {
    private String entiteit;
    private int id;
    private int value;
    private String attribuut;
    private String operator;

    public TransportRule(String entiteit, int id, int value, String attribuut, String operator) {
        this.entiteit = entiteit;
        this.id = id;
        this.value = value;
        this.attribuut = attribuut;
        this.operator = operator;
    }

    public String toJSONString() {
        JSONObject transportRuleJSONObject = new JSONObject();



        transportRuleJSONObject.put("ruleId", id );
        transportRuleJSONObject.put("entiteit",entiteit);
        transportRuleJSONObject.put("value", value);
        transportRuleJSONObject.put("attribuut", attribuut);
        transportRuleJSONObject.put("operator", operator);


        return  transportRuleJSONObject.toJSONString();



    }
}
