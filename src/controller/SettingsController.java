package controller;

import controller.ViewManager.ViewManagerException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class SettingsController extends Controller
{
    @FXML
    private Button buttonMainSettingsClose, buttonPasswordSettingsClose, buttonBackupSettingsClose;

    public SettingsController() throws IOException
    {
        initialize(this, "/view/settings");
    }

    @Override
    public void load() throws ViewManagerException
    {
        if(checkInitialLoad(getClass().getSimpleName()))
        {
            buttonMainSettingsClose.addEventHandler(ActionEvent.ACTION, e ->
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
}
