package view;

import javafx.scene.control.Button;
import model.Consumable;

public class NewOrderButton extends Button {
    // location of the CSS for each button
    private static final String STYLESHEET_LOCATION = "/view/new-order-button.css";

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
        this.setText(consumable.getCodeName()
                + "\n" + consumable.getPrice());
    }

    public void setAppearance() {
        this.getStylesheets().add(NewOrderButton.STYLESHEET_LOCATION);
        this.setHeight(150);
        this.setWidth(150);
        if (consumable != null)
            this.setId(consumable.getCategory().getCategoryName().replace(" ", ""));
    }

    private Consumable consumable;
}