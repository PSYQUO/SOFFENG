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

        buttonNewOrder.setOnAction(e -> viewManager.switchViews("NewOrderController"));
    }
}
