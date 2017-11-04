package receipt;

import java.util.*;

public class Footer{
    private static String sep = "-------------------------------------";
    private String cashier;
    private String date;
    
    public Footer(String cashier, String date){
        this.cashier = cashier;
        this.date = date;
    }
    public String kitchenFooter(){
        ArrayList<String> lines = new ArrayList<String>();
        lines.add(String.format("%35S\n", sep));

        lines.add(String.format("%-15s\n", "Cashier: "+cashier));
        lines.add(String.format("%-15s\n", "Date Time: "+date));
        
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
        lines.add(String.format("%1s\n", " "));

        lines.add(String.format("%33S\n", "Thank you. Please come again."));
        
        lines.add(String.format("%35S\n", sep));

        lines.add(String.format("%-15s\n", "Cashier: "+cashier));
        lines.add(String.format("%-15s\n", "Date Time: "+date));

        String text = "";
        for (int i = 0; i < lines.size(); i++){
            text = text + lines.get(i);
        }
        return text;
    }
}