package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class JoleneNewOrderController
{
	
	@FXML
    private ComboBox<String> comboName;

    @FXML
    private BorderPane borderpaneNewOrder;

    @FXML
    private Tab tabBudget;

    @FXML
    private FlowPane flowpaneBudget;

    @FXML
    private Tab tabCombo;

    @FXML
    private FlowPane flowpaneCombo;

    @FXML
    private Tab tabSandwich;

    @FXML
    private FlowPane flowpaneSandwich;

    @FXML
    private Tab tabExtras;

    @FXML
    private FlowPane flowpaneExtras;

    @FXML
    private ScrollPane scrollpaneSummary;

    @FXML
    private VBox vboxReceipt;

    @FXML
    private Button buttonOK;

    @FXML
    private Label labelSubtotal;

    @FXML
    private BorderPane borderpanePayment;

    @FXML
    private Button buttonPaymentClose;

    @FXML
    private Label labelTotal;

    @FXML
    private CheckBox checkboxSenior;

    @FXML
    private TextField textfieldPayment;

    @FXML
    private Spinner<Integer> spinnerCustNo;

    @FXML
    private Label labelChange;

    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Button button3;
    
    @FXML
    private Button button4;

    @FXML
    private Button button5;

    @FXML
    private Button button6;
    
    @FXML
    private Button button7;

    @FXML
    private Button button8;

    @FXML
    private Button button9;

    @FXML
    private Button button00;

    @FXML
    private Button button0;

    @FXML
    private Button buttonPeriod;

    @FXML
    private Button buttonBackspace;

    @FXML
    private Button buttonEnter;
    
    @FXML
    void initialize() {
        assert borderpaneNewOrder != null : "fx:id=\"borderpaneNewOrder\" was not injected: check your FXML file 'neworder.fxml'.";
        assert tabBudget != null : "fx:id=\"tabBudget\" was not injected: check your FXML file 'neworder.fxml'.";
        assert flowpaneBudget != null : "fx:id=\"flowpaneBudget\" was not injected: check your FXML file 'neworder.fxml'.";
        assert tabCombo != null : "fx:id=\"tabCombo\" was not injected: check your FXML file 'neworder.fxml'.";
        assert flowpaneCombo != null : "fx:id=\"flowpaneCombo\" was not injected: check your FXML file 'neworder.fxml'.";
        assert tabSandwich != null : "fx:id=\"tabSandwich\" was not injected: check your FXML file 'neworder.fxml'.";
        assert flowpaneSandwich != null : "fx:id=\"flowpaneSandwich\" was not injected: check your FXML file 'neworder.fxml'.";
        assert tabExtras != null : "fx:id=\"tabExtras\" was not injected: check your FXML file 'neworder.fxml'.";
        assert flowpaneExtras != null : "fx:id=\"flowpaneExtras\" was not injected: check your FXML file 'neworder.fxml'.";
        assert comboName != null : "fx:id=\"comboName\" was not injected: check your FXML file 'neworder.fxml'.";
        assert scrollpaneSummary != null : "fx:id=\"scrollpaneSummary\" was not injected: check your FXML file 'neworder.fxml'.";
        assert vboxReceipt != null : "fx:id=\"vboxReceipt\" was not injected: check your FXML file 'neworder.fxml'.";
        assert buttonOK != null : "fx:id=\"buttonOK\" was not injected: check your FXML file 'neworder.fxml'.";
        assert labelSubtotal != null : "fx:id=\"labelSubtotal\" was not injected: check your FXML file 'neworder.fxml'.";
        assert borderpanePayment != null : "fx:id=\"borderpanePayment\" was not injected: check your FXML file 'neworder.fxml'.";
        assert buttonPaymentClose != null : "fx:id=\"buttonPaymentClose\" was not injected: check your FXML file 'neworder.fxml'.";
        assert labelTotal != null : "fx:id=\"labelTotal\" was not injected: check your FXML file 'neworder.fxml'.";
        assert checkboxSenior != null : "fx:id=\"checkboxSenior\" was not injected: check your FXML file 'neworder.fxml'.";
        assert textfieldPayment != null : "fx:id=\"textfieldPayment\" was not injected: check your FXML file 'neworder.fxml'.";
        assert spinnerCustNo != null : "fx:id=\"spinnerCustNo\" was not injected: check your FXML file 'neworder.fxml'.";
        assert labelChange != null : "fx:id=\"labelChange\" was not injected: check your FXML file 'neworder.fxml'.";
        assert button1 != null : "fx:id=\"button1\" was not injected: check your FXML file 'neworder.fxml'.";
        assert button2 != null : "fx:id=\"button2\" was not injected: check your FXML file 'neworder.fxml'.";
        assert button3 != null : "fx:id=\"button3\" was not injected: check your FXML file 'neworder.fxml'.";
        assert button4 != null : "fx:id=\"button4\" was not injected: check your FXML file 'neworder.fxml'.";
        assert button5 != null : "fx:id=\"button5\" was not injected: check your FXML file 'neworder.fxml'.";
        assert button6 != null : "fx:id=\"button6\" was not injected: check your FXML file 'neworder.fxml'.";
        assert button7 != null : "fx:id=\"button7\" was not injected: check your FXML file 'neworder.fxml'.";
        assert button8 != null : "fx:id=\"button8\" was not injected: check your FXML file 'neworder.fxml'.";
        assert button9 != null : "fx:id=\"button9\" was not injected: check your FXML file 'neworder.fxml'.";
        assert button00 != null : "fx:id=\"button00\" was not injected: check your FXML file 'neworder.fxml'.";
        assert button0 != null : "fx:id=\"button0\" was not injected: check your FXML file 'neworder.fxml'.";
        assert buttonPeriod != null : "fx:id=\"buttonPeriod\" was not injected: check your FXML file 'neworder.fxml'.";
        assert buttonBackspace != null : "fx:id=\"buttonBackspace\" was not injected: check your FXML file 'neworder.fxml'.";
        assert buttonEnter != null : "fx:id=\"buttonEnter\" was not injected: check your FXML file 'neworder.fxml'.";
        
        // TODO Fetch stuff from database, instantiate NewOrderButtons here
    }
    
    /* 
     * OK button goes to Payment
     */
    @FXML
    public void buttonOkOnAction(ActionEvent event) {
    	borderpanePayment.setDisable(false);
    	borderpanePayment.setVisible(true);
    	borderpaneNewOrder.setDisable(true);
    }
    
    /*
     * Enter button (payment) goes back to New Order
     */
    @FXML
    public void buttonEnterOnAction(ActionEvent event) {
    	borderpanePayment.setDisable(true);
    	borderpanePayment.setVisible(false);
    	borderpaneNewOrder.setDisable(false);
    	spinnerCustNo.getEditor().clear(); // remove spinner content
    	textfieldPayment.clear(); // remove textfield content
    	
    	// TODO at this point papasok na sa DB dapat
    	
    	// TODO Dapat after nito magpapakita yung "Transaction complete!"
    }
    
    /*
     * Payment close button goes back to New Order
     */
    @FXML
    public void buttonPaymentCloseOnAction(ActionEvent event) {
    	borderpanePayment.setDisable(true);
    	borderpanePayment.setVisible(false);
    	borderpaneNewOrder.setDisable(false);
    	spinnerCustNo.getEditor().clear(); // remove spinner content
    	textfieldPayment.clear(); // remove textfield content
    }

    // TODO add functionality to number buttons
    
    // TODO add functionality to backspace button
}
