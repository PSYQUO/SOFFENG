package controller.viewmanager;

public class ViewManagerException extends Exception
{
    public ViewManagerException(String classname)
    {
        super("No ViewManager set in " + classname + " class. Initial load will not be executed.");
    }
}