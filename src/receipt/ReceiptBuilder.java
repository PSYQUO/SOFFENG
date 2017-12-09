package receipt;

import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import model.transaction.Transaction;
import model.food.LineItem;

public class ReceiptBuilder
{
    private Receipt receipt;
    private ReceiptHeader header;
    private ReceiptBody body;
    private ReceiptFooter footer;

    public ReceiptBuilder()
    {
        receipt = new Receipt();
        header = new ReceiptHeader();
        body = new ReceiptBody();
        footer = new ReceiptFooter();
    }

    public ReceiptBuilder processTransaction(Transaction transaction)
    {
        if (transaction.getLineItems() != null)
            setLineItems(transaction.getLineItems());
        if (transaction.getCashReceived() > -1)
            setPayment(transaction.getCashReceived());
        if (transaction.getChange() > -1)
            setChange(transaction.getChange());
        if (transaction.getTotal() > -1)
            setTotal(transaction.getTotal());
        if (transaction.getSubTotal() > -1)
            setSubTotal(transaction.getSubTotal());
        if (transaction.getDiscount() > -1)
            setDiscount(transaction.getDiscount());
        if (transaction.getMode() != null)
            setTransactionMode(transaction.getMode());
        if (transaction.getTransactionID() > -1)
            setTransactionNo(transaction.getTransactionID());
        if (transaction.getCustomerNo() > -1)
            setCustomerNo(transaction.getCustomerNo());
        if (transaction.getCashier().getUsername() != null)
            setCashierName(transaction.getCashier().getUsername());
        setTransactionDate(LocalDateTime.now());
        return this;
    }

    // ReceiptHeader methods
    public ReceiptBuilder setTransactionMode(String transactionMode)
    {
        header.setTransactionMode(transactionMode);
        return this;
    }

    public ReceiptBuilder setTransactionNo(int transactionNo)
    {
        header.setTransactionNo(transactionNo);
        return this;
    }

    public ReceiptBuilder setCustomerNo(int customerNo)
    {
        header.setCustomerNo(customerNo);
        return this;
    }

    // ReceiptBody methods
    public ReceiptBuilder setTotal(double total)
    {
        body.setTotal(total);
        return this;
    }

    public ReceiptBuilder setSubTotal(double subTotal)
    {
        body.setSubTotal(subTotal);
        return this;
    }

    public ReceiptBuilder setDiscount(double discount)
    {
        body.setDiscount(discount);
        return this;
    }

    public ReceiptBuilder setPayment(double payment)
    {
        body.setPayment(payment);
        return this;
    }

    public ReceiptBuilder setChange(double change)
    {
        body.setChange(change);
        return this;
    }

    public ReceiptBuilder addLineItem(LineItem item)
    {
        body.addLineItem(item);
        return this;
    }
    
    public ReceiptBuilder removeLineItem(LineItem item)
    {
        body.removeLineItem(item);
        return this;
    }
    
    public ReceiptBuilder setLineItems(List<LineItem> items)
    {
        body.setLineItems(items);
        return this;
    }

    // ReceiptFooter methods
    public ReceiptBuilder setCashierName(String cashierName)
    {
        footer.setCashierName(cashierName);
        return this;
    }

    public ReceiptBuilder setTransactionDate(LocalDateTime transactionDate)
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        if (transactionDate != null)
        {
            footer.setTransactionDate(dtf.format(transactionDate));
        }
        else
        {
            footer.setTransactionDate("");
        }
        return this;
    }

    public void clear()
    {
        // There are two methods of clearing the builder here
        // Which do you guys suppose is more optimal?

        this.setTransactionMode("")
            .setTransactionNo(-1)
            .setCustomerNo(-1)
            .setPayment(-1)
            .setLineItems(new ArrayList<LineItem>())
            .setCashierName("")
            .setTransactionDate(null);
        
        // receipt = new Receipt();
        // header = new ReceiptHeader();
        // body = new ReceiptBody();
        // footer = new ReceiptFooter();
    }

    // Build and return the receipt
    public Receipt build()
    {
        receipt.setHeader(header);
        receipt.setBody(body);
        receipt.setFooter(footer);

        return receipt;
    }
}