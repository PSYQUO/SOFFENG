package receipt;

public class ReceiptBuilder {
    private Receipt receipt;
    private Items items;

    public ReceiptBuilder() {
        receipt = new Receipt();
        items = new Items();
    }

    public ReceiptBuilder addHeader(Header header) {
        receipt.setHeader(header);
        return this;
    }

    public ReceiptBuilder addItem(ReceiptItem item) {
        items.addItem(item);
        return this;
    }

    // public ReceiptBuilder addPayment(double payment) {
        
    // }

    public ReceiptBuilder addFooter(Footer footer) {
        receipt.setFooter(footer);
        return this;
    }

    public Receipt build() {
        receipt.setItems(items);
        return receipt;
    }
}