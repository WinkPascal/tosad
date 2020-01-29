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

public class InterEntityCompareRuleController implements Initializable, Controller {
    private int currentId = 0;

    private static final InterEntityCompareRuleController INSTANCE = new InterEntityCompareRuleController();

    public static InterEntityCompareRuleController getInstance(){
        if(INSTANCE == null){
            return new InterEntityCompareRuleController();
        }
        return INSTANCE;
    }

    @FXML public ComboBox<String> dataBaseCombo = new ComboBox();
    @FXML public ComboBox<String> table1Combo = new ComboBox<>();
    @FXML public ComboBox<String> column1Combo = new ComboBox<>();
    @FXML public ComboBox<String> table2Combo = new ComboBox<>();
    @FXML public ComboBox<String> column2Combo = new ComboBox<>();
    @FXML public ComboBox<String> operatorCombo = new ComboBox<>();

    @FXML public TextArea previewArea;

    @FXML public Button generateButton;
    @FXML public Button setButton;
    @FXML public Button cancelButton;


    public void generate() throws InterruptedException {
        if( dataBaseCombo.getValue() == null ||
                table1Combo.getValue() == null ||
                column1Combo.getValue() == null ||
                operatorCombo.getValue() == null ||
                table2Combo.getValue() == null ||
                column2Combo.getValue() == null ||
                dataBaseCombo.getValue().trim().isEmpty() ||
                table1Combo.getValue().trim().isEmpty() ||
                column1Combo.getValue().trim().isEmpty() ||
                operatorCombo.getValue().trim().isEmpty() ||
                table2Combo.getValue().trim().isEmpty() ||
                column2Combo.getValue().trim().isEmpty()
        ) {
            showAlert("Vul alle velden in!");
        }
        else {
            ArrayList<Attribute> attributes = new ArrayList<>();
            AttributeBuilderInterface attributeBuilder = new AttributeBuilder();
            attributeBuilder.setName(column2Combo.getValue());
            attributeBuilder.setEntity(table2Combo.getValue());
            attributes.add(attributeBuilder.build());

            AttributeBuilderInterface attributeBuilder2 = new AttributeBuilder();
            attributeBuilder2.setEntity(table1Combo.getValue());
            attributeBuilder2.setName(column1Combo.getValue());
            attributes.add(attributeBuilder2.build());

            Rule rule = new Rule(attributes,"ICMP", "Inter Entity Compare rule", 2, "", operatorCombo.getSelectionModel().getSelectedItem().toString(), "GENERATED");
            int ruleId = rule.save();
            System.out.println(operatorCombo.getValue());


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
        table1Combo.getSelectionModel().clearSelection();
        column1Combo.getSelectionModel().clearSelection();
        operatorCombo.getSelectionModel().clearSelection();
        table2Combo.getSelectionModel().clearSelection();
        column2Combo.getSelectionModel().clearSelection();
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

        TargetDatabaseDAO targetDatabase =  TargetDatabaseDAOOracleImpl.getInstance();

        table1Combo.getItems().setAll(targetDatabase.getTables());


        table1Combo.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                column1Combo.getItems().setAll(targetDatabase.getAllColumns(table1Combo.getValue()));
            }
        });

        table2Combo.getItems().setAll(targetDatabase.getTables());
        table2Combo.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                column2Combo.getItems().setAll(targetDatabase.getAllColumns(table2Combo.getValue()));
            };
        });

        //operators
        operatorCombo.getItems().add("==");
        operatorCombo.getItems().add("!=");
        operatorCombo.getItems().add(">");
        operatorCombo.getItems().add("<");
    }
}
