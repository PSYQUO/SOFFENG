package controller;

import controller.ViewManager.ViewManagerException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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

    private DatabaseModel dbm;

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

            greenBut.addEventHandler(ActionEvent.ACTION, e ->
            {
                RawItem selectedItem = tableviewInventory.getSelectionModel().getSelectedItem();
                if(selectedItem != null)
                {
                    selectedItem.setQuantity(selectedItem.getQuantity() + 1);
                    tableviewInventory.refresh();
                    /**
                     * This is currently querying the DB every button press.
                     * TODO: Once the dialog gets implemented this will change
                     */
                    dbm.updateRawItem(selectedItem);
                }
            });

            redBut.addEventHandler(ActionEvent.ACTION, e ->
            {
                RawItem selectedItem = tableviewInventory.getSelectionModel().getSelectedItem();
                if(selectedItem != null)
                {
                    selectedItem.setQuantity(selectedItem.getQuantity() - 1);
                    tableviewInventory.refresh();
                    /**
                     * This is currently querying the DB every button press.
                     * TODO: Once the dialog gets implemented this will change
                     */
                    dbm.updateRawItem(selectedItem);
                }
            });

            buttonBack.addEventHandler(ActionEvent.ACTION, e ->
            {
                viewManager.switchViews("MainMenuController");
                clear();
            });
        }

        loadRawItems();
    }

    @Override
    public void clear()
    {
        tableviewInventory.getItems().clear();
    }

    private void loadRawItems()
    {
        dbm = new DatabaseModel();
        ArrayList<RawItem> rawItemList = dbm.getRawItems();
        ObservableList<RawItem> data = FXCollections.observableArrayList(rawItemList);

        tableviewInventory.setItems(data);
    }
}
