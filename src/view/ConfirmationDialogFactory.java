package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;

public class ConfirmationDialogFactory implements I_DialogFactory {

    // location of the CSS for entire layout
    private static final String STYLESHEET_LOCATION = "/views/dialogs.css";
    private Label message;

    public Dialog create() {
        initialize();
        return dialog;
    }

    public Dialog create(ConfirmationType confirmationType) {
        // Check warning type to place message
        switch (confirmationType) {
            case DATABASE_ADD:
                message.setText("This entry will be added to the database.\n" +
                        "This action cannot be undone. Continue?");
                break;
            case DATABASE_EDIT:
                message.setText("This entry will be edited in the database.\n" +
                        "This action cannot be undone. Continue?");
                break;
            case DATABASE_REMOVE:
                message.setText("This entry will be removed from the database.\n" +
                        "This action cannot be undone. Continue?");
                break;
            case PASSWORD_CHANGE:
                message.setText("Are you sure you want to change your password?");
                break;
            case APP_EXIT:
                message.setText("Are you sure you want to exit the application?");
                break;
        }

        dialog.getDialogPane().setContent(message);

        return dialog;
    }

    public void initialize() {
        dialog = new Dialog();
        // Add CSS
        dialog.getDialogPane().getStylesheets().add(STYLESHEET_LOCATION);
        dialog.getDialogPane().getStyleClass().add("background");
        // Remove the title bar
        dialog.initStyle(StageStyle.UNDECORATED);
        // Do not set header text
        dialog.setHeaderText(null);
        // Add buttons: OK, CANCEL
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL, ButtonType.OK);

        // TODO Figure out how to get the buttons themselves for CSS styling (OK button is green)

        // Create space to place stuff
        VBox vboxContent = new VBox();
        vboxContent.setAlignment(Pos.CENTER);
        vboxContent.setSpacing(30.0);
        vboxContent.setPadding(new Insets(30, 10, 10, 10));

        // Initialize label
        message = new Label();

        // Warning graphic
        // TODO add circular (i) graphic (Is this necessary?)

        vboxContent.getChildren().addAll(message); // TODO add circular i graphic to vboxContent
        dialog.getDialogPane().setContent(vboxContent);
    }

    private Dialog dialog;
}
