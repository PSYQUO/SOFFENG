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
                anchorpaneMainSettings.setDisable(true);
                anchorpanePasswordSettings.setVisible(true);
                anchorpanePasswordSettings.setDisable(false);
            });

            buttonPasswordSettingsClose.addEventHandler(ActionEvent.ACTION, e ->
            {
                anchorpanePasswordSettings.setVisible(false);
                anchorpanePasswordSettings.setDisable(true);
                anchorpaneMainSettings.setVisible(true);
                anchorpaneMainSettings.setDisable(false);
            });

            buttonSetBackup.addEventHandler(ActionEvent.ACTION, e ->
            {
                anchorpaneMainSettings.setVisible(false);
                anchorpaneMainSettings.setDisable(true);
                anchorpaneBackupSettings.setVisible(true);
                anchorpaneBackupSettings.setDisable(false);
            });

            buttonBackupSettingsClose.addEventHandler(ActionEvent.ACTION, e ->
            {
                anchorpaneBackupSettings.setVisible(false);
                anchorpaneBackupSettings.setDisable(true);
                anchorpaneMainSettings.setVisible(true);
                anchorpaneMainSettings.setDisable(false);
            });
        }
    }

    @Override
    public void clear()
    {

    }
}
