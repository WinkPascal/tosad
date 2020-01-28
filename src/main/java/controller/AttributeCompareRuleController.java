package controller;

import domain.connection.Client;
import domain.connection.GeneratorClient;
import domain.connection.TransportRule;
import domain.definer.Attribute;
import domain.definer.Rule;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import domain.businessRule.Facade;
import domain.businessRule.FacadeInterface;
import domain.businessRule.Table;
import javafx.fxml.Initializable;

public class AttributeCompareRuleController implements Initializable {

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

            showAlert();

        }
        else {
            ArrayList<String> values = new ArrayList<>();
            ArrayList<Attribute> attributes = new ArrayList<>();
            values.add(valueTextField.getText());
            attributes.add((new Attribute(columnCombo.getValue(), values, tableCombo.getValue())));
            Rule rule = new Rule(attributes,"ACMP", "Attribute Compare rule", 0, "", operatorCombo.getValue(), "GENERATED");
            int ruleId = rule.save();


            TransportRule transportRule = new TransportRule(ruleId, "generate");
            new GeneratorClient("localhost",5000,transportRule,this);
        }
    }
    public void showAlert(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText(null);
        alert.setContentText("Vul alle velden in!");


        alert.showAndWait();
    }

    public void setGeneratedPreviewArea(String preview){
        previewArea.setText(preview);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dataBaseCombo.getItems().setAll("Generic_Database");
        tableCombo.getItems().setAll("Generic_Table");
        columnCombo.getItems().setAll("Generic_Column");
        operatorCombo.getItems().setAll("<",">","==","!=");

//        FacadeInterface facade = new Facade();
//        List<Table> tables = facade.getTables();
//        for (Table table : tables) {
//            table.getName();
//            // als de table is gekozen kunnen de column worden opgehaalt
//            for (String columnaam : table.getColumns()) {
//                System.out.println(columnaam);
//            }
//        }
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
