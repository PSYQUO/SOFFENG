package view.dialog;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PasswordDialogFactory implements I_DialogFactory {
    // location of the CSS for entire layout
    private static final String STYLESHEET_LOCATION = "/view/dialogs.css";
    private static final String MESSAGE = "Enter your password:";
    private static final String WARNING = "Incorrect password! Please try again!";

    private Stage stage;

    public PasswordDialogFactory(Stage stage)
    {
        this.stage = stage;
    }

    @Override
    public Dialog create() {
        initialize();
        return dialog;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public void initialize() {
        dialog = new Dialog();
        dialog.getDialogPane().getStylesheets().add(STYLESHEET_LOCATION);
        dialog.getDialogPane().getStyleClass().add("background");
        // Remove the title bar
        dialog.initStyle(StageStyle.UNDECORATED);
        // Do not set header text
        dialog.setHeaderText(null);
        // Initialize window to avoid closing main window
        dialog.initOwner(stage);
        // Add buttons: OK, CANCEL
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL, ButtonType.OK);

        // TODO Figure out how to get the buttons themselves for CSS styling (OK button is green)

        // Create space to place stuff
        VBox vboxContent = new VBox();
        vboxContent.setAlignment(Pos.CENTER);
        vboxContent.setSpacing(20.0);
        vboxContent.setPadding(new Insets(30, 10, 10, 10));

        // Add all elements to space, place in dialog
        passwordField = new PasswordField();
        warning = new Label("");
        warning.setId("labelWarning");
        vboxContent.getChildren().addAll(new Label(MESSAGE), passwordField, warning);
        dialog.getDialogPane().setContent(vboxContent);

        // Focus on password
        Platform.runLater(() -> passwordField.requestFocus());
    }

    public void notifyIncorrectPassword() {
        // Red border and background for passwordField
        passwordField.setStyle("-fx-background-color: #f4ccff; " +
                "-fx-border-color: RGB(239, 83, 80); " +
                "-fx-border-width: 3px;");
        warning.setText(WARNING);
    }

    private PasswordField passwordField;
    private Label warning;
    private Dialog dialog;
}
