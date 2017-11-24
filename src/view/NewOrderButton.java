package view;

import javafx.scene.control.Button;
import model.Consumable;

import java.text.DecimalFormat;

public class NewOrderButton extends Button
{
    private static final String STYLESHEET_LOCATION = "/view/new-order-button.css";
    private Consumable consumable;

    public NewOrderButton()
    {
        setPrice(0.00);
    }

    public NewOrderButton(String name)
    {
        setName(name);
        setPrice(0.00);
    }

    public NewOrderButton(String name, Double price)
    {
        setName(name);
        setPrice(price);
    }

    public NewOrderButton(Consumable c)
    {
        setConsumable(c);
        setAppearance();
        refreshText();
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

    public void setConsumable(Consumable c)
    {
        consumable = c;
    }

    public Consumable getConsumable()
    {
        return consumable;
    }

    public void setAppearance()
    {
        this.getStylesheets().add(NewOrderButton.STYLESHEET_LOCATION);
        this.setHeight(150);
        this.setWidth(150);
        if(consumable != null)
            this.setId(consumable.getCategory().getCategoryName().replace(" ", ""));
    }

    public void refreshText()
    {
        String text = "";
        if(name != null)
            text += name + "\n";
        if(price != null)
            text += df.format((double) price);
        this.setText(text);
    }

    private String name;
    private Double price;
    private DecimalFormat df = new DecimalFormat("0.00");
}