package controller;

import database.TargetDatabase.TargetDatabaseDAO;
import database.TargetDatabase.TargetDatabaseDAOOracleImpl;
import domain.connection.Client;
import domain.connection.TransportRule;
import domain.definer.Attribute;
import domain.definer.AttributeBuilder;
import domain.definer.AttributeBuilderInterface;
import domain.definer.Rule;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AttributeOtherRuleController implements Initializable, Controller {

    private int currentId = 0;

    private static final AttributeOtherRuleController INSTANCE = new AttributeOtherRuleController();

    public static AttributeOtherRuleController getInstance(){
        if(INSTANCE == null){
            return new AttributeOtherRuleController();
        }
        return INSTANCE;
    }

    @FXML public ComboBox<String> dataBaseCombo = new ComboBox();
    @FXML public ComboBox<String> tableCombo = new ComboBox<>();

    @FXML public TextField inputField;

    @FXML public Button generateButton;
    @FXML public Button setButton;
    @FXML public Button cancelButton;

    @FXML public TextArea previewArea;


    public void generate() throws InterruptedException {
        if( dataBaseCombo.getValue() == null ||
                tableCombo.getValue() == null ||
                dataBaseCombo.getValue().trim().isEmpty() ||
                tableCombo.getValue().trim().isEmpty()
        ) {
            showAlert("Vul alle velden in!");
        }
        else {
            ArrayList<String> value = new ArrayList<>();
            ArrayList<Attribute> attributes = new ArrayList<>();
            AttributeBuilderInterface attributeBuilder = new AttributeBuilder();
            attributeBuilder.setEntity(tableCombo.getSelectionModel().getSelectedItem().toString());
            attributeBuilder.setValue(value);
            attributeBuilder.setName(inputField.getText());
            attributes.add(attributeBuilder.build());
            
            Rule rule = new Rule(attributes,"AOTH", "Attribute Other Rule", 21, "", null, "GENERATED");
            int ruleId = rule.save();

            TransportRule transportRule = new TransportRule(ruleId, "generate");
            new Client("localhost",5000,transportRule,this);
            currentId = ruleId;

        }
    }

    public void set(){
        TransportRule transportRule = new TransportRule(currentId, "set");
        new Client("localhost", 5000, transportRule, this);
        showAlert("Rule met id: " + currentId + " set in database" );
        cancel();
    }

    public void cancel(){
        dataBaseCombo.getSelectionModel().clearSelection();
        tableCombo.getSelectionModel().clearSelection();
        inputField.clear();
    }

    public void showAlert(String content){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void setGeneratedPreviewArea(String preview){
        previewArea.setText(preview);
    }

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {

        dataBaseCombo.getItems().setAll("Generic_Database");
        TargetDatabaseDAO targetDatabase =  TargetDatabaseDAOOracleImpl.getInstance();
        tableCombo.getItems().setAll(targetDatabase.getTables());


//        inputField.textProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//                    inputField.setText(oldValue);
//
//            }
      //  });
    }
}
