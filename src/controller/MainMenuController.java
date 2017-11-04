package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class MainMenuController extends Controller
{
    @FXML
    Button buttonNewOrder;

    public MainMenuController() throws IOException
    {
        initialize(this, "/view/main-menu");
    }

    @Override
    public void load()
    {
        if(initialLoad)
        {
            buttonNewOrder.setOnAction(e ->
            {
                if(viewManager == null)
                    System.err.println("No ViewManager set in " + getClass().getSimpleName());
                else
                    viewManager.switchViews("NewOrderController");
            });

            initialLoad = false;
        }

    }
}
