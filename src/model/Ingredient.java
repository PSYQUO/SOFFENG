package model;

public class Ingredient {
    private RawItem rawItem;
    private int quantity;

    public Ingredient(RawItem rawItem, int quantity) {
        this.rawItem = rawItem;
        this.quantity = quantity;
    }

    public RawItem getRawItem() {
        return rawItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}