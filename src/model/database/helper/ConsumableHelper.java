package model.database.helper;

import model.database.DatabaseHelper;
import model.food.Category;
import model.food.Consumable;
import model.food.Meal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Used to access the Consumable database table through specific data operations.
 */
public class ConsumableHelper extends DatabaseHelper {

    // Constants referring to the database table columns.
    public final String TABLE_NAME = "Consumable";
    public final String COLUMN_CONSUMABLE_ID = "Consumable_ID";
    public final String COLUMN_CONSUMABLE_NAME = "Consumable_Name";
    public final String COLUMN_CONSUMABLE_CODENAME = "Consumable_CodeName";
    public final String COLUMN_CONSUMABLE_CATEGORY_ID = "Category_ID";
    public final String COLUMN_CONSUMABLE_PRICE = "Consumable_Price";
    public final String COLUMN_CONSUMABLE_MEAL_ID = "Meal_ID";

    /**
     * Inserts a Consumable into the database.
     *
     * @param consumable    The consumable to be inserted.
     * @return              Returns true if adding is successful.
     */
    public boolean addConsumable(Consumable consumable) {
        // Prepare the query.
        String query = "INSERT INTO " + TABLE_NAME + "( "
                     + "(" + COLUMN_CONSUMABLE_NAME + ", "
                           + COLUMN_CONSUMABLE_CODENAME + ", "
                           + COLUMN_CONSUMABLE_PRICE + ", "
                           + COLUMN_CONSUMABLE_MEAL_ID + ", "
                           + COLUMN_CONSUMABLE_CATEGORY_ID + ") "
                           + "VALUES (?, ?, ?, ?, ?);";

        // Prepare the variables for binding.
        String consumableName = consumable.getName();
        String consumableCodeName = consumable.getCodeName();
        double consumablePrice = consumable.getPrice();

        Integer consumableMealId = null;
        if (consumable.getMeal() != null) {
            consumableMealId = consumable.getMeal().getMealID();
        }
        else {
            System.err.println("INFO: Consumable-to-be-added's Meal attribute is empty.");
        }

        Integer consumableCategoryId = null;
        if (consumable.getCategory() != null) {
            consumableCategoryId = consumable.getCategory().getCategoryID();
        }
        else {
            System.err.println("WARNING: Consumable-to-be-added's Category must not be NULL! Foreign key constraint will fail! No insertion occurred.");
            return false;
        }

        // Execute the query and store the result.
		int result = database.executeUpdate(query, new Object[] { consumableName,
                                                                  consumableCodeName,
                                                                  consumablePrice,
                                                                  consumableMealId,
                                                                  consumableCategoryId });

        // Return the result. Adding is successful if result != -1.
		return result != -1;
    }

    /**
     * Retrieve a Consumable from the database.
     *
     * @param id    An id that refers to the desired Consumable in the database.
     * @return      The desired Consumable. Returns null if the Consumable is not found.
     */
    public Consumable getConsumable(int id) {
        // Prepare the query.
        String query = "SELECT " + COLUMN_CONSUMABLE_ID + ", "
                                 + COLUMN_CONSUMABLE_NAME + ", "
                                 + COLUMN_CONSUMABLE_CODENAME + ", "
                                 + COLUMN_CONSUMABLE_PRICE + ", "
                                 + COLUMN_CONSUMABLE_MEAL_ID + ", "
                                 + COLUMN_CONSUMABLE_CATEGORY_ID + " "
                                 + "FROM " + TABLE_NAME + " "
                                 + "WHERE " + COLUMN_CONSUMABLE_ID + " = ?;";

        // Execute the query and store the result.
		ResultSet rs = database.executeQuery(query, new Object[] {id});

        // Declare object to be returned.
		Consumable consumable = null;

		try {
			if (rs.next ()) {
                // Retrieve Category components from the result.
                int consumableId = rs.getInt(COLUMN_CONSUMABLE_ID);
                String consumableName = rs.getString(COLUMN_CONSUMABLE_NAME);
                String consumableCodeName = rs.getString(COLUMN_CONSUMABLE_CODENAME);
                double consumablePrice = rs.getDouble(COLUMN_CONSUMABLE_PRICE);
                Category consumableCategory = new Category(rs.getInt(COLUMN_CONSUMABLE_CATEGORY_ID), null);
                Meal consumableMeal = new Meal(rs.getInt(COLUMN_CONSUMABLE_MEAL_ID), null);

                // NOTE: consumableMeal can be null.
                // Create a Consumable object from the components.
                consumable = new Consumable(consumableId, consumableName, consumableCodeName,
                                            consumableCategory, consumablePrice, null, consumableMeal);
			}
		} catch (SQLException e) {
			e.printStackTrace ();
		}

        // Return the desired Consumable. NOTE: Can be null.
		return consumable;
    }

