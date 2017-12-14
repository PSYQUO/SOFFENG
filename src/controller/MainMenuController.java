package controller;

import controller.viewmanager.ViewManagerException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import model.DatabaseModel;
import model.User;
import view.dialog.DialogFactory;
import view.dialog.PasswordDialogFactory;

import java.io.IOException;
import java.util.Optional;

public class MainMenuController extends Controller
{
    @FXML
    private Button buttonNewOrder, buttonInventory, buttonSettings, buttonFiles, buttonAnalytics;

    @FXML
    private ChoiceBox comboName;

    private DatabaseModel dbm;
    private User currentUser;
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
            {
                if(currentUser != null)
                    viewManager.switchViews("NewOrderController");
                else
                {
                    DialogFactory df = new DialogFactory(stage);
                    Dialog d = df.createWarningDialog("You are not currently logged in.\nPlease login using the blue dropdown at the top.");
                    d.show();
                }
            });

            buttonInventory.addEventHandler(ActionEvent.ACTION, e ->
            {
                if(currentUser != null)
                    viewManager.switchViews("InventoryController");
                else
                {
                    DialogFactory df = new DialogFactory(stage);
                    Dialog d = df.createWarningDialog("You are not currently logged in.\nPlease login using the blue dropdown at the top.");
                    d.show();
                }
            });

            buttonSettings.addEventHandler(ActionEvent.ACTION, e ->
            {
                if(currentUser != null)
                    viewManager.switchViews("SettingsController");
                else
                {
                    DialogFactory df = new DialogFactory(stage);
                    Dialog d = df.createWarningDialog("You are not currently logged in.\nPlease login using the blue dropdown at the top.");
                    d.show();
                }
            });

            buttonFiles.addEventHandler(ActionEvent.ACTION, e ->
            {
                if(currentUser != null)
                    viewManager.switchViews("FilesController");
                else
                {
                    DialogFactory df = new DialogFactory(stage);
                    Dialog d = df.createWarningDialog("You are not currently logged in.\nPlease login using the blue dropdown at the top.");
                    d.show();
                }
            });

            buttonAnalytics.addEventHandler(ActionEvent.ACTION, e ->
            {
                if(currentUser != null)
                {
                    if(currentUser.getRole().getRoleID() == 2 || currentUser.getRole().getRoleID() == 3)
                        viewManager.switchViews("AnalyticsController");
                    else
                    {

                    }
                }
                else
                {
                    DialogFactory df = new DialogFactory(stage);
                    Dialog d = df.createWarningDialog("You are not currently logged in.\nPlease login using the blue dropdown at the top.");
                    d.show();
                }
            });

        }

        loadUsers();
    }

    @Override
    public void clear()
    {

    }

    public User getCurrentUser()
    {
        return currentUser;
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

        comboName.addEventHandler(ActionEvent.ACTION, event ->
        {
            PasswordDialogFactory pdf = new PasswordDialogFactory(stage);
            Dialog d = pdf.create();
            Optional<ButtonType> result = d.showAndWait();

            if(result.isPresent() && result.get() == ButtonType.OK)
            {
                // Typecasting
                User user = (User) comboName.getSelectionModel().getSelectedItem();
                if(user != null)
                {
                    String pass = user.getPassword();
                    if(pdf.getPasswordField().getText().equals(pass))
                        currentUser = user;
                    else
                    {
                        pdf.notifyIncorrectPassword();
                        comboName.getSelectionModel().select(currentUser);
                    }
                }
            }

            if(result.isPresent() && result.get() == ButtonType.CANCEL)
                comboName.getSelectionModel().select(currentUser);

        });
    }

    private void loadUsers()
    {
        comboName.setItems(FXCollections.observableArrayList(dbm.getUsers()));
    }
}
