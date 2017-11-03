package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

/**
 * Controller Interface
 *
 * Note:
 */

public abstract class Controller
{
    protected Parent root;
    protected ViewManager viewManager;

    /**
     *
     * @param controller
     * @param fxmlpath
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

    public void setViewManager(ViewManager viewManager)
    {
        this.viewManager = viewManager;
    }

    public Parent getRoot()
    {
        return root;
    }
}
