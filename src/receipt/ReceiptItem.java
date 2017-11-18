package receipt;

// Unless someone can justify the advantage of using this over
// LineItem then this class should not be used.
public class ReceiptItem {
    private String name;
    private int quantity;
    private double price;

    public ReceiptItem(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String kitchenItem() {
        return String.format("%-15s%21s", name, quantity);
    }

    public String customerItem() {
        return String.format("%-15s%5s%16s", name, quantity + "", price + "");
    }
}