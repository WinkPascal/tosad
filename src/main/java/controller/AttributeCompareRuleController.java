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
import java.util.List;
import java.util.ResourceBundle;


public class AttributeCompareRuleController implements Initializable, Controller {

    private int currentId = 0;

    private static final AttributeCompareRuleController INSTANCE = new AttributeCompareRuleController();

    public static AttributeCompareRuleController getInstance(){
        if(INSTANCE == null){
            return new AttributeCompareRuleController();
        }
        return INSTANCE;
    }

    @FXML public ComboBox<String> dataBaseCombo = new ComboBox();
    @FXML public ComboBox<String> tableCombo = new ComboBox<>();
    @FXML public ComboBox<String> columnCombo = new ComboBox<>();
    @FXML public ComboBox<String> operatorCombo = new ComboBox<>();

    @FXML public TextField valueTextField;

    @FXML public Button generateButton;
    @FXML public Button setButton;
    @FXML public Button cancelButton;

    @FXML public TextArea previewArea;

    @FXML public ComboBox<String> updateBox = new ComboBox<>();

    public void generate() throws InterruptedException {
        if( dataBaseCombo.getValue() == null ||
            tableCombo.getValue() == null ||
            columnCombo.getValue() == null ||
            operatorCombo.getValue() == null ||
            dataBaseCombo.getValue().trim().isEmpty() ||
            tableCombo.getValue().trim().isEmpty() ||
            columnCombo.getValue().trim().isEmpty() ||
            operatorCombo.getValue().trim().isEmpty()
        ) {
            showAlert("Vul alle velden in!");
        }
        else {
            ArrayList<String> values = new ArrayList<>();
            ArrayList<Attribute> attributes = new ArrayList<>();

            values.add(valueTextField.getText());

            AttributeBuilderInterface attributeBuilder = new AttributeBuilder();
            attributeBuilder.setName(columnCombo.getValue());
            attributeBuilder.setValue(values);
            attributeBuilder.setEntity(tableCombo.getValue());
            attributes.add(attributeBuilder.build());

            Rule rule = new Rule(attributes,"ACMP", "Attribute Compare rule", 21, "", operatorCombo.getSelectionModel().getSelectedItem().toString(), "GENERATED");

            int ruleId = 0;
            String updateBoxValue = updateBox.getValue();
            if(updateBoxValue != null){
                System.out.println("update");
                ruleId = rule.update(Integer.parseInt(updateBoxValue));
                TransportRule transportRule = new TransportRule(ruleId, "update");
                new Client("localhost",5000,transportRule,this);
            } else{
                ruleId = rule.save();
                TransportRule transportRule = new TransportRule(ruleId, "generate");
                new Client("localhost",5000,transportRule,this);
            }
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
        columnCombo.getSelectionModel().clearSelection();
        operatorCombo.getSelectionModel().clearSelection();
        valueTextField.clear();
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        dataBaseCombo.getItems().setAll("Generic_Database");

        updateBox.getItems().setAll(Rule.getIdsOfSetTriggersByRuleCode("ACMP"));

        TargetDatabaseDAO targetDatabase =  TargetDatabaseDAOOracleImpl.getInstance();

        tableCombo.getItems().setAll(targetDatabase.getTables());

        tableCombo.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                columnCombo.getItems().setAll(targetDatabase.getAllColumns(tableCombo.getValue()));
            }
        });

        //operators
        operatorCombo.getItems().add("==");
        operatorCombo.getItems().add("!=");
        operatorCombo.getItems().add(">");
        operatorCombo.getItems().add("<");

        valueTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                    valueTextField.setText(oldValue);
                }
            }
        });
    }
}
