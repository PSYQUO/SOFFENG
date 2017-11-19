package controller;

import controller.ViewManager.ViewManagerException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.*;
import model.transaction.Transaction;
import model.transaction.TransactionBuilder;

import javafx.util.Callback;
import java.io.IOException;
import java.util.ArrayList;

public class FilesController extends Controller
{
    public FilesController() throws IOException
    {
        initialize(this, "/view/files", "/view/files");
    }

    @Override
    public void load() throws ViewManagerException
    {

    }

    @Override
    public void clear()
    {

    }

    private void loadUsers()
    {
        DatabaseModel dbm = new DatabaseModel();
        ArrayList<User> userList = dbm.getUsers();

        ObservableList<ObservableList<String>> columnData = FXCollections.observableArrayList();

        for(User u : userList)
        {
            ObservableList<String> row = FXCollections.observableArrayList();
            row.add(u.getUserID() + "");
            row.add(u.getusername());
            row.add(u.getUserLoginName());
            row.add(u.getPassword());
            row.add(u.getRole().getRoleName());
            

            columnData.add(row);
        }

        //tableviewInventory.setItems(columnData);
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
            row.add(t.getTransactionDate() + "");
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
