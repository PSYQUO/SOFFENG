package receipt;

import java.util.*;

public class ReceiptHeader
{
    private static String separator = "------------------------------------";
    private String transactionMode;
    private int transactionNo;
    private int customerNo;

    public void setTransactionMode(String transactionMode)
    {
        this.transactionMode = transactionMode;
    }

    public void setTransactionNo(int transactionNo)
    {
        this.transactionNo = transactionNo;
    }

    public void setCustomerNo(int customerNo)
    {
        this.customerNo = customerNo;
    }

    public String kitchenHeader()
    {
        ArrayList<String> lines = new ArrayList<String>();
        
        lines.add(String.format("%-15S\n", "Kitchen slip"));
        lines.add(String.format("%-15s\n", "Transaction Type: " + transactionMode));
        lines.add(String.format("%-15s\n", "Transaction No.: " + transactionNo));
        lines.add(String.format("%-15s\n", "Customer No.: " + customerNo));

        lines.add(String.format("%1s\n", " "));
        lines.add(String.format("%-15s%21s\n", "Item", "Qty"));
        lines.add(String.format("%-15s%21s\n", "----", "---"));

        String text = "";
        for (int i = 0; i < lines.size(); i++)
        {
            text = text + lines.get(i);
        }

        return text;
    }

    public String customerHeader()
    {
        ArrayList<String> lines = new ArrayList<String>();

        lines.add(String.format("%28S\n", "Tony Joe's Barbeque"));
        lines.add(String.format("%35S\n", "Parkwood 2 Gate, Legaspi Street,"));
        lines.add(String.format("%29S\n", "Maybunga, Pasig City"));
        lines.add(String.format("%29S\n", "Tel No: 0939-527-9331"));
        //lines.add(String.format("%32S\n", "VAT REG TIN: 2401-DLSU-MNL"));

        lines.add(String.format("%1s\n", " "));
        lines.add(String.format("%-15s\n", "Transaction Type: " + transactionMode));
        lines.add(String.format("%-15s\n", "Transaction No.: " + transactionNo));
        lines.add(String.format("%-15s\n", "Customer No.: " + customerNo));
        lines.add(String.format("%35S\n", separator));
        lines.add(String.format("%-15s%6s%5s%10s\n", "Item", "", "Qty", "Price"));
        lines.add(String.format("%-15s%6s%5s%10s\n", "----", "", "---", "-----"));

        String text = "";
        for (int i = 0; i < lines.size(); i++)
        {
            text = text + lines.get(i);
        }

        return text;
    }

}