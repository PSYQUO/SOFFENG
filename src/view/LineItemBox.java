package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import model.LineItem;

public class LineItemBox extends HBox {

    public LineItemBox() {
        addCloseButton();
        addAllLabels();
        setAppearance();
    }

    public LineItemBox(LineItem l) {
        addCloseButton();
        this.setLineItem(l);
        addAllLabels();
        refreshAllLabels();
        setAppearance();
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

    public void setLineItem(LineItem l) {
        lineItem = l;
    }

    public LineItem getLineItem() {
        return lineItem;
    }

    public void addAllLabels() {
        this.getChildren().addAll(name, price, qty);
    }

    // uses the data of its line item to refresh its labels
    public void refreshAllLabels() {
        name.setText(lineItem.getConsumable().getCodeName());
        price.setText(lineItem.getConsumable().getPrice() + "");
        qty.setText(lineItem.getQuantity() + "");
    }

    public void setAppearance() {
        this.setMaxWidth(Double.MAX_VALUE);
        this.setSpacing(10.0);
        this.setStyle("Button {-fx-text-fill: BLACK;}");
    }

    private Label name = new Label();
    private Label price = new Label();
    private Label qty = new Label();
    private LineItem lineItem;
}