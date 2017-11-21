package receipt;

import java.util.*;

import model.LineItem;

public class ReceiptBody
{
    private static String separator = "------------------------------------";
    private double discount;
    private double subTotal; // Setting this here just in case
    private double total; // Can be calculated
    private double payment; // Has to be set
    private double change; // Can be calculated given that payment has been set
    private ArrayList<LineItem> lineItems;

    public ReceiptBody()
    {
        lineItems = new ArrayList<LineItem>();
        change = -1;
    }

    public void setTotal(double total)
    {
        this.total = total;
    }

    public void setSubTotal(double subTotal)
    {
        this.subTotal = subTotal;
    }

    public void setPayment(double payment)
    {
        this.payment = payment;
    }

    public void setChange(double change)
    {
        this.change = change;
    }

    public void setDiscount(double discount)
    {
        this.discount = discount;
    }
    
    public void addLineItem(LineItem item)
    {
        lineItems.add(item);
    }

    public void removeLineItem(LineItem item)
    {
        lineItems.remove(item);
    }

    public void setLineItems(ArrayList<LineItem> items)
    {
        lineItems.addAll(items);
    }

    public String customerItems()
    {
        String text = "";
        for (LineItem li : lineItems)
        {
            text = text + li.getCustomerItem();
        }

        return text;
    }

    public String kitchenItems()
    {
        String text = "";
        for (LineItem li : lineItems)
        {
            text = text + li.getKitchenItem();
        }

        return text;
    }

    public String paymentInfo()
    {
        ArrayList<String> lines = new ArrayList<String>();
        
        lines.add(String.format("%35S\n", separator));
        lines.add(String.format("%-15s%21S\n", "Subtotal", subTotal));
        lines.add(String.format("%-15s%21S\n", "Discount", discount));
        lines.add(String.format("%36s\n", "--------"));
        //lines.add(String.format("%1s\n", " "));
        lines.add(String.format("%-15s%21S\n", "Total", total));
        lines.add(String.format("%-15s%21S\n", "Amount Tendered", payment));
        lines.add(String.format("%-15s%21S\n", "Change", change));

        String text = "";
        for (int i = 0; i < lines.size(); i++)
        {
            text = text + lines.get(i);
        }
        
        return text;
    }

}