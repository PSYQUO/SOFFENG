package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

/**
 * Controller Abstract Class
 *
 */

public abstract class Controller
{
    protected Parent root;
    protected ViewManager viewManager;

    /**
     * Initializes the FXML class and sets its controller.
     * @param controller Controller to be added to the FXML class
     * @param fxmlpath Path to the FXML class
     * @throws IOException
     */
    protected void initialize(Controller controller, String fxmlpath) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlpath + ".fxml"));
        loader.setController(controller);

        root = loader.load();
        root.getStylesheets().add(getClass().getResource(fxmlpath + ".css").toExternalForm());
    }

    /**
     * Sets the ViewManager of the controller.
     * @param viewManager ViewManager
     */
    public void setViewManager(ViewManager viewManager)
    {
        this.viewManager = viewManager;
    }

    public Parent getRoot()
    {
        return root;
    }
}