    /**
     * Retrieves a list of all Consumables from the database.
     *
     * @return  A list of all Consumables from the database.
     */
    public List<Consumable> getAllConsumables() {
        // Prepare the query.
        String query = "SELECT " + COLUMN_CONSUMABLE_ID + ", "
                                 + COLUMN_CONSUMABLE_NAME + ", "
                                 + COLUMN_CONSUMABLE_CODENAME + ", "
                                 + COLUMN_CONSUMABLE_PRICE + ", "
                                 + COLUMN_CONSUMABLE_MEAL_ID + ", "
                                 + COLUMN_CONSUMABLE_CATEGORY_ID
                                 + " FROM " + TABLE_NAME + ";";

        // Execute the query and store the result.
		ResultSet rs = database.executeQuery (query, null);

        // Declare list to be returned.
		List<Consumable> consumables = null;

		try {
			while (rs.next()) {
                // Retrieve Consumable components from the result.
                int consumableId = rs.getInt(COLUMN_CONSUMABLE_ID);
                String consumableName = rs.getString(COLUMN_CONSUMABLE_NAME);
                String consumableCodeName = rs.getString(COLUMN_CONSUMABLE_CODENAME);
                double consumablePrice = rs.getDouble(COLUMN_CONSUMABLE_PRICE);
                Category consumableCategory = new Category(rs.getInt(COLUMN_CONSUMABLE_CATEGORY_ID), null);
                Meal consumableMeal = new Meal(rs.getInt(COLUMN_CONSUMABLE_MEAL_ID), null);

                // Create a Consumable object from the components.
                Consumable consumable = new Consumable(consumableId, consumableName, consumableCodeName,
                                                       consumableCategory, consumablePrice, null, consumableMeal);

                // Initialize the list if null. Else, add the newly created Consumable to the list.
                if (consumables == null) {
                    consumables = new ArrayList<>();
                }
                else {
                    consumables.add(consumable);
                }
			}
		} catch (SQLException e) {
			e.printStackTrace ();
		}

        // Return the list of Consumables. NOTE: Can be null.
		return consumables;
    }

    /**
     * Updates a Consumable in the database with a specific id.
     *
     * @param consumable    The category to be edited.
     * @return              The number of records affected by the update operation.
     */
    public int editConsumable(Consumable consumable) {
        // Prepare the query.
        String query = "UPDATE " + TABLE_NAME + " "
                     + "SET " + COLUMN_CONSUMABLE_NAME + " = ?, "
                              + COLUMN_CONSUMABLE_CODENAME + " = ?, "
                              + COLUMN_CONSUMABLE_PRICE + " = ?, "
                              + COLUMN_CONSUMABLE_MEAL_ID + " = ?, "
                              + COLUMN_CONSUMABLE_CATEGORY_ID + " = ? "
                              + "WHERE " + COLUMN_CONSUMABLE_ID + " = ?;";

        // Prepare the variables for binding.
        String consumableName = consumable.getName();
        String consumableCodeName = consumable.getCodeName();
        double consumablePrice = consumable.getPrice();

        Integer consumableMealId = null;
        if (consumable.getMeal() != null) {
            consumableMealId = consumable.getMeal().getMealID();
        }
        else {
            System.err.println("INFO: Consumable-to-be-added's Meal attribute is empty.");
        }

        Integer consumableCategoryId = null;
        if (consumable.getCategory() != null) {
            consumableCategoryId = consumable.getCategory().getCategoryID();
        }
        else {
            System.err.println("WARNING: Consumable-to-be-added's Category must not be NULL! Foreign key constraint will fail! No update occurred.");
            return -1;
        }

        // Execute the query and store the result.
        int result = database.executeUpdate(query, new Object[] { consumableName,
                                                                  consumableCodeName,
                                                                  consumablePrice,
                                                                  consumableMealId,
                                                                  consumableCategoryId });

        // Return the number of records affected by the update operation.
		return result;
    }

    /**
     * Deletes a Consumable in the database with a specific id.
     *
     * @param id    An id referring to the Consumable to be deleted from the database.
     * @return      The number of records affected by the delete operation.
     */
    public int deleteConsumable(int id) {
        // Prepare the query.
        String query = "DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_CONSUMABLE_ID + " = ?;";

        // Execute the query and store the result.
        int result = database.executeUpdate(query, new Object[] {id});

        // Return the number of records affected by the delete.
        return result;
    }

}