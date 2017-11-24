package controller;

import controller.ViewManager.ViewManagerException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import model.DatabaseModel;
import model.RawItem;

import java.io.IOException;
import java.util.ArrayList;

public class InventoryController extends Controller
{
    @FXML
    private TableView<RawItem> tableviewInventory;

    @FXML
    private TableColumn<RawItem, String> colIngredients;

    @FXML
    private TableColumn<RawItem, Integer> colNumber;

    @FXML
    private Button buttonBack, greenBut, redBut;

    public InventoryController() throws IOException
    {
        initialize(this, "/view/inventory", "/view/inventory");
    }

    @Override
    public void load() throws ViewManagerException
    {
        if(checkInitialLoad(getClass().getSimpleName()))
        {
            colIngredients.setCellValueFactory(new PropertyValueFactory<>("name"));
            colNumber.setCellValueFactory(new PropertyValueFactory<>("quantity"));

            loadRawItems();

            greenBut.addEventHandler(ActionEvent.ACTION, e ->
            {
                RawItem selectedItem = tableviewInventory.getSelectionModel().getSelectedItem();
                if(selectedItem != null)
                {
                    selectedItem.setQuantity(selectedItem.getQuantity() + 1);
                    tableviewInventory.refresh();
                }
            });

            redBut.addEventHandler(ActionEvent.ACTION, e ->
            {
                RawItem selectedItem = tableviewInventory.getSelectionModel().getSelectedItem();
                if(selectedItem != null)
                {
                    selectedItem.setQuantity(selectedItem.getQuantity() - 1);
                    tableviewInventory.refresh();
                }
            });

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
        ObservableList<RawItem> data = FXCollections.observableArrayList();
        data.addAll(rawItemList);

        tableviewInventory.setItems(data);
    }
}
