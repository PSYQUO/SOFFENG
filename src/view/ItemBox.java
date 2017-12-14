package view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public abstract class ItemBox extends HBox {

    private static final String STYLESHEET_LOCATION = "/view/line-item-box.css";

    public ItemBox() {
        // set hbox constraints
        this.setPrefHeight(30);
        this.setPrefWidth(Double.MAX_VALUE);
        this.setMaxWidth(Double.MAX_VALUE);
        this.setSpacing(10.0);
        this.getStylesheets().add(STYLESHEET_LOCATION);
    }

    public abstract void initialize();

    public abstract void refreshAllLabels();
}
