package domain.connection;

import org.json.simple.JSONObject;

import java.text.ParseException;

public class TransportRule {
    private String method;
    private int id;

    public TransportRule(String method, int id) {
        this.method = method;
        this.id = id;
    }

    public String toJSONString() {
        JSONObject transportRuleJSONObject = new JSONObject();



        transportRuleJSONObject.put("method",method);
        transportRuleJSONObject.put("id", id );

        return  transportRuleJSONObject.toJSONString();



    }
}
