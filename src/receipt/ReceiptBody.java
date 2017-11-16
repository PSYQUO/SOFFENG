package receipt;

import java.util.List;
import java.util.ArrayList;

import model.LineItem;

public class ReceiptBody {
    private double subTotal; // Setting this here just in case
    private double total; // Can be calculated
    private double payment; // Has to be set
    private double change; // Can be calculated given that payment has been set
    private List<LineItem> lineItems;

    public ReceiptBody() {
        lineItems = new ArrayList<LineItem>();
        change = -1;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public void setChange(double change) {
        this.change = change;
    }

    // public void computeTransaction() {
    //     change = payment - total;
    // }
    
    public void addLineItem(LineItem item) {
        lineItems.add(item);
        // total += item.getConsumable().getPrice()
        //        * item.getQuantity();
    }

    public void removeLineItem(LineItem item) {
        lineItems.remove(item);
        // total -= item.getConsumable().getPrice()
        //        * item.getQuantity();
    }

    public void setLineItems(List<LineItem> items) {
        lineItems.addAll(items);

        // for (LineItem li : lineItems)
        //     total += li.getConsumable().getPrice()
        //            * li.getQuantity();
    }

    public String customerItems() {
        // TODO
        // lineItems = new ArrayList<LineItem>();
        // String text = "";

        // for (int i = 0; i < lineItems.size(); i++) {
        //     text = text + lineItems.get(i);
        // }

        // return text;
        return null;
    }

    public String kitchenItems() {
        // TODO
        // lineItem = new ArrayList<LineItem>();
        // String text = "";

        // for (LineItem li : lineItems) {
        //     text += li.getConsumable().getName() + "    " 
        //           + li.getQuantity() + "    " 
        //           + li.getConsumable().getPrice() * li.getQuantity() + "\n";
        //     text += li.kitchenItem() + "\n";
        // }

        // return text;
        return null;
    }

    public String paymentInfo() {
        List<String> lines = new ArrayList<String>();
        lines.add(String.format("%37s\n", "--------"));
        //lines.add(String.format("-15S", "Item Sold 489"));
        lines.add(String.format("%1s\n", " "));
        lines.add(String.format("%-15S%21S\n", "Amount Tendered", payment + ""));
        lines.add(String.format("%-15S%21S\n", "Change", change + ""));

        String text = "";
        for (int i = 0; i < lines.size(); i++) {
            text = text + lines.get(i);
        }
        
        return text;
    }

}