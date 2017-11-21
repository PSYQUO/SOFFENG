package model;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;

public class PasswordDialog extends Dialog<String> {
    // location of the CSS for entire layout
    private static final String STYLESHEET_LOCATION = "/views/dialogs.css";
    private static final String MESSAGE = "Enter your password:";
    private static final String WARNING = "Enter your password:";

    public PasswordDialog(User u) {
        getDialogPane().getStylesheets().add(STYLESHEET_LOCATION);
        // Remove the title bar
        initStyle(StageStyle.UNDECORATED);
        // Do not set header text
        setHeaderText(null);
        // Add buttons: OK, CANCEL
        getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL, ButtonType.OK);

        // TODO Figure out how to get the buttons themselves for CSS styling (OK button is green)

        // Create space to place stuff
        VBox vboxContent = new VBox();
        vboxContent.setAlignment(Pos.CENTER);

        // Add all elements to space, place in dialog
        warning = new Label("");
        warning.setId("labelWarning");
        vboxContent.getChildren().addAll(new Label(MESSAGE), passwordField, warning);
        getDialogPane().setContent(vboxContent);

        // Focus on password
        Platform.runLater(() -> passwordField.requestFocus());

        showAndWait();
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public void passwordIncorrectNotification() {
        // Red border and background for passwordField
        passwordField.setStyle("-fx-background-color: #f4ccff; " +
                "-fx-border-color: RGB(239, 83, 80); " +
                "-fx-border-width: 3px;");
        warning.setText("Incorrect password! Please try again!");
    }

    private PasswordField passwordField;
    private Label warning;
}
