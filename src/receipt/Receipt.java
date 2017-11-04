package receipt;

public class Receipt{
    private Header header;
    private Footer footer;
    private Items items;

    public Receipt() {

    }

    public Receipt(Header header, Footer footer, Items items) {
        this.header = header;
        this.footer = footer;
        this.items = items;
    }

    public String customerReceipt() {
        return header.customerHeader()+items.customerItems()+items.paymentInfo()+footer.customerFooter();
    }

    public String kitchenReceipt() {
        return header.kitchenHeader()+items.kitchenItems()+footer.kitchenFooter();
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public void setItems(Items items) {
        this.items = items;
    }

    public void setFooter(Footer footer) {
        this.footer = footer;
    }

    // public static class ReceiptBuilder {
    //     private Receipt receipt;

    //     public ReceiptBuilder(){
            
    //     }
    //     public ReceiptBuilder addHeader(Header header){

    //     }
    //     public ReceiptBuilder addItem(Items item){
            
    //     }
    //     public ReceiptBuilder addPayment(double payment){
            
    //     }
    //     public ReceiptBuilder addFooter(Footer footer){
            
    //     }
    //     public Receipt build(){
            
    //     }
    // }
}