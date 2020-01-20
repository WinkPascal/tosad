module TOSAD {
    requires transitive javafx.controls;
    requires transitive javafx.fxml;
    requires json.simple;
	requires java.sql;
    exports view;
    exports controller;
    exports definer;
    opens view to javafx.base, javafx.fxml;
    opens controller to javafx.base , javafx.fxml;
    opens definer to json.simple;

}