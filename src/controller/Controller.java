package controller;

import controller.ViewManager.ViewManager;
import controller.ViewManager.ViewManagerException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

/**
 * Controller Abstract Class
 */

public abstract class Controller
{
    private boolean initialLoad = true;

    private Parent root;
    protected ViewManager viewManager;

    /**
     * The load method adds action listeners to the view's elements and populates the view.
     */
    public abstract void load() throws ViewManagerException;

    /**
     * The clear method clears the view's elements
     */
    public abstract void clear();

    /**
     * Initializes the FXML class and sets its controller.
     * @param controller Controller to be added to the FXML class
     * @param fxmlpath Path to the FXML class
     * @param csspath Path to the css file
     * @throws IOException
     */
    protected void initialize(Controller controller, String fxmlpath, String csspath) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlpath + ".fxml"));
        loader.setController(controller);

        root = loader.load();

        if(csspath != null)
            root.getStylesheets().add(getClass().getResource(fxmlpath + ".css").toExternalForm());
    }

    /**
     * Checks if its the controller's initial load.
     *
     * @param classname Class name of the controller.
     * @return Returns true if it is the controller's initial load.
     * @throws ViewManagerException
     */
    protected boolean checkInitialLoad(String classname) throws ViewManagerException
    {
        if(viewManager == null)
            throw new ViewManagerException(classname);

        return initialLoad && !(initialLoad = false);
        /**
         * if(initialLoad)
            {
                initialLoad = false;
                return true;
            }
            return false;
         */
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
