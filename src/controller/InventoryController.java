package controller;

import controller.ViewManager.ViewManagerException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.DatabaseModel;
import model.RawItem;

import javax.security.auth.callback.Callback;
import java.io.IOException;
import java.util.ArrayList;

public class InventoryController extends Controller
{
    @FXML
    private TableView tableviewInvetory;

    @FXML
    private TableColumn tableInventory, colNumber;

    public InventoryController() throws IOException
    {
        initialize(this, "/view/inventory", true);
    }

    @Override
    public void load() throws ViewManagerException
    {
        if(checkInitialLoad(getClass().getSimpleName()))
        {
            loadRawItems();
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

        ObservableList<String> colRawItemName = FXCollections.observableArrayList();
        ObservableList<String> colQuantity = FXCollections.observableArrayList();

        tableInventory.setCellValueFactory(
                (Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>)
                        param -> new SimpleStringProperty(param.getValue().get(0).toString())
        );
    }
}
