package controller;

import java.io.IOException;

import controller.viewmanager.ViewManagerException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import java.io.File;
import model.BackUp;

public class SettingsController extends Controller
{   
    @FXML
    private Button buttonBack, buttonBack1, buttonBack2,
                   buttonChangePassword, buttonSetBackup, buttonApply, buttonChangeLocation;

    @FXML
    private AnchorPane anchorpaneMainSettings, anchorpanePasswordSettings, anchorpaneBackupSettings;

    @FXML
    private TextField textfieldBackupLocation;

    private Stage stage;

    public SettingsController(String fxmlpath, String csspath, Stage primaryStage) throws IOException
    {
        super(fxmlpath, csspath);
        stage = primaryStage;
    }

    @Override
    public void load() throws ViewManagerException
    {
        if(isFirstLoad())
        {
            buttonBack.addEventHandler(ActionEvent.ACTION, e ->
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

            buttonBack1.addEventHandler(ActionEvent.ACTION, e ->
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

            buttonBack2.addEventHandler(ActionEvent.ACTION, e ->
            {
                anchorpaneBackupSettings.setVisible(false);
                anchorpaneBackupSettings.setDisable(true);
                anchorpaneMainSettings.setVisible(true);
                anchorpaneMainSettings.setDisable(false);
            });

            buttonApply.addEventHandler(ActionEvent.ACTION, e ->
            {
                BackUp b = new BackUp(textfieldBackupLocation.getText());
            });

            buttonChangeLocation.addEventHandler(ActionEvent.ACTION, e ->
            {
                DirectoryChooser directoryChooser = new DirectoryChooser();
                File selectedDirectory = directoryChooser.showDialog(stage);
                if(selectedDirectory == null)
                {
                    textfieldBackupLocation.setText("No Directory selected");
                }
                else
                {
                    textfieldBackupLocation.setText(selectedDirectory.getAbsolutePath() + "\\");
                }
            });
        }
    }

    @Override
    public void clear()
    {

    }
}
