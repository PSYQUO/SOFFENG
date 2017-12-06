package model.food;

/**
 * The representation of an item/ingredient in stock in the inventory.
 */
public class RawItem {
    public int rawItemID;
    private String name;
    private int quantity;
    private double price;

    public RawItem(String name, int quantity, double price) {
        this.rawItemID = -1;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public RawItem(int rawItemID, String name, int quantity, double price) {
        this.rawItemID = rawItemID;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public int getRawItemID(){
        return rawItemID;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity > 0)
            this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price > 0)
            this.price = price;
    }
}