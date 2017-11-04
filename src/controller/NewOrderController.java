package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import model.Consumable;
import model.DatabaseModel;

import java.io.IOException;
import java.util.ArrayList;

public class NewOrderController extends Controller
{
    @FXML
    private BorderPane borderpanePayment, borderpaneNewOrder;

    @FXML
    private Button buttonOK, buttonEnter, buttonPaymentClose;

    @FXML
    private Spinner<Integer> spinnerCustNo;

    @FXML
    private TextField textfieldPayment;

    @FXML
    private FlowPane flowpaneBudget, flowpaneCombo, flowpaneSandwitch, flowpaneExtras;

    public NewOrderController() throws IOException
    {
        initialize(this, "/view/new-order");
    }

    @Override
    public void load()
    {
        if(initialLoad)
        {
            buttonOK.addEventHandler(ActionEvent.ACTION, e ->
            {
                borderpanePayment.setDisable(false);
                borderpanePayment.setVisible(true);
                borderpaneNewOrder.setDisable(true);
            });

            buttonEnter.addEventHandler(ActionEvent.ACTION, e ->
            {
                borderpanePayment.setDisable(true);
                borderpanePayment.setVisible(false);
                borderpaneNewOrder.setDisable(false);
                spinnerCustNo.getEditor().clear(); // remove spinner content
                textfieldPayment.clear(); // remove textfield content

                // TODO: at this point papasok na sa DB dapat

                // TODO: Dapat after nito magpapakita yung "Transaction complete!"
            });

            buttonPaymentClose.addEventHandler(ActionEvent.ACTION, e ->
            {
                borderpanePayment.setDisable(true);
                borderpanePayment.setVisible(false);
                borderpaneNewOrder.setDisable(false);
                spinnerCustNo.getEditor().clear(); // remove spinner content
                textfieldPayment.clear(); // remove textfield content
            });

            initialLoad = false;
        }

        loadMeals();
    }

    private void loadMeals()
    {
//        flowpaneBudget (Budget meals)
//        flowpaneCombo (Combo meals)
//        flowpaneSandwich (para sa Sandwich, Appetizer, Pasta 'to)
//        flowpaneExtras (Extras)

        DatabaseModel dbm = new DatabaseModel();
        ArrayList<Consumable> consumablesList = dbm.getConsumables();
    }
}
