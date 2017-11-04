package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class NewOrderController extends Controller
{
    @FXML
    private BorderPane borderpanePayment;

    @FXML
    private Button buttonPaymentClose;

    @FXML
    private Button buttonOK;

    public NewOrderController() throws IOException
    {
        initialize(this, "/view/new-order");


    }
}
