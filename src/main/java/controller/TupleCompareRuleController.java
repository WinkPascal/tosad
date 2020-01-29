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

public class TupleCompareRuleController implements Initializable , Controller{
    private int currentId = 0;

    private static final TupleCompareRuleController INSTANCE = new TupleCompareRuleController();

    public static TupleCompareRuleController getInstance(){
        if(INSTANCE == null){
            return new TupleCompareRuleController();
        }
        return INSTANCE;
    }

    @FXML
    public ComboBox<String> dataBaseCombo = new ComboBox();
    @FXML public ComboBox<String> tableCombo = new ComboBox<>();
    @FXML public ComboBox<String> column1Combo = new ComboBox<>();
    @FXML public ComboBox<String> column2Combo = new ComboBox<>();
    @FXML public ComboBox<String> operatorCombo = new ComboBox<>();


    @FXML public Button generateButton;
    @FXML public Button setButton;
    @FXML public Button cancelButton;

    @FXML public TextArea previewArea;



    public void generate() throws InterruptedException {
        if( dataBaseCombo.getValue() == null ||
                tableCombo.getValue() == null ||
                column1Combo.getValue() == null ||
                column2Combo.getValue() == null ||
                operatorCombo.getValue() == null ||
                dataBaseCombo.getValue().trim().isEmpty() ||
                tableCombo.getValue().trim().isEmpty() ||
                column1Combo.getValue().trim().isEmpty() ||
                column2Combo.getValue().trim().isEmpty() ||
                operatorCombo.getValue().trim().isEmpty()
        ) {

            showAlert("Vul alle velden in!");

        }
        else {
            ArrayList<Attribute> attributes = new ArrayList<>();
            AttributeBuilderInterface attributeBuilder = new AttributeBuilder();
            attributeBuilder.setEntity(tableCombo.getValue());
            attributeBuilder.setName(column2Combo.getValue());
            attributes.add(attributeBuilder.build());

            AttributeBuilderInterface attributeBuilder1 = new AttributeBuilder();
            attributeBuilder1.setEntity(tableCombo.getValue());
            attributeBuilder1.setName(column1Combo.getValue());
            attributes.add(attributeBuilder1.build());

            Rule rule = new Rule(attributes,"TCMR", "Tuple Compare Rule", 2, "", operatorCombo.getSelectionModel().getSelectedItem().toString(), "GENERATED");
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
        column1Combo.getSelectionModel().clearSelection();
        column2Combo.getSelectionModel().clearSelection();
        operatorCombo.getSelectionModel().clearSelection();
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

        tableCombo.getItems().setAll(targetDatabase.getTables());

        tableCombo.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                column1Combo.getItems().setAll(targetDatabase.getAllColumns(tableCombo.getValue()));
                column2Combo.getItems().setAll(targetDatabase.getAllColumns(tableCombo.getValue()));
            }
        });

        //operators
        operatorCombo.getItems().add("==");
        operatorCombo.getItems().add("!=");
        operatorCombo.getItems().add(">");
        operatorCombo.getItems().add("<");
    }

}
