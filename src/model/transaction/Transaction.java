package model.transaction;

import model.food.LineItem;
import model.User;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents an actual order transaction. 
 * Contains the list of line items that are involved in the transaction, 
 * including the price, tax, ...etc. 
 * It also contains a reference to the operating cashier and data 
 * that determines the mode of transaction used.
 */
public class Transaction {
    
    public static final String MODE_DINE_IN = "Dine-in";
    public static final String MODE_TAKE_OUT = "Take-out";
    public static final String MODE_DELIVERY = "Delivery";

    public final int transactionID;
    
    protected LocalDateTime date;
    protected User cashier;
    protected String mode;
    protected double cashReceived;
    protected double change;
    protected double subTotal;
    protected double discount;
    protected double total;
    protected List<LineItem> lineItems;
    protected int customerNo;

    public Transaction() {
        transactionID = -1;
    }

    public Transaction(int transactionID) {
        this.transactionID = transactionID;
    }

    public int getTransactionID(){
        return transactionID;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public User getCashier() {
        return cashier;
    }

    public String getMode() {
        return mode;
    }

    public double getCashReceived() {
        return cashReceived;
    }

    public double getChange() {
        return change;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public double getDiscount() {
        return discount;
    }

    public double getTotal() {
        return total;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public int getCustomerNo() {
        return customerNo;
    }

    protected void setDate(LocalDateTime date) {
        this.date = date;
    }

    protected void setCashier(User cashier) {
        this.cashier = cashier;
    }

    protected void setMode(String mode) {
        this.mode = mode;
    }

    protected void setCashReceived(double cashReceived) {
        this.cashReceived = cashReceived;
    }

    protected void setChange(double change) {
        this.change = change;
    }

    protected void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    protected void setDiscount(double discount) {
        this.discount = discount;
    }

    protected void setTotal(double total) {
        this.total = total;
    }

    /**
     * Adds a line item to the transaction's current list of line items.
     * Also automatically adds the total price of the line item to the
     * transaction's current total.
     *
     * @param lineItem The line item to be added to the list.
     */
    protected void addLineItem(LineItem lineItem) {
        lineItems.add(lineItem);

        total += lineItem.getConsumable().getPrice()
               * lineItem.getQuantity();
    }
    
    /**
     * Removes a line item from the transaction's current list of line items.
     * Also automatically deducts the total price of the line item from the
     * transaction's current total.
     *
     * @param lineItem The line item to be removed from the list.
     */
    protected void removeLineItem(LineItem lineItem) {
        lineItems.remove(lineItem);

        total -= lineItem.getConsumable().getPrice()
               * lineItem.getQuantity();
    }

    protected void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
        
        total = 0;
        for (LineItem li : lineItems)
            total += li.getConsumable().getPrice()
                   * li.getQuantity();
    }
    
    public void computeTransaction() {
        change = cashReceived - total;
    }

    protected void setCustomerNo(int customerNo) {
        this.customerNo = customerNo;
    }

}