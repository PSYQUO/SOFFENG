package controller.ViewManager;

public class ViewManagerException extends Exception
{
    public ViewManagerException(String classname)
    {
        super("No ViewManager set in \"" + classname + "\" class. Button actions will not be set.");
    }
}