package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;
import javax.swing.text.html.ImageView;

public class WarningDialog extends Dialog {
    // location of the CSS for entire layout
    private static final String STYLESHEET_LOCATION = "/views/dialogs.css";
    private Label message;

    public WarningDialog(WarningType warningType) {
        // Add CSS
        getDialogPane().getStylesheets().add(STYLESHEET_LOCATION);
        getDialogPane().getStyleClass().add("background");
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
        vboxContent.setSpacing(30.0);
        vboxContent.setPadding(new Insets(30, 10, 10, 10));

        // Initialize label
        message = new Label();

        // Warning graphic
        // TODO add circular (i) graphic (Is this necessary?)

        vboxContent.getChildren().addAll(message); // TODO add circular i graphic to vboxContent

        // Check warning type to place message
        switch (warningType) {
            case INVALID_INPUT:
                message.setText("Input is invalid. Please insert a \n" +
                        "new value and try again.");
                break;
            case INVALID_PAYMENT:
                message.setText("The payment entered is invalid.\n" +
                        "Please enter a new value and try again.");
                break;
            case RESTRICTED_ACCESS:
                message.setText("This account does not have\n" +
                        "access to this feature.");
                break;
        }

        getDialogPane().setContent(message);

        showAndWait();
    }
}