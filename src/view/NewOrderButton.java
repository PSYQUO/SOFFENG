package view;

import javafx.scene.control.Button;
import model.Consumable;

import java.text.DecimalFormat;

public class NewOrderButton extends Button
{

    private static final String STYLESHEET_LOCATION = "/view/new-order-button.css";


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

//package view;
//
//        import javafx.scene.control.Button;
//        <<<<<<< HEAD
//import model.Consumable;
//
//public class NewOrderButton extends Button {
//    // location of the CSS for each button
//    private static final String STYLESHEET_LOCATION = "/view/new-order-button.css";
//
//    public NewOrderButton(Consumable c) {
//        setConsumable(c);
//        setAppearance();
//=======
//
//        public class NewOrderButton extends Button {
//            public NewOrderButton() {
//                setPrice(0.00);
//            }
//
//            public NewOrderButton(String name) {
//                setName(name);
//                setPrice(0.00);
//            }
//
//            public NewOrderButton(String name, Double price) {
//                setName(name);
//                setPrice(price);
//            }
//
//            public String getName() {
//                return name;
//            }
//
//            public void setName(String name) {
//                this.name = name;
//>>>>>>> parent of 8a4f44f... V2017.11.22 patch v1 (#54)
//                refreshText();
//            }
//
//            public void setConsumable(Consumable c) {
//                consumable = c;
//            }
//
//            public Consumable getConsumable() {
//                return consumable;
//            }
//
//            public void refreshText() {
//<<<<<<< HEAD
//                this.setText(consumable.getCodeName()
//                        + "\n" + consumable.getPrice());
//            }
//
//            public void setAppearance() {
//                this.getStylesheets().add(NewOrderButton.STYLESHEET_LOCATION);
//                this.setHeight(150);
//                this.setWidth(150);
//                if (consumable != null)
//                    this.setId(consumable.getCategory().getCategoryName().replace(" ", ""));
//            }
//
//            private Consumable consumable;
//=======
//        this.setText(name + "\n" + price);
//        }
//
//        private String name;
//        private Double price;
//>>>>>>> parent of 8a4f44f... V2017.11.22 patch v1 (#54)
//    }