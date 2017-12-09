package controller;

import controller.viewmanager.ViewManager;
import controller.viewmanager.ViewManagerException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

/**
 * Controller Abstract Class
 */

public abstract class Controller
{
    private boolean firstLoad = true;

    private Parent root;
    protected ViewManager viewManager;

    /**
     * Constructor of a controller. The constructor initializes and links the FXML class to the controller.
     *
     * @param fxmlpath Path to the FXML class
     * @param csspath  Path to the css file
     * @throws IOException
     */
    public Controller(String fxmlpath, String csspath) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlpath));
        loader.setController(this);

        root = loader.load();

        if(csspath != null)
            root.getStylesheets().add(getClass().getResource(csspath).toExternalForm());
    }

    /**
     * The load method adds action listeners to the view's elements and populates the view.
     */
    public abstract void load() throws ViewManagerException;

    /**
     * The clear method clears the view's elements
     */
    public abstract void clear();

    /**
     * Checks if its the controller's first load.
     *
     * @return Returns true if it is the controller's initial load.
     * @throws ViewManagerException
     */
    protected boolean isFirstLoad() throws ViewManagerException
    {
        if(viewManager == null)
            throw new ViewManagerException(getClass().getSimpleName());

        return firstLoad && !(firstLoad = false);
    }

    /**
     * Sets the ViewManager of the controller.
     *
     * @param viewManager ViewManager
     */
    public void setViewManager(ViewManager viewManager)
    {
        this.viewManager = viewManager;
    }

    /**
     * getRoot method.
     *
     * @return The root of the controller's view.
     */
    public Parent getRoot()
    {
        return root;
    }
}
