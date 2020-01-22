package controller;

import domain.connection.Client;
import domain.connection.TransportRule;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import domain.businessRule.Facade;
import domain.businessRule.FacadeInterface;
import domain.businessRule.Table;
import javafx.fxml.Initializable;

public class AttributeCompareRuleController implements Initializable {

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		FacadeInterface facade = new Facade();
		List<Table> tables = facade.getTables();
		for (Table table : tables) {
			table.getName();
			// als de table is gekozen kunnen de column worden opgehaalt
			for (String columnaam : table.getColumns()) {
				System.out.println(columnaam);
			}


    @FXML public ComboBox<String> dataBaseCombo = new ComboBox();
    @FXML public ComboBox<String> tableCombo = new ComboBox<>();
    @FXML public ComboBox<String> columnCombo = new ComboBox<>();
    @FXML public ComboBox<String> operatorCombo = new ComboBox<>();

    @FXML public TextField valueTextField;

    @FXML public Button generateButton;
    @FXML public Button setButton;
    @FXML public Button cancelButton;



    public void generate(){
        TransportRule transportRule = new TransportRule("CREATE", 1);
        new Client("localhost",5000,transportRule);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dataBaseCombo.getItems().setAll("Generic_Database");
        tableCombo.getItems().setAll("Generic_Table");
        columnCombo.getItems().setAll("Generic_Column");
        operatorCombo.getItems().setAll("<",">","==","!=");


    }
}
