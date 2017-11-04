package controller;

import java.io.IOException;

public class SettingsController extends Controller
{
    public SettingsController() throws IOException
    {
        initialize(this, "/view/main-settings");
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
