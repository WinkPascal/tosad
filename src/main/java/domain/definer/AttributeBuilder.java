package domain.definer;

import java.util.ArrayList;
import java.util.List;

public class AttributeBuilder implements AttributeBuilderInterface{
    private String name = null;
    private ArrayList<String> value = null;
    private String entity = null;
    private int dbId = 0;

    public void setName(String name){
        this.name = name;
    }

    public void setValue(ArrayList<String> value){
        this.value = value;
    }

    public void setEntity(String entity){
        this.entity = entity;
    }

    public void setDbId(int dbId){
        this.dbId = dbId;
    }

    public Attribute build(){
        Attribute attribute =  new Attribute(name, value, entity, dbId);
        return attribute;
    }
}
