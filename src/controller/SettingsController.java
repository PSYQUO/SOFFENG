package controller;

import controller.ViewManager.ViewManagerException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class SettingsController extends Controller
{
    @FXML
    private Button buttonMainSettingsClose, buttonPasswordSettingsClose, buttonBackupSettingsClose,
                   buttonChangePassword, buttonSetBackup;

    @FXML
    private AnchorPane anchorpaneMainSettings, anchorpanePasswordSettings, anchorpaneBackupSettings;

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

            buttonChangePassword.addEventHandler(ActionEvent.ACTION, e ->
            {
                anchorpaneMainSettings.setVisible(false);
                anchorpanePasswordSettings.setVisible(true);
            });

            buttonPasswordSettingsClose.addEventHandler(ActionEvent.ACTION, e ->
            {
                anchorpanePasswordSettings.setVisible(false);
                anchorpaneMainSettings.setVisible(true);
            });

            buttonSetBackup.addEventHandler(ActionEvent.ACTION, e ->
            {
                anchorpaneMainSettings.setVisible(false);
                anchorpaneBackupSettings.setVisible(true);
            });

            buttonBackupSettingsClose.addEventHandler(ActionEvent.ACTION, e ->
            {
                anchorpaneBackupSettings.setVisible(false);
                anchorpaneMainSettings.setVisible(true);
            });
        }
    }

    @Override
    public void clear()
    {

    }
}
