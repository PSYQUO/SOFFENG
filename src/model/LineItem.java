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
}