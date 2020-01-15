module TOSAD {
    requires transitive javafx.controls;
    requires transitive javafx.fxml;
    exports view;
    exports controler;
    opens view to javafx.base, javafx.fxml;
    opens controler to javafx.base , javafx.fxml;

}