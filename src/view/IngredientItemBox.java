package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import model.food.Ingredient;
import model.food.LineItem;

public class IngredientItemBox extends ItemBox {
    public IngredientItemBox(Ingredient l) {
        super();
        initialize();
        addCloseButton();
        this.setIngredient(l);
        refreshAllLabels();
    }

    /**
     * Adds an "X" button inside the IngredientItemBox
     * which allows the IngredientItemBox to be removed from
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

            // remove p from parent's child list
            ((Pane) p.getParent()).getChildren().remove(p);
        };
    }

    /**
     * Adds "+" and "-" buttons inside the IngredientItemBox
     * to add or subtract from the line item quantity
     */
    public void addQuantityButtons() {
        buttonAdd = new Button("+");
        buttonAdd.setId("buttonAdd"); // for CSS
        buttonAdd.setMinSize(USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        buttonAdd.setPrefSize(USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        buttonSubtract = new Button("-");
        buttonSubtract.setId("buttonSubtract"); // for CSS
        buttonSubtract.setMinSize(USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        buttonSubtract.setPrefSize(USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);

        buttonAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // TODO insert handler activities
            }
        });

        buttonSubtract.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // TODO insert handler activities
            }
        });

        this.getChildren().addAll(buttonAdd, buttonSubtract);
    }

    /**
     * Changes the contained IngredientItemBox into the
     * parameter specified
     * @param i ingredient
     */
    public void setIngredient(Ingredient i) {
        ingredient = i;
    }

    /**
     * Changes the contained IngredientItemBox into the
     * parameter specified
     * @return Ingredient
     */
    public Ingredient getIngredient() {
        return ingredient;
    }

    /**
     * Initializes the content of the IngredientItemBox
     */
    @Override
    public void initialize() {
        this.setStyle("-fx-background-color: #ffffff; " +
                "-fx-padding: 5px; " +
                "-fx-border-radius: 30; " +
                "-fx-border-width: 3px; " +
                "-fx-border-color: #ffffff;");
        this.setAlignment(Pos.CENTER_LEFT);

        // initialize labels
        name = new Label();
        qty = new Label();

        // initialize grid, set pane constraints
        GridPane grid = new GridPane();
        grid.setPrefWidth(330);
        grid.setMaxWidth(Double.MAX_VALUE);
        grid.setPrefHeight(USE_COMPUTED_SIZE);

        // set column constraints in percentage
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(70);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(30);
        grid.getColumnConstraints().addAll(column1, column2);

        grid.setGridLinesVisible(false);

        // Add labels
        grid.add(name, 0, 0);
        grid.add(qty, 1, 0);
        this.getChildren().add(grid);
    }

    /**
     * Refreshes the IngredientItemBox labels by grabbing
     * information from the contained Ingredient
     */
    @Override
    public void refreshAllLabels() {
        name.setText(ingredient.getRawItem().getName());
        // TODO refresh qty
    }

    private Label name;
    private Label qty;
    private Ingredient ingredient;
    private Button buttonClose, buttonAdd, buttonSubtract;
}
