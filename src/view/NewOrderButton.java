package view;

import javafx.scene.control.Button;
import java.text.DecimalFormat;

public class NewOrderButton extends Button {

    public NewOrderButton() {
        setPrice(0.00);
    }

    public NewOrderButton(String name) {
        setName(name);
        setPrice(0.00);
    }

    public NewOrderButton(String name, Double price) {
        setName(name);
        setPrice(price);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        refreshText();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
        refreshText();
    }

    public void refreshText() {
        String text = "";
        if (name != null)
            text += name + "\n";
        if (price != null)
            text += df.format((double) price);
        this.setText(text);
    }

    private String name;
    private Double price;
    private DecimalFormat df = new DecimalFormat("0.00");
}