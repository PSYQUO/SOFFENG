package model;

import java.time.LocalDateTime;
import java.util.List;

public class Transaction {
    public int transactionID;
    private LocalDateTime transactionDate;
    private User cashier;
    private TransactionMode mode;
    private double cashReceived;
    private double change;
    private double tax;
    private double discount;
    private double total;
    private List<LineItem> lineItems;
    private int custNo;

    public Transaction(LocalDateTime transactionDate, User cashier, TransactionMode mode, double cashReceived, double change, double tax, double discount, double total, List<LineItem> lineItems, int custNo) {
        this.transactionID = -1;
        this.transactionDate = transactionDate;
        this.cashier = cashier;
        this.mode = mode;
        this.cashReceived = cashReceived;
        this.change = change;
        this.discount = discount;
        this.total = total;
        this.lineItems = lineItems;
        this.custNo = custNo;
    }

    public Transaction(int transactionID, LocalDateTime transactionDate, User cashier, TransactionMode mode, double cashReceived, double change, double discount, double total, List<LineItem> lineItems, int custNo) {
        this.transactionID = transactionID;
        this.transactionDate = transactionDate;
        this.cashier = cashier;
        this.mode = mode;
        this.cashReceived = cashReceived;
        this.change = change;
        this.discount = discount;
        this.total = total;
        this.lineItems = lineItems;
        this.custNo = custNo;
    }

    public int getTransactionID()
    {
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

    public enum TransactionMode {
        DINE_IN,
        TAKE_OUT,
        DELIVERY
    }
}