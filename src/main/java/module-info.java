module TOSAD {
    requires transitive javafx.controls;
    requires transitive javafx.fxml;
    requires json.simple;
    requires mysql.connector.java;
	requires java.sql;
	requires ojdbc10;
    exports view;
    exports controller;
    exports domain.definer;
    opens view to javafx.base, javafx.fxml;
    opens controller to javafx.base , javafx.fxml;
    opens domain.definer to json.simple;

}