module TOSAD {
    requires transitive javafx.controls;
    requires transitive javafx.fxml;
	requires java.sql;
    exports view;
    exports controller;
    opens view to javafx.base, javafx.fxml;
    opens controller to javafx.base , javafx.fxml;

}