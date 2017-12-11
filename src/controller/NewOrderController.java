package controller;

import controller.viewmanager.ViewManagerException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.CheckBox;
import javafx.scene.control.SplitPane;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import model.food.Consumable;
import model.food.Ingredient;
import model.DatabaseModel;
import model.transaction.Transaction;
import model.transaction.TransactionBuilder;
import model.food.LineItem;
import model.User;

import view.NewOrderButton;
import view.LineItemBox;

import receipt.Receipt;
import receipt.ReceiptBuilder;
import receipt.ReceiptPrinter;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.text.DecimalFormat;

import javafx.beans.property.DoubleProperty;

public class NewOrderController extends Controller
{
    @FXML
    private BorderPane borderpanePayment;
    
    @FXML
    private SplitPane splitpaneNewOrder;

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
    private Label labelSubtotal;

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

    public NewOrderController(String fxmlpath, String csspath) throws IOException
    {
        super(fxmlpath, csspath);
    }

    @Override
    public void load() throws ViewManagerException
    {
        // Temporary hard-coded data
        transactionId = 10;
        customerNo = 2;
        transactionMode = Transaction.MODE_DINE_IN;
        cashier = new User("Bob", "bobthebuilder", "builder", null);

        lineItems = new ArrayList<LineItem>();
        transactionBuilder = new TransactionBuilder(transactionId);
        receiptBuilder = new ReceiptBuilder();
        labelChange.setText("");
        labelTotal.setText("");
        textfieldPayment.setText("0");
        spinnerCustNo.getValueFactory().setValue(1);
        vboxReceipt.getChildren().clear();
        labelSubtotal.setText("0.00");
        
        transactionBuilder.setCustomerNo(customerNo)
                            .setMode(transactionMode)
                            .setCashier(cashier)
                            .setDate(LocalDateTime.now());

        if(isFirstLoad())
        {

            textfieldPayment.setEditable(false);

            // Attach event handlers for each button in the numpad
            for (Node n : gridpaneNumpad.getChildren()) {
                Button b = (Button) n;
                b.addEventHandler(ActionEvent.ACTION, e ->
                {
                    if (b.getText().equals(".")) {
                        if (textfieldPayment.getText().contains("."))
                            return;
                    }

                    if (textfieldPayment.getText().length() >= 6)
                        return;

                    if (textfieldPayment.getText().equals("0") && !b.getText().equals("."))
                        textfieldPayment.setText("");
                    
                    textfieldPayment.setText(textfieldPayment.getText() + b.getText());

                    if (Integer.parseInt(textfieldPayment.getText()) == 0)
                        textfieldPayment.setText("0");

                    double total = transactionBuilder.build().getTotal();

                    if (checkboxSenior.isSelected())
                        total -= total * 0.20;

                    double change = Double.parseDouble(textfieldPayment.getText()) - total;
                    labelChange.setText(df.format(change));
                });
            }

            checkboxSenior.addEventHandler(ActionEvent.ACTION, e ->
            {
                // Get the current total of the transaction.
                double total = transactionBuilder.build().getTotal();
                
                // Apply the senior citizen discount if selected.
                if (checkboxSenior.isSelected())
                    total -= total * 0.20;
                // double change = Double.parseDouble(textfieldPayment.getText()) - total;
                labelChange.setText(df.format((Double.parseDouble(textfieldPayment.getText()) - total)));
                labelTotal.setText(df.format(total));
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

                if (tempTransaction.getTotal() <= 0)
                    return;
                
                checkboxSenior.setSelected(false);

                if (tempTransaction.getTotal() != -1)
                    labelTotal.setText(df.format(tempTransaction.getTotal()));

                // Calculate the change given a payment and a total.
                double change = Double.parseDouble(textfieldPayment.getText()) - tempTransaction.getTotal();
                labelChange.setText(df.format(change));

                borderpanePayment.setDisable(false);
                borderpanePayment.setVisible(true);
                splitpaneNewOrder.setDisable(true);
            });

            // Finalize transaction.
            buttonEnter.addEventHandler(ActionEvent.ACTION, e ->
            {
                double change = Double.parseDouble(labelChange.getText());
                double total = Double.parseDouble(labelTotal.getText());
                if (change < 0 || total <= 0)
                    return;

                try {
                    transactionBuilder.setCustomerNo(Integer.parseInt(spinnerCustNo.getEditor().getText()));
                } catch (NumberFormatException nfe) {
                    return;
                }

                transactionBuilder.setSubTotal(transactionBuilder.build().getTotal());
                transactionBuilder.setTotal(total);
                // System.out.println(total - transactionBuilder.build().getSubTotal());
                double discount = (total - transactionBuilder.build().getSubTotal());
                if (discount < 0)
                    discount *= -1;
                transactionBuilder.setDiscount(discount);
                transactionBuilder.setCashReceived(Double.parseDouble(textfieldPayment.getText()));
                transactionBuilder.setChange(Double.parseDouble(labelChange.getText()));

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
                receiptBuilder.clear();
                Receipt receipt = receiptBuilder.processTransaction(transactionBuilder.build()).build();
                //System.out.println(receipt.customerReceipt());
                //System.out.println(receipt.kitchenReceipt());

                System.out.println(receipt.customerReceipt()+"\n"+receipt.kitchenReceipt());
                ReceiptPrinter rp = new ReceiptPrinter();
                //rp.printReceipt(receipt.customerReceipt());
                rp.printReceipt(receipt.customerReceipt()+"\n"+receipt.kitchenReceipt());



                // TODO: Dapat after nito magpapakita yung "Transaction complete!"

                borderpanePayment.setDisable(true);
                borderpanePayment.setVisible(false);
                splitpaneNewOrder.setDisable(false);
                // spinnerCustNo.getEditor().clear(); // remove spinner content
                textfieldPayment.clear(); // remove textfield content
                
                viewManager.switchViews("MainMenuController");
                clear();

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
                labelChange.setText(df.format(change) + "");
            });

            // Cancel payment input
            buttonPaymentClose.addEventHandler(ActionEvent.ACTION, e ->
            {
                borderpanePayment.setDisable(true);
                borderpanePayment.setVisible(false);
                splitpaneNewOrder.setDisable(false);
                spinnerCustNo.getEditor().clear(); // remove spinner content
                // spinnerCustNo.getValueFactory().setValue(1);
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
            NewOrderButton nob = new NewOrderButton(c);
            
            // Disables the button when there are not enough ingredients.
            List<Ingredient> ingredients = dbm.searchIngredientByConsumableID(c.consumableID);
            for (Ingredient i : ingredients) {
                if (i.getRawItem().getQuantity() < i.getQuantity())
                    nob.setDisable(true);
            }

            // When an order button is clicked.
            nob.addEventHandler(ActionEvent.ACTION, e ->
            {
                LineItem lineItem = new LineItem(transactionId, c, 1);
                transactionBuilder.addLineItem(lineItem);
                // addLineItem(lineItems, lineItem);
                refreshSummary();
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

    public void refreshSummary() {
        List<LineItem> lineItems = transactionBuilder.build().getLineItems();
        vboxReceipt.getChildren().clear();

        for (LineItem li : lineItems) {
            LineItemBox lib = new LineItemBox(li);
            
            if (li.getQuantity() <= 1)
                lib.getSubtractButton().setDisable(true);

            lib.addEventHandler(ActionEvent.ACTION, e -> {
                // LineItem libLi = ((LineItemBox)e2.getSource()).getLineItem();
                List<LineItem> lineItems2 = transactionBuilder.build().getLineItems();
                LineItem libLi = lib.getLineItem();
                int index = lineItems2.indexOf(libLi);

                Button buttonSubtract = lib.getSubtractButton();

                // int statusFlag = ((LineItemBox)e2.getSource()).getStatusFlag();
                int statusFlag = lib.getStatusFlag();

                switch (statusFlag) {
                    case LineItemBox.MARK_FOR_INCREASE:
                        lineItems2.get(index).increaseQuantity(1);
                        if (lineItems2.get(index).getQuantity() > 1 && buttonSubtract.isDisabled())
                            buttonSubtract.setDisable(false);
                        lib.setStatusFlag(LineItemBox.DEFAULT);
                        break;
                    case LineItemBox.MARK_FOR_DECREASE:
                        if (lineItems2.get(index).getQuantity() <= 1) {
                            // lineItems.remove(index);
                            if (!buttonSubtract.isDisabled())
                                buttonSubtract.setDisable(true);
                        }
                        else
                            lineItems2.get(index).decreaseQuantity(1);
                        lib.setStatusFlag(LineItemBox.DEFAULT);
                        break;
                    case LineItemBox.MARK_FOR_DELETE:
                        // lineItems.remove(index);
                        lineItems2.remove(index);
                        lib.setStatusFlag(LineItemBox.DEFAULT);
                        break;
                    case LineItemBox.DEFAULT:
                        break;
                }
                transactionBuilder.setLineItems(lineItems2);
                refreshSummary();
            });

            vboxReceipt.getChildren().add(lib);
        }
        labelSubtotal.setText(df.format(transactionBuilder.build().getTotal()));
    }

    public void addLineItem(List<LineItem> lineItems, LineItem lineItem) {
        // Check if the selected line item is already in the list of line items
        boolean duplicate = false;
        for (LineItem li : lineItems) {
            if (li.getConsumable().getName().equals(lineItem.getConsumable().getName())) {
                li.increaseQuantity(1);
                duplicate = true;
                break;
            }
        }
        if (!duplicate) {
            lineItems.add(lineItem);
        }
    }
}
