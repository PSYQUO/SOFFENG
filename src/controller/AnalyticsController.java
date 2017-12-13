package controller;

import controller.viewmanager.ViewManagerException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class AnalyticsController extends Controller
{
    @FXML
    private Button buttonBackMenu;

    public AnalyticsController(String fxmlpath, String csspath) throws IOException
    {
        super(fxmlpath, csspath);
    }

    @Override
    public void load() throws ViewManagerException
    {
        if(isFirstLoad())
        {
            buttonBackMenu.addEventHandler(ActionEvent.ACTION, e ->
                viewManager.switchViews("MainMenuController"));
        }
    }

    @Override
    public void clear()
    {

    }
}
