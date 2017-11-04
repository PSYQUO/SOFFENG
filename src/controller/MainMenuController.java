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
            buttonNewOrder.setOnAction(e -> viewManager.switchViews("NewOrderController"));

            initialLoad = false;
        }

    }
}
