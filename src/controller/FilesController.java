package controller;

import controller.ViewManager.ViewManagerException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import model.Consumable;
import model.DatabaseModel;
import model.Role;
import model.User;
import model.transaction.Transaction;

import java.io.IOException;
import java.util.ArrayList;

public class FilesController extends Controller
{
    @FXML
    private Button buttonBack;

    @FXML
    private TableView<User> tableviewAccounts;

    @FXML
    private TableColumn<User, String>  colAcctUsername, colAcctName, colAcctRole;

    @FXML
    private TableColumn<User, Integer> colAcctID;

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
            setTableColumnActions();

            buttonBack.addEventHandler(ActionEvent.ACTION, e ->
            {
                viewManager.switchViews("MainMenuController");
                clear();
            });
        }

        loadUsers();
    }

    @Override
    public void clear()
    {
        tableviewAccounts.getItems().clear();
    }

    private void setTableColumnActions()
    {
        colAcctUsername.setCellValueFactory(new PropertyValueFactory<>("userID"));
        colAcctName.setCellValueFactory(new PropertyValueFactory<>("username"));
        colAcctUsername.setCellValueFactory(new PropertyValueFactory<>("userLoginName"));
        colAcctRole.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getRole().getRoleName()));
    }

    private void loadUsers()
    {
        dbm = new DatabaseModel();
        ArrayList<User> userList = dbm.getUsers();
        ObservableList<User> data = FXCollections.observableArrayList(userList);
        tableviewAccounts.getItems().addAll(data);
    }

    private void loadTransactions()
    {
        DatabaseModel dbm = new DatabaseModel();
        ArrayList<Transaction> transList = dbm.getTransactions();

        ObservableList<ObservableList<String>> columnData = FXCollections.observableArrayList();

        for(Transaction t : transList)
        {
            ObservableList<String> row = FXCollections.observableArrayList();
            row.add(t.getTransactionID() + "");
            row.add(t.getDate() + "");
            row.add(t.getCashier().getUserLoginName());
            row.add(t.getMode().toString());
            row.add(t.getCashReceived() + "");
            row.add(t.getChange() + "");
            row.add(t.getDiscount() + "");
            row.add(t.getSubTotal() + "");
            row.add(t.getTotal() + "");

            columnData.add(row);
        }

        //tableviewInventory.setItems(columnData);
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
