package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import model.Consumable;

public class LineItemBox extends HBox {

    public LineItemBox() {
        addCloseButton();
        count = 0;
        addAllLabels();
    }

    public LineItemBox(Consumable c) {
        addCloseButton();
        count = 0;
        this.setConsumable(c);
        addAllLabels();
        refreshAllLabels();
    }

    public void addCloseButton() {
        Button x = new Button("x");

        EventHandler<ActionEvent> handler = event -> {
            // get button that triggered the action
            Node n = (Node) event.getSource();
        
            // get node to remove
            Node p = n.getParent();
        
            // remove p from parent's child list
            ((Pane) p.getParent()).getChildren().remove(p);
        };
        
        x.setOnAction(handler);
        
        this.getChildren().add(x);
    }

    public void addLabels() {
        this.getChildren().addAll(this.name, this.price, this.qty);
    }

    public void setConsumable(Consumable c) {
        consumable = c;
    }

    public Consumable getConsumable() {
        return consumable;
    }

    public void setCount(int q) {
        count = c;
    }

    public int getCount() {
        return count;
    }

    public void addAllLabels() {
        this.getChildren().addAll(name, price, qty);
    }

    // uses the data of its consumable to refresh its labels
    public void refreshAllLabels() {
        name.setText(consumable.getCodeName());
        price.setText(consumable.getPrice() + "");
        qty.setText(count + "");
    }

    public void setAppearance() {
        this.setMaxWidth(Double.MAX_VALUE);
        this.setSpacing(10.0);
        // this.setStyle();
    }

    private Label name = new Label();
    private Label price = new Label();
    private Label qty = new Label();
    private Consumable consumable;

    // int value for the qty
    private int count;
}