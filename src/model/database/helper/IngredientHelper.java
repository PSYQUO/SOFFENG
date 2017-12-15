package model.database.helper;

import model.database.DatabaseHelper;
import model.food.Consumable;
import model.food.Ingredient;
import model.food.RawItem;
import model.food.RawItemQuantityPair;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Used to access the Ingredient database table through specific data operations.
 */
public class IngredientHelper extends DatabaseHelper {

    // Constants referring to the database table columns.
    public final String TABLE_NAME = "Ingredient";
    public final String COLUMN_CONSUMABLE_ID = "Consumable_ID";
    public final String COLUMN_RAWITEM_ID = "RawIngredient_ID";
    public final String COLUMN_QUANTITY = "Quantity";

    /**
     * Inserts an Ingredient into the database.
     *
     * @param consumable            The representation of a food ingredient.
     * @param rawItemQuantityPair   Determines the number of raw items used as ingredient to a consumable.
     * @return                      A boolean that is true if adding is successful.
     */
    public boolean addIngredient(Consumable consumable, RawItemQuantityPair rawItemQuantityPair) {
        // Prepare the query.
        String query = "INSERT INTO " + TABLE_NAME
                     + " (" + COLUMN_CONSUMABLE_ID + ", "
                            + COLUMN_RAWITEM_ID + ", "
                            + COLUMN_QUANTITY + ") "
                            + "VALUES (?, ?, ?);";

        // Prepare the variables for binding.
        Integer consumableId = null;
        if (consumable != null) {
            consumableId = consumable.getConsumableID();
        }
        else {
            System.err.println("WARNING: Consumable of ingredient to be added is null!");
        }

        Integer rawItemId = null;
        if (rawItemQuantityPair.getRawItem() != null) {
            rawItemId = rawItemQuantityPair.getRawItem().getRawItemID();
        }
        else {
            System.err.println("WARNING: Raw ingredient of ingredient to be added is null!");
        }

        int quantity = rawItemQuantityPair.getQuantity();

        // Execute the query and store the result.
        int result = database.executeUpdate(query, new Object[] { consumableId, rawItemId, quantity });

        // Return the result. Adding is successful if result != -1.
        return result != -1;
    }

    /**
     * Retrieves the ingredients of the specified consumable.
     *
     * @param id    The database ID of a consumable in the database.
     * @return      The ingredients of the specified consumable.
     */
    public List<Ingredient> getIngredientsByConsumable(int id) {
        // Prepare the query.
        String query = "SELECT " + COLUMN_CONSUMABLE_ID + ", "
                                 + COLUMN_RAWITEM_ID + ", "
                                 + COLUMN_QUANTITY
                                 + " FROM " + TABLE_NAME
                                 + " WHERE " + COLUMN_CONSUMABLE_ID + " = ?;";

        // Execute the query and store the result.
        ResultSet rs = database.executeQuery (query, new Object[] {id});

        // Declare object to be returned.
        List<Ingredient> ingredients = null;

        try {
            while (rs.next()) {
                // Retrieve Ingredient components from the result.
                int Cid = rs.getInt(COLUMN_CONSUMABLE_ID);
                int q = rs.getInt(COLUMN_QUANTITY);
                int rawItemId = rs.getInt(COLUMN_RAWITEM_ID);

                RawItem rawItem = new RawItem(rawItemId, null, -1, -1);

                // Create an Ingredient object from the components.
                Ingredient ingredient = new Ingredient(rawItem, q);

                // Initialize the list if null. Else, add the newly created Ingredient to the list.
                if (ingredients == null) {
                    ingredients = new ArrayList<>();
                }
                else {
                    ingredients.add(ingredient);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace ();
        }

        // Return the list of Ingredients. NOTE: Can be null.
        return ingredients;
    }

    /**
     * Retrieves a list of all Ingredients from the database.
     *
     * @return  A list of all Ingredients from the database.
     */
    public List<Ingredient> getAllIngredients() {
        String query = "SELECT " + COLUMN_CONSUMABLE_ID + ", "
                                 + COLUMN_RAWITEM_ID + ", "
                                 + COLUMN_QUANTITY
                                 + " FROM " + TABLE_NAME + ";";

		ResultSet rs = database.executeQuery (query, null);
		List<Ingredient> ingredients = null;

        try {
            while (rs.next()) {
                // Retrieve Ingredient components from the result.
                int Cid = rs.getInt(COLUMN_CONSUMABLE_ID);
                int q = rs.getInt(COLUMN_QUANTITY);
                int rawItemId = rs.getInt(COLUMN_RAWITEM_ID);

                RawItem rawItem = new RawItem(rawItemId, null, -1, -1);

                // Create an Ingredient object from the components.
                Ingredient ingredient = new Ingredient(rawItem, q);

                // Initialize the list if null. Else, add the newly created Ingredient to the list.
                if (ingredients == null) {
                    ingredients = new ArrayList<>();
                }
                else {
                    ingredients.add(ingredient);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace ();
        }

        // Return the list of Ingredients. NOTE: Can be null.
        return ingredients;
    }

    /**
     * Updates an Ingredient in the database with a specific id.
     *
     * @param consumable            The Consumable that is composed by the Ingredient.
     * @param rawItemQuantityPair   The RawItemQuantity pair containing details about the Ingredient.
     * @return                      The number of records affected by the update operation.
     */
    public int editIngredient(Consumable consumable, RawItemQuantityPair rawItemQuantityPair) {
        // Prepare the query.
        String query = "UPDATE " + TABLE_NAME + " "
                     + "SET " + COLUMN_QUANTITY + " = ? "
                              + "WHERE " + COLUMN_CONSUMABLE_ID + " = ? AND " + COLUMN_RAWITEM_ID + " = ?;";

        // Prepare the variables for binding.
        Integer consumableId = null;
        if (consumable != null) {
            consumableId = consumable.getConsumableID();
        }
        else {
            System.err.println("WARNING: Consumable of ingredient to be added is null!");
        }

        int rawItemId = rawItemQuantityPair.getRawItem().getRawItemID();
        int quantity = rawItemQuantityPair.getQuantity();

        // Execute the query and store the result.
        int result = database.executeUpdate(query, new Object[] { quantity, consumableId, rawItemId });

        // Return the number of records affected by the update operation.
        return result;
    }

    /**
     * Deletes an Ingredient in the database composing a specific Consumable.
     *
     * @param consumable    The Consumable composed by the Ingredient.
     * @param rawItem       The RawItem containing details about the Ingredient.
     * @return              The number of records affected by the delete operation.
     */
    public int deleteIngredient(Consumable consumable, RawItem rawItem) {
        // Prepare the query.
        String query = "DELETE FROM " + TABLE_NAME + " "
                     + "WHERE " + COLUMN_CONSUMABLE_ID + " = ? AND " + COLUMN_RAWITEM_ID + " = ?;";

        // Prepare the variables for binding.
        Integer consumableId = null;
        if (consumable != null) {
            consumableId = consumable.getConsumableID();
        }
        else {
            System.err.println("WARNING: Consumable of ingredient to be deleted is null!");
        }

        Integer rawItemId = null;
        if (rawItem != null) {
            rawItemId = rawItem.getRawItemID();
        }
        else {
            System.err.println("WARNING: Raw ingredient of ingredient to be deleted is null!");
        }

        // Execute the query and store the result.
        int result = database.executeUpdate(query, new Object[] { consumableId, rawItemId });

        // Return the number of records affected by the delete.
        return result;
    }

}