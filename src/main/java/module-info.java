module TOSAD {
    requires transitive javafx.controls;
    requires transitive javafx.fxml;
    exports view;
    exports controller;
    opens view to javafx.base, javafx.fxml;
    opens controller to javafx.base , javafx.fxml;

}