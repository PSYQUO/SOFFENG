
package view;

import javafx.scene.control.Button;
import model.food.Consumable;

import java.text.DecimalFormat;

public class NewOrderButton extends Button
{
    // location of the CSS for each button
    private static final String STYLESHEET_LOCATION = "/view/new-order-button.css";

    public NewOrderButton(Consumable c)
    {
        setConsumable(c);
        setAppearance();
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

    public void refreshText()
    {
        this.setText(consumable.getCodeName()
                + "\n" + df.format(consumable.getPrice()));
    }

    public void setAppearance()
    {
        this.getStylesheets().add(NewOrderButton.STYLESHEET_LOCATION);
        this.setPrefHeight(150.0);
        this.setMaxHeight(150.0);
        this.setPrefWidth(150);
        this.setMaxWidth(150.0);
        if(consumable != null)
            this.setId(consumable.getCategory().getCategoryName().replace(" ", ""));
    }

    private Consumable consumable;
    private DecimalFormat df = new DecimalFormat("0.00");
}