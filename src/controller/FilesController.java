package controller;

import controller.viewmanager.ViewManagerException;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.food.Consumable;
import model.DatabaseModel;
import model.User;
import model.transaction.Transaction;

import java.io.IOException;

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


    // Meals Table component declaration
    @FXML
    private TableView<Consumable> tableviewMeals;

    @FXML
    private TableColumn<Consumable, Integer> colMealID;

    @FXML
    private TableColumn<Consumable, String> colMealName, colMealCodename, colMealCategory;

    @FXML
    private TableColumn<Consumable, Double> colMealPrice;

    private DatabaseModel dbm;

    public FilesController(String fxmlpath, String csspath) throws IOException
    {
        super(fxmlpath, csspath);
        dbm = new DatabaseModel();
    }

    @Override
    public void load() throws ViewManagerException
    {
        if(isFirstLoad())
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
        tableviewMeals.setItems(FXCollections.observableArrayList(dbm.getConsumables()));
    }

    @Override
    public void clear()
    {
        tableviewAccounts.getItems().clear();
        tableviewTransactions.getItems().clear();
        tableviewMeals.getItems().clear();
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

        colMealCategory.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getCategory().getCategoryName()));
        colMealCodename.setCellValueFactory(new PropertyValueFactory<>("codeName"));
        colMealID.setCellValueFactory(new PropertyValueFactory<>("consumableID"));
        colMealName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colMealPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
}
