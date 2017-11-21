package model;

public class LineItem {
    private int transID;
    private Consumable consumable;
    private int quantity;
    
    public LineItem(int transID, Consumable consumable, int quantity) {
        this.transID = transID;
        this.consumable = consumable;
        this.quantity = quantity;
    }

    public Consumable getConsumable() {
        return consumable;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getTransID() {
        return transID;
    }

    public void increaseQuantity(int value) {
        if (value > 0)
            quantity += value;
    }

    public void decreaseQuantity(int value) {
        if (value > 0 && quantity - value > 0)
            quantity -= value;
    }

    public String getKitchenItem() {
        return String.format("%-15s%21s\n", consumable.getName(), quantity);
    }

    public String getCustomerItem() {
        return String.format("%-15s%5s%16s\n", consumable.getName(), quantity + "", consumable.getPrice());
    }
}