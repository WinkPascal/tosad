module TOSAD {
    requires transitive javafx.controls;
    requires transitive javafx.fxml;
    requires transitive java.sql;
    requires json.simple;
    requires ojdbc10;
    exports view;
    exports controller;
    exports domain.definer;
    exports database.ToolDatabase;
    opens view to javafx.base, javafx.fxml;
    opens controller to javafx.base , javafx.fxml;
    opens domain.definer to json.simple, javafx.fxml, javafx.base, javafx.controls, javafx.graphics;
    opens database.ToolDatabase to java.sql;


}