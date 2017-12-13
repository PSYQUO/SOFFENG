package controller;

import controller.viewmanager.ViewManagerException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import model.DatabaseModel;
import model.food.RawItem;

import javax.swing.*;
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
    private BorderPane borderpaneIncoming, borderpaneOutgoing;

    @FXML
    private Label labelNameIncoming, labelNameOutgoing;

    @FXML
    private Spinner spinnerQuantityIncoming, spinnerQuantityOutgoing;

    @FXML
    private Button buttonBack, greenBut, redBut, buttonCancelIncoming, buttonOKIncoming,
            buttonCancelOutgoing, buttonOKOutgoing;

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
                if(!tableviewInventory.getSelectionModel().isEmpty())
                {
                    RawItem selectgedItem = tableviewInventory.getSelectionModel().getSelectedItem();
                    labelNameIncoming.setText("Item: " + selectgedItem.getName());

                    changePaneState("TO_IN");
                }
            });

            redBut.addEventHandler(ActionEvent.ACTION, e ->
            {
                if(!tableviewInventory.getSelectionModel().isEmpty())
                {
                    RawItem selectedItem = tableviewInventory.getSelectionModel().getSelectedItem();
                    labelNameOutgoing.setText("Item: " + selectedItem.getName());

                    changePaneState("TO_OUT");
                }
            });

            buttonCancelIncoming.addEventHandler(ActionEvent.ACTION, e ->
                    changePaneState("TO_TABLE"));

            buttonCancelOutgoing.addEventHandler(ActionEvent.ACTION, e ->
                    changePaneState("TO_TABLE"));

            buttonOKIncoming.addEventHandler(ActionEvent.ACTION, e ->
            {
                String text = spinnerQuantityIncoming.getEditor().getText().trim();
                if(!text.equals(""))
                {
                    int qty = Integer.parseInt(text);

                    RawItem selectedItem = tableviewInventory.getSelectionModel().getSelectedItem();
                    selectedItem.setQuantity(selectedItem.getQuantity() + qty);

                    dbm.updateRawItem(selectedItem);
                    tableviewInventory.refresh();
                    changePaneState("TO_TABLE");
                }
            });

            buttonOKOutgoing.addEventHandler(ActionEvent.ACTION, e ->
            {
                String text = spinnerQuantityOutgoing.getEditor().getText().trim();
                if(!text.equals(""))
                {
                    int qty = Integer.parseInt(text);

                    RawItem selectedItem = tableviewInventory.getSelectionModel().getSelectedItem();
                    selectedItem.setQuantity(selectedItem.getQuantity() - qty);

                    dbm.updateRawItem(selectedItem);
                    tableviewInventory.refresh();
                    changePaneState("TO_TABLE");
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
        changePaneState("TO_TABLE");
    }

    private void changePaneState(String state)
    {
        switch(state)
        {
            case "TO_IN":
                borderpaneOutgoing.setVisible(false);
                borderpaneIncoming.setVisible(true);
                borderpaneIncoming.setDisable(false);
                tableviewInventory.setDisable(true);
                break;
            case"TO_OUT":
                borderpaneIncoming.setVisible(false);
                borderpaneOutgoing.setVisible(true);
                borderpaneOutgoing.setDisable(false);
                tableviewInventory.setDisable(true);
                break;
            case "TO_TABLE":
                borderpaneIncoming.setVisible(false);
                borderpaneIncoming.setDisable(true);
                borderpaneOutgoing.setVisible(false);
                borderpaneOutgoing.setDisable(true);
                tableviewInventory.setDisable(false);
                break;
        }
    }
}
