package controller;

import controller.ViewManager.ViewManagerException;

import java.io.IOException;

public class FilesController extends Controller
{
    public FilesController() throws IOException
    {
        initialize(this, "/view/inventory", "/view/inventory");
    }

    @Override
    public void load() throws ViewManagerException
    {

    }

    @Override
    public void clear()
    {

    }
}
