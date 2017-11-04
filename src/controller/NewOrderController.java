package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import model.Consumable;
import model.Ingredient;
import model.RawItem;
import model.DatabaseModel;
import view.NewOrderButton;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class NewOrderController extends Controller
{
    @FXML
    private BorderPane borderpanePayment, borderpaneNewOrder;

    @FXML
    private Button buttonOK, buttonEnter, buttonPaymentClose, buttonNewOrderClose;

    @FXML
    private Spinner<Integer> spinnerCustNo;

    @FXML
    private TextField textfieldPayment;

    @FXML
    private FlowPane flowpaneBudget, flowpaneCombo, flowpaneSandwich, flowpaneExtras;

    public NewOrderController() throws IOException
    {
        initialize(this, "/view/new-order", true);
    }

    @Override
    public void load() throws ViewManagerException
    {
        if(checkInitialLoad(getClass().getSimpleName()))
        {
            buttonNewOrderClose.addEventHandler(ActionEvent.ACTION, e ->
                    viewManager.switchViews("MainMenuController"));

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
        }


        loadMeals();
    }

    @Override
    public void clear()
    {

    }

    private void loadMeals()
    {
//      flowpaneBudget (Budget meals)
//      flowpaneCombo (Combo meals)
//      flowpaneSandwich (para sa Sandwich, Appetizer, Pasta 'to)
//      flowpaneExtras (Extras)

        DatabaseModel dbm = new DatabaseModel();
        ArrayList<Consumable> consumablesList = dbm.getConsumables();

        for(Consumable c : consumablesList)
        {
            NewOrderButton nob = new NewOrderButton(c.getName(), c.getPrice());
            
            /* Disables the button when there are not enough ingredients. */
            List<Ingredient> ingredients = dbm.searchIngredientByConsumable(c.consumableID);
            for (Ingredient i : ingredients) {
                if (i.getRawItem().getQuantity() < i.getQuantity())
                    nob.setDisable(true);
            }

            String category = c.getCategory().getCategoryName();

            if(category.equals("Budget Meal"))
                flowpaneBudget.getChildren().add(nob);
            else if(category.equals("Combo Meal"))
                flowpaneCombo.getChildren().add(nob);
            else if(category.equals("Sandwich") || category.equals("Appetizer") || category.equals("Pasta"))
                flowpaneSandwich.getChildren().add(nob);
            else
                flowpaneExtras.getChildren().add(nob);
        }
    }
}
