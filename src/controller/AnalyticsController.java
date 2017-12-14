package controller;

import controller.viewmanager.ViewManagerException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import model;

import java.io.IOException;

public class AnalyticsController extends Controller
{
    @FXML
    private TableView<Col2> tableMostSold, tableMostWasted;

    @FXML
    private TableView<XReading> tableXReading;

    @FXML
    private TableView<ZReading> tableZReading;

    @FXML
    private Button buttonXreading, buttonZreading, buttonSold, buttonWasted,
            buttonBackMenu, buttonBackS, buttonBackW, buttonBackZ, buttonBackX;

    @FXML
    private AnchorPane analMainMenu, analMostSold, analMostWasted, analZReading, analXReading;

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

            buttonXreading.addEventHandler(ActionEvent.ACTION, event ->
            {
                changePaneState("TO_XR");
            });

            buttonZreading.addEventHandler(ActionEvent.ACTION, event ->
            {
                changePaneState("TO_ZR");
            });

            buttonSold.addEventHandler(ActionEvent.ACTION, event ->
            {
                changePaneState("TO_MS");
            });

            buttonWasted.addEventHandler(ActionEvent.ACTION, event ->
                    changePaneState("TO_MW"));

            buttonBackX.addEventHandler(ActionEvent.ACTION, event ->
            {
                changePaneState("TO_MAIN");
            });

            buttonBackZ.addEventHandler(ActionEvent.ACTION, event ->
            {
                changePaneState("TO_MAIN");
            });

            buttonBackS.addEventHandler(ActionEvent.ACTION, event ->
            {
                changePaneState("TO_MAIN");
            });

            buttonBackW.addEventHandler(ActionEvent.ACTION, event ->
            {
                changePaneState("TO_MAIN");
            });
        }

        loadTables();
    }

    @Override
    public void clear()
    {

    }

    private void changePaneState(String state)
    {
        switch(state)
        {
            case "TO_XR":
                analXReading.setVisible(true);
                analXReading.setDisable(false);
                analMainMenu.setVisible(false);
                analMainMenu.setDisable(true);
                break;
            case "TO_ZR":
                analZReading.setVisible(true);
                analZReading.setDisable(false);
                analMainMenu.setVisible(false);
                analMainMenu.setDisable(true);
                break;
            case "TO_MS":
                analMostSold.setVisible(true);
                analMostSold.setDisable(false);
                analMainMenu.setVisible(false);
                analMainMenu.setDisable(true);
                break;
            case "TO_MW":
                analMostWasted.setVisible(true);
                analMostWasted.setDisable(false);
                analMainMenu.setVisible(false);
                analMainMenu.setDisable(true);
                break;
            case "TO_MAIN":
                analMainMenu.setVisible(true);
                analMainMenu.setDisable(false);
                analMostSold.setVisible(false);
                analMostSold.setDisable(true);
                analMostWasted.setVisible(false);
                analMostWasted.setDisable(true);
                analXReading.setVisible(false);
                analXReading.setDisable(true);
                analZReading.setVisible(false);
                analZReading.setDisable(true);
                break;
        }
    }

    private void loadTables()
    {

    }
}
