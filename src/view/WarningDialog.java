package view;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.stage.StageStyle;
import javax.swing.text.html.ImageView;

public class WarningDialog extends Dialog {
    // location of the CSS for entire layout
    private static final String STYLESHEET_LOCATION = "/views/dialogs.css";

    public WarningDialog(WarningType warningType) {
        // Add CSS
        getDialogPane().getStylesheets().add(STYLESHEET_LOCATION);
        // Warning graphic
        // TODO add yellow triangle graphic (Is this necessary?)
        // Remove the title bar
        initStyle(StageStyle.UNDECORATED);
        // Do not set header text
        setHeaderText(null);
        // Add buttons: OK, CANCEL
        getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL, ButtonType.OK);

        // Check warning type to place message
        switch(warningType) {
            case INVALID_INPUT:
                this.setContentText("Input is invalid. Please insert a new value and try again.");
                break;
            case INVALID_PAYMENT:
                this.setContentText("The payment entered is invalid. Please enter a new value and try again.");
                break;
            case RESTRICTED_ACCESS:
                this.setContentText("This account does not have access to this feature.");
                break;
        }

        showAndWait();
    }
}