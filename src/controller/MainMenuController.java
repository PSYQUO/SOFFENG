package controller;

import controller.viewmanager.ViewManagerException;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import model.DatabaseModel;
import model.User;
import view.dialog.PasswordDialogFactory;

import javax.swing.*;
import java.io.IOException;

public class MainMenuController extends Controller
{
    @FXML
    private Button buttonNewOrder, buttonInventory, buttonSettings, buttonFiles, buttonAnalytics;

    @FXML
    private ChoiceBox comboName;

    private DatabaseModel dbm;
    private User user;
    private Stage stage;

    public MainMenuController(String fxmlpath, String csspath, Stage primaryStage) throws IOException
    {
        super(fxmlpath, csspath);
        dbm = new DatabaseModel();
        stage = primaryStage;
    }

    @Override
    public void load() throws ViewManagerException
    {
        if(isFirstLoad())
        {
            setupComboName();

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

        loadUsers();
    }

    @Override
    public void clear()
    {

    }

    private void setupComboName()
    {
        comboName.setConverter(new StringConverter<User>()
        {
            @Override
            public String toString(User user)
            {
                return user.getUsername();
            }

            @Override
            public User fromString(String string)
            {
                return null;
            }
        });

        comboName.addEventHandler(ActionEvent.ACTION, event -> {
            PasswordDialogFactory pdf = new PasswordDialogFactory(stage);
            Dialog d = pdf.create();
            d.show();
            if(pdf.getPasswordField().getText().equals("1234"))
            {
                System.out.println("SUP BRO");
            }
        });
    }

    private void loadUsers()
    {
        comboName.setItems(FXCollections.observableArrayList(dbm.getUsers()));
    }
}
