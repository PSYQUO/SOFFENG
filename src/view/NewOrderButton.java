package view;

import javafx.scene.control.Button;

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
        this.setText(name + "\n" + price);
    }

    private String name;
    private Double price;
}