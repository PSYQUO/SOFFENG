package controller;

import controller.viewmanager.ViewManagerException;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.DatabaseModel;
import model.XReading;
import model.ZReading;
import model.food.MostSoldWasted;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AnalyticsController extends Controller
{
    @FXML
    private TableView<MostSoldWasted> tableMostSold, tableMostWasted;

    @FXML
    private TableView<XReading> tableXReading;

    @FXML
    private TableView<ZReading> tableZReading;

//    @FXML
//    private TableColumn
//            colItemS, colPriceS, colQuantityS,
//            colItemW, colPriceW, colQuantityW;

    @FXML
    private TableColumn<XReading, Double> colTotalX;

    @FXML
    private TableColumn<XReading, String> colUserX;

    @FXML
    private TableColumn<ZReading, Double> colTotalZ;

    @FXML
    private TableColumn<ZReading, String> colDateZ;

    @FXML
    private Button buttonXreading, buttonZreading, buttonSold, buttonWasted,
            buttonBackMenu, buttonBackS, buttonBackW, buttonBackZ, buttonBackX;

    @FXML
    private AnchorPane analMainMenu, analMostSold, analMostWasted, analZReading, analXReading;

    @FXML
    private Label dateBoxX;

    private DatabaseModel dbm;

    public AnalyticsController(String fxmlpath, String csspath) throws IOException
    {
        super(fxmlpath, csspath);
        dbm = new DatabaseModel();
    }

    @Override
    public void load() throws ViewManagerException
    {
        if(isFirstLoad())
        {
            buttonBackMenu.addEventHandler(ActionEvent.ACTION, e ->
                    viewManager.switchViews("MainMenuController"));

            setTablePropertiesAndItems();

            buttonXreading.addEventHandler(ActionEvent.ACTION, event ->
                    changePaneState("TO_XR"));

            buttonZreading.addEventHandler(ActionEvent.ACTION, event ->
                    changePaneState("TO_ZR"));

            buttonSold.addEventHandler(ActionEvent.ACTION, event ->
                    changePaneState("TO_MS"));

            buttonWasted.addEventHandler(ActionEvent.ACTION, event ->
                    changePaneState("TO_MW"));

            buttonBackX.addEventHandler(ActionEvent.ACTION, event ->
                    changePaneState("TO_MAIN"));

            buttonBackZ.addEventHandler(ActionEvent.ACTION, event ->
                    changePaneState("TO_MAIN"));

            buttonBackS.addEventHandler(ActionEvent.ACTION, event ->
                    changePaneState("TO_MAIN"));

            buttonBackW.addEventHandler(ActionEvent.ACTION, event ->
                    changePaneState("TO_MAIN"));
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

    private void setTablePropertiesAndItems()
    {
        colUserX.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getUser().getUsername()));
        colTotalX.setCellValueFactory(new PropertyValueFactory<>("total"));
        colTotalZ.setCellValueFactory(new PropertyValueFactory<>("total"));
        colDateZ.setCellValueFactory(new PropertyValueFactory<>("date"));
    }

    private void loadTables()
    {
//        tableMostSold.setItems(FXCollections.observableArrayList(dbm.getMostandLeastSold()));
//        tableMostWasted.setItems(FXCollections.observableArrayList(dbm.getMostandLeastSold()));
        tableXReading.setItems(FXCollections.observableArrayList(dbm.getXReadToday()));
        dateBoxX.setText(new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
        tableZReading.setItems(FXCollections.observableArrayList(dbm.getZReadAll()));
    }
}
