package receipt;

import java.util.*;

public class Header{
    private static String sep = "-------------------------------------";
    private String type;
    private int transNo;
    private int custNo;
    
    public Header(String type, int transNo, int custNo){
        this.type = type;
        this.transNo = transNo;
        this.custNo = custNo;
    }
    public String kitchenHeader(){
        ArrayList<String> lines = new ArrayList<String>();
        lines.add(String.format("%-15S\n", "Kitchen slip"));
        lines.add(String.format("%-15s\n", "Transaction Type: "+type));
        lines.add(String.format("%-15s\n", "Transaction No.: "+transNo));
        lines.add(String.format("%-15s\n", "Customer No.: "+custNo));
        lines.add(String.format("%35S\n", sep));

        String text = "";
        for (int i = 0; i < lines.size(); i++){
            text = text + lines.get(i);
        }
        return text;
    }
    public String customerHeader(){
        ArrayList<String> lines = new ArrayList<String>();
        lines.add(String.format("%28S\n", "Tony Joe's Barbeque"));
        lines.add(String.format("%35S\n", "Parkwood 2 Gate, Legaspi Street,"));
        lines.add(String.format("%29S\n", "Maybunga, Pasig City"));
        lines.add(String.format("%29S\n", "Tel No: 0939-527-9331"));
        lines.add(String.format("%32S\n", "VAT REG TIN: 2401-DLSU-MNL"));

        lines.add(String.format("%1s\n", " "));
        lines.add(String.format("%-15s\n", "Transaction Type: "+type));
        lines.add(String.format("%-15s\n", "Transaction No.: "+transNo));
        lines.add(String.format("%-15s\n", "Customer No.: "+custNo));
        lines.add(String.format("%35S\n", sep));

        String text = "";
        for (int i = 0; i < lines.size(); i++){
            text = text + lines.get(i);
        }
        return text;
    }
    
    public static void main(String[] args){
        Header h = new Header("", 1, 1);
        System.out.print(h.kitchenHeader());
        System.out.print(h.customerHeader());
    }
}