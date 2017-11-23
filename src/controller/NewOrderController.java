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
import java.text.DecimalFormat;

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
    private List<LineItem> lineItems;
    private DoubleProperty changeProperty;
    
    private DecimalFormat df = new DecimalFormat("0.00");

    public NewOrderController() throws IOException
    {
        initialize(this, "/view/new-order", "/view/new-order");
    }

    @Override
    public void load() throws ViewManagerException
    {

        if(checkInitialLoad(getClass().getSimpleName()))
        {
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

            receiptTextArea = new TextArea();
            receiptTextArea.setMaxWidth(Double.MAX_VALUE);
            receiptTextArea.setMaxHeight(Double.MAX_VALUE);

            changeProperty = new SimpleDoubleProperty();
            // changeProperty.setValue(transactionBuilder.build().getTotal() - Double.parseDouble(textfieldPayment.getText()));
            // labelChange.textProperty().bind(changeProperty.asString());
            // labelChange.textProperty().bind(textfieldPayment.textProperty() - transactionBuilder.build().getTotal());

            // Attach event handlers for each button in the numpad
            for (Node n : gridpaneNumpad.getChildren()) {
                Button b = (Button) n;
                b.addEventHandler(ActionEvent.ACTION, e ->
                {
                    if (textfieldPayment.getText().equals("0"))
                        textfieldPayment.setText("");
                    textfieldPayment.setText(textfieldPayment.getText() + b.getText());
                    double total = transactionBuilder.build().getTotal();
                    if (checkboxSenior.isSelected())
                        total -= total * 0.20;
                    double change = Double.parseDouble(textfieldPayment.getText()) - total;
                    labelChange.setText(df.format(change));
                });
            }

            checkboxSenior.addEventHandler(ActionEvent.ACTION, e ->
            {
                // double total = transactionBuilder.build().getTotal();
                double total = transactionBuilder.build().getTotal();
                if (checkboxSenior.isSelected())
                    total -= total * 0.20;
                // double change = Double.parseDouble(textfieldPayment.getText()) - total;
                labelChange.setText(df.format((Double.parseDouble(textfieldPayment.getText()) - total)));
                labelTotal.setText(df.format(total));
            });

            buttonBack.addEventHandler(ActionEvent.ACTION, e ->
            {
                viewManager.switchViews("MainMenuController");
                clear();
            });

            buttonOK.addEventHandler(ActionEvent.ACTION, e ->
            {
                labelTotal.setText("");
                textfieldPayment.setText("0");
                Transaction tempTransaction = transactionBuilder.build();

                if (tempTransaction.getTotal() <= 0)
                    return;

                if (tempTransaction.getTotal() != -1)
                    labelTotal.setText(df.format(tempTransaction.getTotal()));

                double change = Double.parseDouble(textfieldPayment.getText()) - tempTransaction.getTotal();
                labelChange.setText(df.format(change));

                borderpanePayment.setDisable(false);
                borderpanePayment.setVisible(true);
                borderpaneNewOrder.setDisable(true);
            });

            buttonEnter.addEventHandler(ActionEvent.ACTION, e ->
            {
                double change = Double.parseDouble(labelChange.getText());
                double total = Double.parseDouble(labelTotal.getText());
                if (change < 0 || total <= 0)
                    return;

                transactionBuilder.setCashReceived(Double.parseDouble(textfieldPayment.getText()));
                transactionBuilder.setChange(Double.parseDouble(labelChange.getText()));
                transactionBuilder.setCustomerNo(spinnerCustNo.getValue());

                /**
                 * Dummy Values
                 */
                // transactionBuilder.addLineItem(new LineItem(1, new Consumable("Nixon", "nix", null, 4.20, null), 420));
                // transactionBuilder.addLineItem(new LineItem(1, new Consumable("Jordan", "nix", null, 69.69, null), 69));

                // receiptBuilder.clear();
                // Receipt receipt = receiptBuilder.processTransaction(transactionBuilder.build()).build();

                // System.out.println(receipt.customerReceipt());
                // System.out.println(receipt.kitchenReceipt());

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

                // borderpanePayment.setDisable(true);
                // borderpanePayment.setVisible(false);
                // borderpaneNewOrder.setDisable(false);
                // spinnerCustNo.getEditor().clear(); // remove spinner content
                // textfieldPayment.clear(); // remove textfield content
                
                viewManager.switchViews("MainMenuController");
                clear();

            });

            buttonBackspace.addEventHandler(ActionEvent.ACTION, e ->
            {
                if (textfieldPayment.getText().length() == 1)
                    textfieldPayment.setText("0");
                else
                    textfieldPayment.setText(
                        textfieldPayment.getText().substring(0, textfieldPayment.getText().length() - 1));

                double total = transactionBuilder.build().getTotal();
                if (checkboxSenior.isSelected())
                    total -= total * 0.20;
                double change = Double.parseDouble(textfieldPayment.getText()) - total;
                labelChange.setText(df.format(change) + "");
            });

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
            
            /* Disables the button when there are not enough ingredients. */
            List<Ingredient> ingredients = dbm.searchIngredientByConsumableID(c.consumableID);
            for (Ingredient i : ingredients) {
                if (i.getRawItem().getQuantity() < i.getQuantity())
                    nob.setDisable(true);
            }

            nob.addEventHandler(ActionEvent.ACTION, e ->
            {
                transactionBuilder.addLineItem(new LineItem(transactionId, c, 1));

                // Receipt building begin
                // receiptBuilder.clear();
                // receiptBuilder.processTransaction(transactionBuilder.build());

                // receipt = receiptBuilder.build();
                // Receipt building end

                // Update receipt sidepane
                // receiptTextArea.setText(receipt.customerReceipt());
                // vboxReceipt.getChildren().clear();
                // vboxReceipt.getChildren().add(receiptTextArea);
            });

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
