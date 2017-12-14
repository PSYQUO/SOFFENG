package controller;

import controller.usercontrol.UserControl;
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
import view.dialog.DialogMessageType;
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
                if(UserControl.getInstance().getCurrentUser() != null)
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
                if(UserControl.getInstance().getCurrentUser() != null)
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
                if(UserControl.getInstance().getCurrentUser() != null)
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
                User user = UserControl.getInstance().getCurrentUser();
                if(user != null)
                {
                    if(user.getRole().getRoleID() == 2 || user.getRole().getRoleID() == 3)
                        viewManager.switchViews("FilesController");
                    else
                    {
                        DialogFactory df = new DialogFactory(stage);
                        Dialog d = df.create(DialogMessageType.RESTRICTED_ACCESS);
                        d.show();
                    }
                }
                else
                {
                    DialogFactory df = new DialogFactory(stage);
                    Dialog d = df.createWarningDialog("You are not currently logged in.\nPlease login using the blue dropdown at the top.");
                    d.show();
                }
            });

            buttonAnalytics.addEventHandler(ActionEvent.ACTION, e ->
            {
                User user = UserControl.getInstance().getCurrentUser();
                if(user != null)
                {
                    if(user.getRole().getRoleID() == 2 || user.getRole().getRoleID() == 3)
                        viewManager.switchViews("AnalyticsController");
                    else
                    {
                        DialogFactory df = new DialogFactory(stage);
                        Dialog d = df.create(DialogMessageType.RESTRICTED_ACCESS);
                        d.show();
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
                        UserControl.getInstance().setCurrentUser(user);
                    else
                    {
                        pdf.notifyIncorrectPassword();
                        comboName.getSelectionModel().select(UserControl.getInstance().getCurrentUser());
                    }
                }
            }

            if(result.isPresent() && result.get() == ButtonType.CANCEL)
                comboName.getSelectionModel().select(UserControl.getInstance().getCurrentUser());

        });
    }

    private void loadUsers()
    {
        comboName.setItems(FXCollections.observableArrayList(dbm.getUsers()));
    }
}
