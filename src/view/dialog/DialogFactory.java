package view.dialog;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;

public class DialogFactory implements I_DialogFactory {
    // location of the CSS for entire layout
    private static final String STYLESHEET_LOCATION = "/view/dialogs.css";

    @Override
    public Dialog create() {
        initialize();
        return dialog;
    }

    /**
     * Calls the appropriate create method for each DialogMessageType.
     * Does not return any dialog box, as compared to the original
     * create method.
     * @param type
     */
    public Dialog create(DialogMessageType type) {
        initialize();
        switch(type) {
            // Confirmation
            case DATABASE_ADD:
            case DATABASE_EDIT:
            case DATABASE_REMOVE:
            case PASSWORD_CHANGE:
            case APP_EXIT:
                return createConfirmationDialog(type);

            // Warning
            case INVALID_INPUT:
            case INVALID_PAYMENT:
            case RESTRICTED_ACCESS:
                return createWarningDialog(type);

            // Information
            case TRANSACTION_COMPLETE:
            case SETTINGS_SAVED:
                return createInformationDialog(type);
        }

        return create();
    }

    /**
     * Returns a custom confirmation dialog with a (i) graphic,
     * cancel, and OK button
     * @return Dialog
     * @param message
     */
    public Dialog createConfirmationDialog(String message) {
        initialize();
        this.message.setText(message);
        // TODO add (i) graphic

        // Add buttons
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL, ButtonType.OK);

        return dialog;
    }

    /**
     * Returns a confirmation dialog with a (i) graphic,
     * cancel, and OK button
     * @return Dialog
     * @param type
     */
    public Dialog createConfirmationDialog(DialogMessageType type) {
        switch(type) {
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

        // TODO add (i) graphic

        // Add buttons
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL, ButtonType.OK);

        return dialog;
    }

    /**
     * Returns a custom warning dialog with a (i) graphic,
     * and OK button
     * @return Dialog
     * @param message
     */
    public Dialog createWarningDialog(String message) {
        initialize();
        this.message.setText(message);
        // TODO add (i) graphic

        // Add buttons
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK);

        return dialog;
    }

    /**
     * Returns a warning dialog with a (i) graphic,
     * and OK button
     * @return Dialog
     * @param type
     */
    public Dialog createWarningDialog(DialogMessageType type) {
        switch (type) {
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

        // TODO add (i) graphic

        // Add buttons
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK);

        return dialog;
    }

    /**
     * Returns a custom information dialog with a (check) graphic,
     * and OK button
     * @return Dialog
     * @param message
     */
    public Dialog createInformationDialog(String message) {
        initialize();
        this.message.setText(message);
        // TODO add (i) graphic

        // Add buttons
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK);

        return dialog;
    }

    /**
     * Returns a information dialog with a (check) graphic,
     * and OK button
     * @return Dialog
     * @param type
     */
    public Dialog createInformationDialog(DialogMessageType type) {
        switch (type) {
            case TRANSACTION_COMPLETE:
                message.setText("Transaction complete!");
                break;
            case SETTINGS_SAVED:
                message.setText("Your settings have been saved!");
                break;
        }

        // TODO add (i) graphic to ImageView

        // Add buttons
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK);

        return dialog;
    }

    /**
     * Initializes the contents of the generic dialog box
     * that are common to all types of dialog boxes, such as
     * the stylesheet, classes (CSS), removal of app bar,
     * and removal of header text.
     */
    public void initialize() {
        dialog = new Dialog();
        message = new Label();
        graphic = new ImageView();
        // Add CSS
        dialog.getDialogPane().getStylesheets().add(STYLESHEET_LOCATION);
        dialog.getDialogPane().getStyleClass().add("background");
        // Remove the title bar
        dialog.initStyle(StageStyle.UNDECORATED);
        // Do not set header text
        dialog.setHeaderText(null);

        // Create space to place stuff
        VBox vboxContent = new VBox();
        vboxContent.setAlignment(Pos.CENTER);
        vboxContent.setSpacing(30.0);
        vboxContent.setPadding(new Insets(30, 10, 10, 10));

        // Add the content
        // TODO insert ImageView as a space for graphics
        vboxContent.getChildren().addAll(message);
        dialog.getDialogPane().setContent(vboxContent);
    }

    private Dialog dialog;
    private Label message;
    private ImageView graphic;
}
