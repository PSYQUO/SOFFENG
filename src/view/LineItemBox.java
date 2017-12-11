package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.scene.Node;
import model.food.LineItem;

public class LineItemBox extends HBox {

    private static final String STYLESHEET_LOCATION = "/view/line-item-box.css";
    public static final int DEFAULT = 0;
    public static final int MARK_FOR_DELETE = 1;
    public static final int MARK_FOR_INCREASE = 2;
    public static final int MARK_FOR_DECREASE= 3;

    private int statusFlag;

    /*
    public LineItemBox() {
        initialize();
        addCloseButton();
    }
    */

    public LineItemBox(LineItem l) {
        // set hbox constraints
        this.setPrefHeight(30);
        this.setPrefWidth(300);
        this.setMaxWidth(Double.MAX_VALUE);
        this.setSpacing(10.0);
        this.getStylesheets().add(STYLESHEET_LOCATION);

        statusFlag = LineItemBox.DEFAULT;
        addQuantityButtons();
        initialize();
        addCloseButton();
        this.setLineItem(l);
        refreshAllLabels();
    }

    /**
     * Adds an "X" button inside the LineItemBox
     * which allows the LineItemBox to be removed from
     * its parent.
     */
    public void addCloseButton() {
        buttonClose = new Button("x");
        buttonClose.setPrefHeight(USE_COMPUTED_SIZE);
        buttonClose.setPrefWidth(USE_COMPUTED_SIZE);
        buttonClose.setId("buttonClose");

        EventHandler<ActionEvent> handler = event -> {
            // get button that triggered the action
            Node n = (Node) event.getSource();

            // get node to remove
            Node p = n.getParent();

            // mark line item box for deletion
            statusFlag = LineItemBox.MARK_FOR_DELETE;

            // remove p from parent's child list
            ((Pane) p.getParent()).getChildren().remove(p);
        };

        buttonClose.setOnAction(handler);

        this.getChildren().add(buttonClose);
    }

    /**
     * Adds "+" and "-" buttons inside the LineItemBox
     * to add or subtract from the line item quantity
     */
    public void addQuantityButtons() {
        Button add = new Button("+");
        add.setId("buttonAdd"); // for CSS
        add.setMinSize(USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        add.setPrefSize(USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        buttonSubtract = new Button("-");
        buttonSubtract.setId("buttonSubtract"); // for CSS
        buttonSubtract.setMinSize(USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        buttonSubtract.setPrefSize(USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);

        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // lineItem.increaseQuantity(1);
                statusFlag = LineItemBox.MARK_FOR_INCREASE;
            }
        });

        buttonSubtract.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // lineItem.decreaseQuantity(1);
                statusFlag = LineItemBox.MARK_FOR_DECREASE;
            }
        });

        this.getChildren().addAll(add, buttonSubtract);
    }

    /**
     * Changes the contained LineItem into the parameter specified
     * @param l
     */
    public void setLineItem(LineItem l) {
        lineItem = l;
    }

    /**
     * Return the LineItem contained inside the LineItemBox object
     * @return LineItem
     */
    public LineItem getLineItem() {
        return lineItem;
    }

    /**
     * Return the status flag of the LineItemBox
     * @return statusFlag
     */
    public int getStatusFlag() {
        return statusFlag;
    }
    
    /**
     * Changes the status flag of the LineItemBox
     * @param f the status flag
     */
    public void setStatusFlag(int f) {
        statusFlag = f;
    }

    /**
     * Return the close button inside the LineItemBox object
     * @return buttonClose
     */
    public Button getCloseButton() {
        return buttonClose;
    }

    /**
     * Return the subtract button inside the LineItemBox object
     * @return buttonSubtract
     */
    public Button getSubtractButton() {
        return buttonSubtract;
    }

    /**
     * Refreshes the LineItemBox labels by grabbing information from
     * the contained LineItem
     */
    public void refreshAllLabels() {
        name.setText(lineItem.getConsumable().getCodeName());
        price.setText(lineItem.getConsumable().getPrice() + "");
        qty.setText(lineItem.getQuantity() + "");
    }

    /**
     * Initializes the content of the LineItemBox
     */
    public void initialize() {
        this.setStyle("-fx-background-color: #ffffff; " +
                "-fx-padding: 5px; " +
                "-fx-border-radius: 30; " +
                "-fx-border-width: 3px; " +
                "-fx-border-color: #ffffff;");
        this.setAlignment(Pos.CENTER_LEFT);

        // initialize labels
        name = new Label();
        price = new Label();
        qty = new Label();

        // initialize grid, set pane constraints
        GridPane grid = new GridPane();
        grid.setPrefWidth(330);
        grid.setMaxWidth(Double.MAX_VALUE);
        grid.setPrefHeight(USE_COMPUTED_SIZE);

        // set column constraints in percentage
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(50);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(20);
        column2.setHgrow(Priority.ALWAYS);
        ColumnConstraints column3 = new ColumnConstraints();
        column3.setPercentWidth(30);
        grid.getColumnConstraints().addAll(column1, column2, column3);

        grid.setGridLinesVisible(false);

        // Add labels
        grid.add(name, 0, 0);
        grid.add(qty, 1, 0);
        grid.add(price, 2, 0);
        this.getChildren().add(grid);
    }

    private Label name;
    private Label price;
    private Label qty;
    private LineItem lineItem;
    private Button buttonClose;
    private Button buttonSubtract;
}