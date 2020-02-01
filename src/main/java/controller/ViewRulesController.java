package controller;

import database.ToolDatabase.ToolDatabaseDaoOracleImpl;
import domain.connection.Client;
import domain.connection.TransportRule;
import domain.definer.Rule;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewRulesController implements Initializable, Controller {

    ToolDatabaseDaoOracleImpl toolDatabaseDaoOracle = ToolDatabaseDaoOracleImpl.getInstance();

    public ObservableList<Rule> ruleObservableList = FXCollections.observableArrayList(toolDatabaseDaoOracle.getAllRules());

    @FXML
    public TableView<Rule> ruleTableView;
    @FXML public TableColumn<Rule, Integer> codeColumn;
    @FXML public TableColumn<Rule, String> descriptionColumn;
    @FXML public TableColumn<Rule, Integer> categoryIdColumn;
    @FXML public TableColumn<Rule, String> sqlCodeColumn;
    @FXML public TableColumn<Rule, Integer> databaseIdColumn;
    @FXML public TableColumn<Rule, Integer> statusColumn;

    @FXML public ComboBox columnCombo;
    @FXML public TextField queryTextField;




    public void update(){
        new Client("localhost",5000, (
                new TransportRule(ruleTableView.getSelectionModel().getSelectedItem().getDbId(),"update")),this);

    }
    public void remove(){
        new Client("localhost", 5000,
                new TransportRule(ruleTableView.getSelectionModel().getSelectedItem().getDbId(), "remove"),this);
        ruleTableView.setItems(FXCollections.observableArrayList(toolDatabaseDaoOracle.getAllRules()));

    }
    public void search() {
        if (columnCombo.getValue() != null || columnCombo.getSelectionModel().getSelectedItem() != null) {
            if(columnCombo.getSelectionModel().getSelectedItem().equals("Code")) {
                ruleTableView.setItems(FXCollections.observableArrayList(toolDatabaseDaoOracle.getRulesByCode(queryTextField.getText())));
            }
            else if(columnCombo.getSelectionModel().getSelectedItem().equals("Entity")){
                ruleTableView.setItems(FXCollections.observableArrayList(toolDatabaseDaoOracle.getRulesByEntity(queryTextField.getText())));
            }
        }

    }

    public void showAlert(String content){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }






    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        columnCombo.getItems().add("Code");
        columnCombo.getItems().add("Entity");

        codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        categoryIdColumn.setCellValueFactory(new PropertyValueFactory<>("categoryId"));
        sqlCodeColumn.setCellValueFactory(new PropertyValueFactory<>("SQLCode"));
        databaseIdColumn.setCellValueFactory(new PropertyValueFactory<>("dbId"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        ruleTableView.setItems(ruleObservableList);

    }
}

