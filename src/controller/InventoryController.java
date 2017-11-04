package controller;

import java.io.IOException;

public class InventoryController extends Controller
{
    public InventoryController() throws IOException
    {
        initialize(this, "/view/inventory", true);
    }

    @Override
    public void load()
    {

    }

    @Override
    public void clear()
    {

    }
}
