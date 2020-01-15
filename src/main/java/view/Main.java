package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;



import java.io.IOException;

public class Main extends Application {
    private Stage primaryStage;
    private static BorderPane mainLayout;

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
        BorderPane trainView = loader.load();
        mainLayout.setCenter(trainView);
    }
    private void showNewAttributeRangeRule(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("NewAttributeRangeRule.fxml"));

    }







    public static void main(String[] args) {
        launch(args);
    }


}
