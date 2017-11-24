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

    public NewOrderButton(Consumable c) {
        setConsumable(c);
        setAppearance();
        refreshText();
    }

    public void setConsumable(Consumable c) {
        consumable = c;
    }

    public Consumable getConsumable() {
        return consumable;
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