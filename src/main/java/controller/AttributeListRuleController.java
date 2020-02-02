package controller;

import database.TargetDatabase.TargetDatabaseDAO;
import database.TargetDatabase.TargetDatabaseDAOOracleImpl;
import database.ToolDatabase.ToolDatabaseDao;
import database.ToolDatabase.ToolDatabaseDaoOracleImpl;
import domain.connection.Client;
import domain.connection.TransportRule;
import domain.definer.Attribute;
import domain.definer.AttributeBuilder;
import domain.definer.AttributeBuilderInterface;
import domain.definer.Rule;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AttributeListRuleController implements Initializable ,Controller {
    private int currentId = 0;

    private static final AttributeRangeRuleController INSTANCE = new AttributeRangeRuleController();

    public static AttributeRangeRuleController getInstance(){
        if(INSTANCE == null){
            return new AttributeRangeRuleController();
        }
        return INSTANCE;
    }


    @FXML public ComboBox<String> dataBaseCombo = new ComboBox();
    @FXML public ComboBox<String> tableCombo = new ComboBox<>();
    @FXML public ComboBox<String> columnCombo = new ComboBox<>();
    @FXML public ComboBox<String> operatorCombo = new ComboBox<>();

    @FXML public TextField value;

    @FXML public Button generateButton;
    @FXML public Button setButton;
    @FXML public Button cancelButton;

    @FXML public TextArea previewArea;

    @FXML public ListView listView;

    @FXML public ComboBox<String> updateBox = new ComboBox<>();

    ObservableList<String> valuesObservableList = FXCollections.observableArrayList();

    @SuppressWarnings("Duplicates")
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
            ArrayList<String> values = new ArrayList<String>(valuesObservableList);
            ArrayList<Attribute> attributes = new ArrayList<>();
            AttributeBuilderInterface attributeBuilder = new AttributeBuilder();
            attributeBuilder.setEntity(tableCombo.getValue());
            attributeBuilder.setValue(values);
            attributeBuilder.setName(columnCombo.getValue());
            attributes.add(attributeBuilder.build());

            Rule rule = new Rule(attributes,"ALIS", "Attribute List rule", 21, "", operatorCombo.getSelectionModel().getSelectedItem(), "GENERATED");

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
        value.clear();
        listView.getItems().clear();
    }
    public void addToList(){
        operatorCombo.setDisable(true);
        valuesObservableList.add(value.getText());
        listView.getItems().clear();
        listView.getItems().addAll(valuesObservableList);
        value.clear();


    }

    @SuppressWarnings("Duplicates")
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

    @SuppressWarnings("Duplicates")
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        updateBox.getItems().setAll(Rule.getIdsOfSetTriggersByRuleCode("ALIS"));

        dataBaseCombo.getItems().setAll("Generic_Database");

        TargetDatabaseDAO targetDatabase =  TargetDatabaseDAOOracleImpl.getInstance();

        tableCombo.getItems().setAll(targetDatabase.getTables());

        tableCombo.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                columnCombo.getItems().setAll(targetDatabase.getAllColumns(tableCombo.getValue()));
            }
        });

        value.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                    value.setText(oldValue);
                }
            }
        });

        //operators
        operatorCombo.getItems().add("FOUND");
        operatorCombo.getItems().add("NOT FOUND");




    }
}
