package controller;

import controller.ViewManager.ViewManagerException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.CheckBox;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import model.Consumable;
import model.Ingredient;
import model.DatabaseModel;
import model.transaction.Transaction;
import model.transaction.TransactionBuilder;
import model.LineItem;
import model.User;

import view.NewOrderButton;

import receipt.Receipt;
import receipt.ReceiptHeader;
import receipt.ReceiptFooter;
import receipt.ReceiptBuilder;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class NewOrderController extends Controller
{
    @FXML
    private BorderPane borderpanePayment, borderpaneNewOrder;

    @FXML
    private Button buttonOK, buttonEnter, buttonPaymentClose, buttonBack, buttonBackspace;

    @FXML
    private CheckBox checkboxSenior;

    @FXML
    private Spinner<Integer> spinnerCustNo;

    @FXML
    private TextField textfieldPayment;

    private TextArea receiptTextArea;

    @FXML
    private Label labelTotal;

    @FXML
    private Label labelChange;

    @FXML
    private FlowPane flowpaneBudget, flowpaneCombo, flowpaneSandwich, flowpaneExtras;

    @FXML
    private VBox vboxReceipt;

    @FXML
    private GridPane gridpaneNumpad;

    private ReceiptBuilder receiptBuilder;
    private TransactionBuilder transactionBuilder;

    private Receipt receipt;

    private int transactionId;
    private int customerNo;
    private String transactionMode;
    private User cashier;
    private List<LineItem> lineItems; // Line items of the current order

    public NewOrderController() throws IOException
    {
        initialize(this, "/view/new-order", "/view/new-order");
    }

    @Override
    public void load() throws ViewManagerException
    {
        if(checkInitialLoad(getClass().getSimpleName()))
        {
            // Temporary hard-coded data
            transactionId = 10;
            customerNo = 2;
            transactionMode = Transaction.MODE_DINE_IN;
            cashier = new User("Bob", "bobthebuilder", "builder", null);

            lineItems = new ArrayList<LineItem>();
            transactionBuilder = new TransactionBuilder(transactionId);
            receiptBuilder = new ReceiptBuilder();

            transactionBuilder.setCustomerNo(customerNo)
                              .setMode(transactionMode)
                              .setCashier(cashier)
                              .setDate(LocalDateTime.now());

            // Attach event handlers for each button in the numpad
            for (Node n : gridpaneNumpad.getChildren()) {
                Button b = (Button) n;
                b.addEventHandler(ActionEvent.ACTION, e ->
                {
                    // Remove the 0 to clear the field for user input.
                    if (textfieldPayment.getText().equals("0"))
                        textfieldPayment.setText("");

                    // Add to the payment input the value of the button's texts.
                    textfieldPayment.setText(textfieldPayment.getText() + b.getText());

                    // Get the current total of the transaction.
                    double total = transactionBuilder.build().getTotal();

                    // Apply the senior citizen discount if selected.
                    if (checkboxSenior.isSelected())
                        total -= total * 0.20;

                    // Calculate the change given a payment and a total.
                    double change = Double.parseDouble(textfieldPayment.getText()) - total;
                    labelChange.setText(change + "");
                });
            }

            checkboxSenior.addEventHandler(ActionEvent.ACTION, e ->
            {
                // Get the current total of the transaction.
                double total = transactionBuilder.build().getTotal();
                
                // Apply the senior citizen discount if selected.
                if (checkboxSenior.isSelected())
                    total -= total * 0.20;
                
                // Calculate the change given a payment and a total.
                labelChange.setText((Double.parseDouble(textfieldPayment.getText()) - total) + "");
                labelTotal.setText(total + "");
            });

            // The x button
            buttonBack.addEventHandler(ActionEvent.ACTION, e ->
            {
                viewManager.switchViews("MainMenuController");
                clear();
            });

            // Display the payment screen after ordering is finished.
            buttonOK.addEventHandler(ActionEvent.ACTION, e ->
            {
                labelTotal.setText("");
                textfieldPayment.setText("0");
                Transaction tempTransaction = transactionBuilder.build();

                // If the total is not invalid/null/uninitialized, display the total.
                if (tempTransaction.getTotal() != -1)
                    labelTotal.setText(tempTransaction.getTotal() + "");

                // Calculate the change given a payment and a total.
                double change = Double.parseDouble(textfieldPayment.getText()) - tempTransaction.getTotal();
                labelChange.setText(change + "");

                borderpanePayment.setDisable(false);
                borderpanePayment.setVisible(true);
                borderpaneNewOrder.setDisable(true);
            });

            // Finalize transaction.
            buttonEnter.addEventHandler(ActionEvent.ACTION, e ->
            {
                double change = Double.parseDouble(labelChange.getText());
                if (change < 0)
                    return; // If change is negative, cancel further computations.

                transactionBuilder.setCashReceived(Double.parseDouble(textfieldPayment.getText()));
                transactionBuilder.setChange(Double.parseDouble(labelChange.getText()));
                transactionBuilder.setCustomerNo(spinnerCustNo.getValue());

                // TODO: at this point papasok na sa DB dapat

                // dbm.addTransaction(transactionBuilder.build());

                // Decrease the inventory stocks after the transaction.
                // RawItem rawItem;
                // for (LineItem li : transaction.getLineItems()) {
                //     for (Ingredient i : li.getConsumable().getIngredients()) {
                //         rawItem = dbm.searchRawItem(i.getRawItem().rawItemID);
                //         rawItem.setQuantity(rawItem.getQuantity() - i.getQuantity());
                //         dbm.updateRawItem(rawItem);
                //     }
                // }

                // TODO: Dapat after nito magpapakita yung "Transaction complete!"

                borderpanePayment.setDisable(true);
                borderpanePayment.setVisible(false);
                borderpaneNewOrder.setDisable(false);
                spinnerCustNo.getEditor().clear(); // remove spinner content
                textfieldPayment.clear(); // remove textfield content

            });

            // Backspace for payment input.
            buttonBackspace.addEventHandler(ActionEvent.ACTION, e ->
            {
                // Override backspace function with set text to 0 if the current text is 1 character.
                if (textfieldPayment.getText().length() == 1)
                    textfieldPayment.setText("0");
                else
                    textfieldPayment.setText(
                        textfieldPayment.getText().substring(0, textfieldPayment.getText().length() - 1));

                // Get the current total of the transaction.
                double total = transactionBuilder.build().getTotal();
                if (checkboxSenior.isSelected())
                    total -= total * 0.20;
                
                // Calculate the change given a payment and a total.
                double change = Double.parseDouble(textfieldPayment.getText()) - total;
                labelChange.setText(change + "");
            });

            // Cancel payment input
            buttonPaymentClose.addEventHandler(ActionEvent.ACTION, e ->
            {
                borderpanePayment.setDisable(true);
                borderpanePayment.setVisible(false);
                borderpaneNewOrder.setDisable(false);
                spinnerCustNo.getEditor().clear(); // remove spinner content
                textfieldPayment.clear(); // remove textfield content
            });

            // ActionEvent cancelOrderHandler = ActionEvent.ACTION, e ->
            // {
                // TODO
                // Get the LineItem assigned to the event source button
                // Remove the LineItem from the current list of line items
                // lineItems.remove();
            // }
        }

        loadMeals();
    }

    @Override
    public void clear()
    {
        flowpaneBudget.getChildren().clear();
        flowpaneExtras.getChildren().clear();
        flowpaneCombo.getChildren().clear();
        flowpaneSandwich.getChildren().clear();
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
            
            // Disables the button when there are not enough ingredients.
            List<Ingredient> ingredients = dbm.searchIngredientByConsumableID(c.consumableID);
            for (Ingredient i : ingredients) {
                if (i.getRawItem().getQuantity() < i.getQuantity())
                    nob.setDisable(true);
            }

            // When an order button is clicked.
            nob.addEventHandler(ActionEvent.ACTION, e ->
            {
                transactionBuilder.addLineItem(new LineItem(transactionId, c, 1));
            });

            String category = c.getCategory().getCategoryName();

            // Segregate the food items by category tabs.
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
