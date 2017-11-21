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

import javafx.util.Callback;
import java.io.IOException;
import java.util.ArrayList;

public class InventoryController extends Controller
{
    @FXML
    private TableView tableviewInventory;

    @FXML
    private TableColumn colIngredients, colNumber;

    @FXML
    private Button buttonBack;

    public InventoryController() throws IOException
    {
        initialize(this, "/view/inventory", "/view/inventory");
    }

    @Override
    public void load() throws ViewManagerException
    {
        if(checkInitialLoad(getClass().getSimpleName()))
        {
            colIngredients.setCellValueFactory(
                    (Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>)
                            param -> new SimpleStringProperty(param.getValue().get(0).toString()));

            colNumber.setCellValueFactory(
                    (Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>)
                            param -> new SimpleStringProperty(param.getValue().get(1).toString()));

            loadRawItems();

            buttonBack.addEventHandler(ActionEvent.ACTION, e ->
            {
                viewManager.switchViews("MainMenuController");
                clear();
            });
        }
    }

    @Override
    public void clear()
    {

    }

    private void loadRawItems()
    {
        DatabaseModel dbm = new DatabaseModel();
        ArrayList<RawItem> rawItemList = dbm.getRawItems();

        ObservableList<ObservableList<String>> columnData = FXCollections.observableArrayList();

        for(RawItem r : rawItemList)
        {
            ObservableList<String> row = FXCollections.observableArrayList();
            row.add(r.getName());
            row.add(r.getQuantity() + "");

            columnData.add(row);
        }

        tableviewInventory.setItems(columnData);
    }
}
