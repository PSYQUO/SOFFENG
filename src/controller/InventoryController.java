package controller;

import controller.viewmanager.ViewManagerException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.DatabaseModel;
import model.food.RawItem;

import java.io.IOException;

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

    public InventoryController(String fxmlpath, String csspath) throws IOException
    {
        super(fxmlpath, csspath);
        dbm = new DatabaseModel();
    }

    @Override
    public void load() throws ViewManagerException
    {
        if(isFirstLoad())
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

        tableviewInventory.setItems(FXCollections.observableArrayList(dbm.getRawItems()));
    }

    @Override
    public void clear()
    {
        tableviewInventory.getItems().clear();
    }
}
