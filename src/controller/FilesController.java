package controller;

import controller.ViewManager.ViewManagerException;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Consumable;
import model.DatabaseModel;
import model.User;
import model.transaction.Transaction;

import java.io.IOException;
import java.util.ArrayList;

public class FilesController extends Controller
{
    @FXML
    private Button buttonBack;

    // Accounts Table component declaration
    @FXML
    private TableView<User> tableviewAccounts;

    @FXML
    private TableColumn<User, String>  colAcctUsername, colAcctName, colAcctRole;

    @FXML
    private TableColumn<User, Integer> colAcctID;

    // Transaction Table component declaration
    @FXML
    private TableView<Transaction> tableviewTransactions;

    @FXML
    private TableColumn<Transaction, Integer> colTransID, colTransCustNo;

    @FXML
    private TableColumn<Transaction, String> colTransDateTime, colTransCashier, colTransType;

    @FXML
    private TableColumn<Transaction, Double> colTransCash, colTransChange, colTransSubtotal, colTransSeniorDiscount, colTransTotal;

    private DatabaseModel dbm;

    public FilesController() throws IOException
    {
        initialize(this, "/view/files", "/view/files");
    }

    @Override
    public void load() throws ViewManagerException
    {
        if(checkInitialLoad(getClass().getSimpleName()))
        {
            setTablePropertiesAndItems();

            buttonBack.addEventHandler(ActionEvent.ACTION, e ->
            {
                viewManager.switchViews("MainMenuController");
                clear();
            });
        }

        tableviewAccounts.setItems(FXCollections.observableArrayList(dbm.getUsers()));
        tableviewTransactions.setItems(FXCollections.observableArrayList(dbm.getTransactions()));
    }

    @Override
    public void clear()
    {
        tableviewAccounts.getItems().clear();
    }

    private void setTablePropertiesAndItems()
    {
        colAcctUsername.setCellValueFactory(new PropertyValueFactory<>("userID"));
        colAcctName.setCellValueFactory(new PropertyValueFactory<>("username"));
        colAcctUsername.setCellValueFactory(new PropertyValueFactory<>("userLoginName"));
        colAcctRole.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getRole().getRoleName()));

        colTransCash.setCellValueFactory(new PropertyValueFactory<>("cashReceived"));
        colTransCashier.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getCashier().getUsername()));
        colTransChange.setCellValueFactory(new PropertyValueFactory<>("change"));
        colTransCustNo.setCellValueFactory(new PropertyValueFactory<>("customerNo"));
        colTransID.setCellValueFactory(new PropertyValueFactory<>("transactionID"));
        colTransSeniorDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        colTransSubtotal.setCellValueFactory(new PropertyValueFactory<>("subTotal"));
        colTransTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colTransType.setCellValueFactory(new PropertyValueFactory<>("mode"));
    }

    private void loadConsumables()
    {
        DatabaseModel dbm = new DatabaseModel();
        ArrayList<Consumable> consList = dbm.getConsumables();

        ObservableList<ObservableList<String>> columnData = FXCollections.observableArrayList();

        for(Consumable c : consList)
        {
            ObservableList<String> row = FXCollections.observableArrayList();
            row.add(c.getConsumableID() + "");
            row.add(c.getName());
            row.add(c.getCodeName());
            row.add(c.getCategory().getCategoryName());
            row.add(c.getPrice() + "");
            row.add(c.getMeal().getMealID() + "");

            columnData.add(row);
        }

        //tableviewInventory.setItems(columnData);
    }
}
