module TOSAD {
    requires transitive javafx.controls;
    requires transitive javafx.fxml;
    exports view;
    opens view to javafx.base, javafx.fxml;



}