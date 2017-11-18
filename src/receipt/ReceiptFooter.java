package receipt;

import java.util.*;

public class ReceiptFooter {
    private static String sep = "------------------------------------";
    private String cashierName;
    private String transactionDate;

    public void setCashierName(String cashierName) {
        this.cashierName = cashierName;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String kitchenFooter(){
        ArrayList<String> lines = new ArrayList<String>();
        lines.add(String.format("%35S\n", sep));

        lines.add(String.format("%-15s\n", "Cashier: " + cashierName));
        lines.add(String.format("%-15s\n", "Date Time: " + transactionDate));
        
        String text = "";
        for (int i = 0; i < lines.size(); i++){
            text = text + lines.get(i);
        }
        return text;
    }
    
    public String customerFooter(){
        ArrayList<String> lines = new ArrayList<String>();
        lines.add(String.format("%35S\n", sep));

        lines.add(String.format("%31S\n", "Antoine IT Solutions Inc."));
        lines.add(String.format("%32S\n", "Taft Avenue, Malate, Manila"));
        lines.add(String.format("%31S\n", "Contact No. 0977-627-4709"));
        //lines.add(String.format("%1s\n", " "));

        //lines.add(String.format("%33S\n", "Thank you. Please come again."));
        
        lines.add(String.format("%35S\n", sep));

        lines.add(String.format("%-15s\n", "Cashier: " + cashierName));
        lines.add(String.format("%-15s\n", "Date Time: " + transactionDate));

        String text = "";
        for (int i = 0; i < lines.size(); i++){
            text = text + lines.get(i);
        }
        return text;
    }
}