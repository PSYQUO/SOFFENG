package receipt;

public class ReceiptItem {
    private String name;
    private int qty;
    private double price;

    public ReceiptItem(String name, int qty, double price) {
        this.name = name;
        this.qty = qty;
        this.price = price;
    }

    public String kitchenItem() {
        return String.format("%-15s%21s", name, qty);
    }

    public String customerItem() {
        return String.format("%-15s%5s%16s", name, qty+"", price+"");
    }
}