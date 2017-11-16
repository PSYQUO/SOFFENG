package controller;

import controller.ViewManager.ViewManagerException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
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
import receipt.Header;
import receipt.Footer;
import receipt.ReceiptItem;
import receipt.ReceiptBuilder;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    
    private TextArea receiptTextArea;

    @FXML
    private FlowPane flowpaneBudget, flowpaneCombo, flowpaneSandwich, flowpaneExtras;

    @FXML
    private VBox vboxReceipt;

    private ReceiptBuilder receiptBuilder;
    private TransactionBuilder transactionBuilder;

    private Receipt receipt;

    private int transactionId;
    private int customerNo;
    private Transaction.TransactionMode transactionMode;
    private User cashier;
    private List<LineItem> lineItems;

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
            transactionMode = Transaction.TransactionMode.DINE_IN;
            cashier = new User("Bob", "bobthebuilder", "builder", null);
            lineItems = new ArrayList<LineItem>();
            transactionBuilder = new TransactionBuilder();
            
            receiptTextArea = new TextArea();
            // receiptTextArea.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
            receiptTextArea.setMaxWidth(Double.MAX_VALUE);
            receiptTextArea.setMaxHeight(Double.MAX_VALUE);

            buttonNewOrderClose.addEventHandler(ActionEvent.ACTION, e ->
            {
                viewManager.switchViews("MainMenuController");
                clear();
            });

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
                /* Check if selected order is already in the list */
                boolean duplicate = false;
                for (LineItem li : lineItems) {
                    if (li.getConsumable().getName().equals(nob.getName())) {
                        li.increaseQuantity(1);
                        duplicate = true;
                        break;
                    }
                }
                if (!duplicate)
                    lineItems.add(new LineItem(transactionId, c, 1));
                
                receiptBuilder = new ReceiptBuilder();
                for (LineItem li : lineItems) {
                    receiptBuilder.addItem(new ReceiptItem(li.getConsumable().getName(), 
                                                           li.getQuantity(), 
                                                           li.getConsumable().getPrice() * li.getQuantity()));
                }

                Header header = new Header(transactionMode.toString(), transactionId, customerNo);

                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                Footer footer = new Footer(cashier.getusername(), dtf.format(now)); // 2016/11/16 12:08:43

                receiptBuilder.addHeader(header);
                receiptBuilder.addFooter(footer);

                receipt = receiptBuilder.build();

                receiptTextArea.setText(receipt.customerReceipt());
                // Label entry = new Label(c.getName() + "1" + c.getPrice());
                vboxReceipt.getChildren().clear();
                vboxReceipt.getChildren().add(receiptTextArea);
                // vboxReceipt.getChildren().add(entry);
                // receiptBuilder.addItem(new ReceiptItem(c.getName(), c.getQuantity(), c.getPrice()));
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
