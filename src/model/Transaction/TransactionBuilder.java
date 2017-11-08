package model.transaction;

import model.LineItem;
import model.User;
import model.transaction.Transaction.TransactionMode;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionBuilder {
    private TransactionInBuilding transaction;

    public TransactionBuilder() {
        transaction = new TransactionInBuilding();
    }

    public TransactionBuilder(int transactionID) {
        transaction = new TransactionInBuilding(transactionID);
    }

    public TransactionBuilder setTransactionDate(LocalDateTime transactionDate) {
        transaction.setTransactionDate(transactionDate);
        return this;
    }

    public TransactionBuilder setCashier(User cashier) {
        transaction.setCashier(cashier);
        return this;
    }

    public TransactionBuilder setMode(TransactionMode mode) {
        transaction.setMode(mode);
        return this;
    }

    public TransactionBuilder setCashReceived(double cashReceived) {
        transaction.setCashReceived(cashReceived);
        return this;
    }

    public TransactionBuilder setChange(double change) {
        transaction.setChange(change);
        return this;
    }

    public TransactionBuilder setTax(double tax) {
        transaction.setTax(tax);
        return this;
    }

    public TransactionBuilder setDiscount(double discount) {
        transaction.setDiscount(discount);
        return this;
    }
    
    public TransactionBuilder setTotal(double total) {
        transaction.setTotal(total);
        return this;
    }

    public TransactionBuilder addLineItem(LineItem lineItem) {
        transaction.addLineItem(lineItem);
        return this;
    }

    public TransactionBuilder removeLineItem(LineItem lineItem) {
        transaction.removeLineItem(lineItem);
        return this;
    }

    public TransactionBuilder setLineItems(List<LineItem> lineItems) {
        transaction.setLineItems(lineItems);
        return this;
    }

    public TransactionBuilder setCustNo(int custNo) {
        transaction.setCustNo(custNo);
        return this;
    }

    public Transaction build() {
        if (transaction.getTotal() == -1) {
            double total = 0;
            for (LineItem li : transaction.getLineItems())
                total += li.getConsumable().getPrice();
            transaction.setTotal(total);
        }

        // if (transaction.getChange() == -1 
        //     && transaction.getTotal() != -1 && transaction.getCashReceived() != -1)
        //     transaction.setChange(transaction.get)

        return transaction;
    }

    private class TransactionInBuilding extends Transaction {
        public TransactionInBuilding() {
            super(-1);
            cashReceived = -1;
            change = -1;
            total = -1;
            lineItems = new ArrayList<LineItem>();
            custNo = -1;
        }
        
        public TransactionInBuilding(int transactionID) {
            super(transactionID);
            cashReceived = -1;
            change = -1;
            total = -1;
            lineItems = new ArrayList<LineItem>();
            custNo = -1;
        }
    }
}