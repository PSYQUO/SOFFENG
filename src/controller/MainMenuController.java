package controller;

import controller.ViewManager.ViewManagerException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class MainMenuController extends Controller
{
    @FXML
    private Button buttonNewOrder, buttonInventory, buttonSettings, buttonFiles, buttonAnalytics;

    public MainMenuController() throws IOException
    {
        initialize(this, "/view/main-menu.fxml", "/view/main-menu.css");
    }

    @Override
    public void load() throws ViewManagerException
    {
        if(checkInitialLoad(getClass().getSimpleName()))
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
