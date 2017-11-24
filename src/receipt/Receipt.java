package receipt;

public class Receipt
{
    private ReceiptHeader header;
    private ReceiptBody body;
    private ReceiptFooter footer;

    // Empty constructor since there is already a builder
    // for this class
    // @see ReceiptBuilder
    public Receipt()
    {

    }

    // Returns the version of the receipt that 
    // is to be given to the customer
    public String customerReceipt()
    {
        return header.customerHeader()
             + body.customerItems()
             + body.paymentInfo()
             + footer.customerFooter();
    }

    // Returns the version of the receipt that 
    // is to be given to the kitchen department
    public String kitchenReceipt()
    {
        return header.kitchenHeader()
             + body.kitchenItems()
             + footer.kitchenFooter();
    }

    public ReceiptHeader getHeader()
    {
        return header;
    }

    public ReceiptBody getBody()
    {
        return body;
    }

    public ReceiptFooter getFooter()
    {
        return footer;
    }

    public void setHeader(ReceiptHeader header)
    {
        this.header = header;
    }

    public void setBody(ReceiptBody body)
    {
        this.body = body;
    }

    public void setFooter(ReceiptFooter footer)
    {
        this.footer = footer;
    }

}