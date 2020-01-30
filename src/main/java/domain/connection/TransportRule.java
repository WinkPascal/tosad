package domain.connection;

import org.json.simple.JSONObject;

import java.text.ParseException;

public class TransportRule {
    private int ruleId;
    private String method;

    public TransportRule(int ruleId, String method) {
        this.ruleId = ruleId;
        this.method = method;
    }

    public String toJSONString() {
        JSONObject transportRuleJSONObject = new JSONObject();



        transportRuleJSONObject.put("ruleId", ruleId);
        transportRuleJSONObject.put("method", method);


        return  transportRuleJSONObject.toJSONString();



    }
}
