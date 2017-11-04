package controller;

public class ViewManagerException extends Exception
{
    public ViewManagerException(String classname)
    {
        super("No ViewManager set in " + classname + ". Button actions will not be set.");
    }
}