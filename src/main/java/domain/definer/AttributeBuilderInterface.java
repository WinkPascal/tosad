package domain.definer;

import java.util.ArrayList;

public interface AttributeBuilderInterface {
    public void setName(String name);

    public void setValue(ArrayList<String> value);

    public void setEntity(String entity);

    public void setDbId(int dbId);

    public Attribute build();
}
