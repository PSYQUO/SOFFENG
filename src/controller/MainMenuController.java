package controller;

import controller.viewmanager.ViewManagerException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class MainMenuController extends Controller
{
    @FXML
    private Button buttonNewOrder, buttonInventory, buttonSettings, buttonFiles, buttonAnalytics;

    public MainMenuController(String fxmlpath, String csspath) throws IOException
    {
        super(fxmlpath, csspath);
    }

    @Override
    public void load() throws ViewManagerException
    {
        if(isFirstLoad())
        {
            buttonNewOrder.addEventHandler(ActionEvent.ACTION, e ->
                    viewManager.switchViews("NewOrderController"));

            buttonInventory.addEventHandler(ActionEvent.ACTION, e ->
                    viewManager.switchViews("InventoryController"));

            buttonSettings.addEventHandler(ActionEvent.ACTION, e ->
                    viewManager.switchViews("SettingsController"));

            buttonFiles.addEventHandler(ActionEvent.ACTION, e ->
                    viewManager.switchViews("FilesController"));

            buttonAnalytics.addEventHandler(ActionEvent.ACTION, e ->
                    viewManager.switchViews("AnalyticsController"));
        }
    }

    @Override
    public void clear()
    {

    }
}
