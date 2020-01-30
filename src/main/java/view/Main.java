package view;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;



import java.io.IOException;

import database.ToolDatabase.ToolDatabaseDaoOracleImpl;

public class Main extends Application {
    private Stage primaryStage;
    private static BorderPane mainLayout;

    private static final Main INSTANCE = new Main();

    public static Main getInstance(){
        return INSTANCE;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("TOSAD APP");
//      this.primaryStage.getIcons().add(new Image("/view/train.png"));
        showMainView();
        showMainItems();

    }

    private void showMainView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        System.out.println(getClass().getClassLoader().getResource("MainView.fxml"));
        loader.setLocation(getClass().getClassLoader().getResource("MainView.fxml"));
        mainLayout = loader.load();
        Scene scene = new Scene(mainLayout);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void showMainItems() throws IOException{
        System.out.println("Test1");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("MainItems.fxml"));
        System.out.println(getClass().getClassLoader().getResource("MainItems.fxml"));
        BorderPane mainView = loader.load();
        mainLayout.setCenter(mainView);

    }

    public void showNewAttributeRangeRule() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("NewAttributeRangeRule.fxml"));
        AnchorPane attributeRangeRuleView = loader.load();
        mainLayout.setCenter(attributeRangeRuleView);

    }
    public void showNewAttributeCompareRule() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("NewAttributeCompareRule.fxml"));
        AnchorPane attributeCompareRule = loader.load();
        mainLayout.setCenter(attributeCompareRule);


    }
    public void showNewAttributeListRule() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("NewAttributeListRule.fxml"));
        AnchorPane attributeListRule = loader.load();
        mainLayout.setCenter(attributeListRule);

    }
    public void showNewAttributeOtherRule() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("NewAttributeOtherRule.fxml"));
        AnchorPane attributeOtherRule = loader.load();
        mainLayout.setCenter(attributeOtherRule);


    }
    public void showNewTupleCompareRule() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("NewTupleCompareRule.fxml"));
        AnchorPane tupleCompareRule = loader.load();
        mainLayout.setCenter(tupleCompareRule);
    }
    public void showNewInterEntityCompareRule() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("NewInterEntityCompareRule.fxml"));
        AnchorPane interEntityCompareRule = loader.load();
        mainLayout.setCenter(interEntityCompareRule);
    }
    public void showNewModifyRule() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("NewModifyRule.fxml"));
        AnchorPane modifyRule = loader.load();
        mainLayout.setCenter(modifyRule);
    }
    public void showAllRules() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("ViewRules.fxml"));
        AnchorPane allRules = loader.load();
        mainLayout.setCenter(allRules);

    }

    public static void main(String[] args) {
    	launch(args);
    }
}
