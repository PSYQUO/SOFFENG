package model.Transaction;

import model.LineItem;
import model.User;

import java.time.LocalDateTime;
import java.util.List;

public class Transaction {
    public final int transactionID;
    protected LocalDateTime transactionDate;
    protected User cashier;
    protected TransactionMode mode;
    protected double cashReceived;
    protected double change;
    protected double tax;
    protected double discount;
    protected double total;
    protected List<LineItem> lineItems;
    protected int custNo;

    public Transaction() {
        transactionID = -1;
    }

    public Transaction(int transactionID) {
        this.transactionID = transactionID;
    }

    public int getTransactionID(){
        return transactionID;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public User getCashier() {
        return cashier;
    }

    public TransactionMode getMode() {
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

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public int getCustNo() {
        return custNo;
    }

    protected void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    protected void setCashier(User cashier) {
        this.cashier = cashier;
    }

    protected void setMode(TransactionMode mode) {
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
    }
    
    protected void removeLineItem(LineItem lineItem) {
        lineItems.remove(lineItem);
    }

    protected void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    protected void setCustNo(int custNo) {
        this.custNo = custNo;
    }

    public enum TransactionMode {
        DINE_IN,
        TAKE_OUT,
        DELIVERY
    }
}