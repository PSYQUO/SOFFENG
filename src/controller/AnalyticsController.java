package controller;

import controller.ViewManager.ViewManagerException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class AnalyticsController extends Controller
{
    @FXML
    private Button buttonBack;

    public AnalyticsController() throws IOException
    {
        initialize(this, "/view/analytics-menu.fxml", "/view/analytics-menu.css");
    }

    @Override
    public void load() throws ViewManagerException
    {
        if(checkInitialLoad(getClass().getSimpleName()))
        {
            buttonBack.addEventHandler(ActionEvent.ACTION, e ->
                viewManager.switchViews("MainMenuController"));
        }
    }

    @Override
    public void clear()
    {

    }
}
