package controller;

import controller.ViewManager.ViewManagerException;

import java.io.IOException;

public class AnalyticsController extends Controller
{
    public AnalyticsController() throws IOException
    {
        initialize(this, "/view/analytics-menu", "/view/analytics-menu");
    }

    @Override
    public void load() throws ViewManagerException
    {
        if(checkInitialLoad(getClass().getSimpleName()))
        {

        }
    }

    @Override
    public void clear()
    {

    }
}
