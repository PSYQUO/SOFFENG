package controller.viewmanager;

import controller.Controller;
import javafx.scene.Scene;

import java.util.ArrayList;
import java.util.List;

/**
 * ViewManager
 * <p>
 * The ViewManager handles the changing of roots in the Stage's scene.
 * It holds all controllers needed in the application.
 * It holds the scene of an application.
 */
public class ViewManager
{
    List<Controller> controllerList;
    Scene scene;

    /**
     * Default constructor of ViewManager.
     * ViewManager should be instantiated with a default controller. This avoids setting the scene's root to null.
     *
     * @param controller Default controller.
     */
    public ViewManager(Controller controller)
    {
        controllerList = new ArrayList<>();
        controllerList.add(controller);
        try
        {
            controller.setViewManager(this);
            controller.load();
        }
        catch(ViewManagerException e)
        {
            e.printStackTrace();
        }

        scene = new Scene(controller.getRoot());
    }

    /**
     * Adds a controller to the ViewManager.
     * Checks whether the controller class was already added to the ViewManager to avoid duplicate controllers.
     *
     * @param controller Controller to be added
     */
    public void addController(Controller controller)
    {
        for(Controller c : controllerList)
        {
            if(controller.getClass().getSimpleName().equals(c.getClass().getSimpleName()))
            {
                System.err.println(controller.getClass().getSimpleName() + " class was already added to the ViewManager");
                return;
            }
        }

        controllerList.add(controller);
    }

    /**
     * getScene method.
     *
     * @return Returns the scene of the ViewManager.
     */
    public Scene getScene()
    {
        return scene;
    }

    /**
     * Switches the currently displayed controller view. Will throw a ViewManagerException if the class was not found.
     *
     * @param classname Class name of the controller to be switched.
     */
    public void switchViews(String classname)
    {
        boolean hasSwitched = false;

        for(Controller c : controllerList)
        {
            if(classname.equals(c.getClass().getSimpleName()))
            {
                try
                {
                    c.load();
                }
                catch(ViewManagerException e)
                {
                    e.printStackTrace();
                }

                scene.setRoot(c.getRoot());
                hasSwitched = true;
            }
        }

        if(!hasSwitched)
            System.err.println("No class named \"" + classname + "\" added in ViewManager");
    }
}
