package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import model.LineItem;

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
        statusFlag = LineItemBox.MARK_FOR_DELETE;
        addCloseButton();
        initialize();
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
        buttonClose.setId("buttonClose");

        EventHandler<ActionEvent> handler = event -> {
            // get button that triggered the action
            Node n = (Node) event.getSource();

            // get node to remove
            Node p = n.getParent();

            // remove p from parent's child list
            ((Pane) p.getParent()).getChildren().remove(p);
        };

        buttonClose.setOnAction(handler);

        this.getChildren().add(buttonClose);
    }

    /**
     * Adds "+" and "-" buttons inside the LineItemBox
     *
     */
    public void addQuantityButtons() {
        Button add = new Button("+");
        add.setId("buttonAdd");
        Button subtract = new Button("-");
        add.setId("buttonSubtract");
        this.getChildren().addAll(add, )
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
     * Return the close button inside the LineItemBox object
     * @return buttonClose
     */
    public Button getCloseButton() {
        return buttonClose;
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
        // initialize labels
        name = new Label();
        price = new Label();
        qty = new Label();

        // initialize grid, set pane constraints
        GridPane grid = new GridPane();
        grid.setMaxWidth(Double.MAX_VALUE);

        // set hbox constraints
        this.setMaxWidth(Double.MAX_VALUE);
        this.setSpacing(10.0);
        this.getStylesheets().add(STYLESHEET_LOCATION);

        // set column constraints in percentage
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(50);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(20);
        ColumnConstraints column3 = new ColumnConstraints();
        column3.setPercentWidth(30);
        grid.getColumnConstraints().addAll(column1, column2, column3);

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
}