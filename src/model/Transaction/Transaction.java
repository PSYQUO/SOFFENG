package model.transaction;

import model.LineItem;
import model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;

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
    protected double tax;
    protected double discount;
    protected double total;
    protected ArrayList<LineItem> lineItems;
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

    public double getTax() {
        return tax;
    }

    public double getDiscount() {
        return discount;
    }

    public double getTotal() {
        return total;
    }

    public ArrayList<LineItem> getLineItems() {
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

    protected void setTax(double tax) {
        this.tax = tax;
    }

    protected void setDiscount(double discount) {
        this.discount = discount;
    }

    protected void setTotal(double total) {
        this.total = total;
    }

    protected void addLineItem(LineItem lineItem) {
        lineItems.add(lineItem);

        total += lineItem.getConsumable().getPrice()
               * lineItem.getQuantity();
    }
    
    protected void removeLineItem(LineItem lineItem) {
        lineItems.remove(lineItem);

        total -= lineItem.getConsumable().getPrice()
               * lineItem.getQuantity();
    }

    protected void setLineItems(ArrayList<LineItem> lineItems) {
        this.lineItems = lineItems;
        
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