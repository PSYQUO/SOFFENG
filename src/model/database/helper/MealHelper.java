package model.database.helper;

import model.database.DatabaseHelper;
import model.food.Consumable;
import model.food.ConsumableQuantityPair;
import model.food.Meal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Used to access the Incoming database table through specific data operations.
 */
public class MealHelper extends DatabaseHelper {

    // Constants referring to the database table columns.
    public final String TABLE_NAME = "Meal";
    public final String COLUMN_MEAL_ID = "Meal_ID";
    public final String COLUMN_ADDONS = "AddOns";
    public final String COLUMN_QUANTITY = "In_Quantity";

    /**
     * Inserts a Meal into the database.
     *
     * @param consumable  The category to be inserted.
     * @param consumableQuantityPair  The category to be inserted.
     * @return          Returns true if adding is successful.
     */
    public boolean addMeal(Consumable consumable, ConsumableQuantityPair consumableQuantityPair) {
        String query = "INSERT INTO " + TABLE_NAME
                     + " (" + COLUMN_MEAL_ID + ", "
                            + COLUMN_ADDONS + ", "
                            + COLUMN_QUANTITY + ") "
                            + "VALUES (? ? ?);";

        int mealId = consumable.getMeal().getMealID();

        Integer addon = null;
        Integer quantity = null;

        if (consumableQuantityPair != null) {
            if (consumableQuantityPair.getConsumable() != null) {
                addon = consumableQuantityPair.getConsumable().getConsumableID();
            }
            else {
                System.err.println("WARNING: Consumable of meal to be added is null!");
            }

            quantity = consumableQuantityPair.getQuantity();
        }
        else {
            System.err.println("WARNING: ConsumableQuantityPair of input to be added is null!");
        }

        int result = database.executeUpdate(query, new Object[] { mealId, addon, quantity });

        return result != -1;
    }

    public List<ConsumableQuantityPair> getConsumablesByMeal(int id) {
        String query = "SELECT " + COLUMN_ADDONS + ", "
                                 + COLUMN_QUANTITY
                                 + " FROM " + TABLE_NAME
                                 + " WHERE " + COLUMN_MEAL_ID + " = ?;";

        ResultSet rs = database.executeQuery(query, new Object[] {id});
        List<ConsumableQuantityPair> consumableQuantityPairList = null;

        try {
            while (rs.next()) {
                int consumableId = rs.getInt(COLUMN_ADDONS);
                int quantity = rs.getInt(COLUMN_QUANTITY);

                Consumable consumable = new Consumable(consumableId, null, null, null, -1, null);
                ConsumableQuantityPair consumableQuantityPair = new ConsumableQuantityPair(consumable, quantity);

                if (consumableQuantityPairList == null) {
                    consumableQuantityPairList = new ArrayList<>();
                }
                else {
                    consumableQuantityPairList.add(consumableQuantityPair);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace ();
        }

        return consumableQuantityPairList;
    }

    public List<Meal> getAllMeals() {
        String query = "SELECT " + COLUMN_MEAL_ID + ", "
                                 + COLUMN_ADDONS + ", "
                                 + COLUMN_QUANTITY
                                 + " FROM " + TABLE_NAME + ";";

        ResultSet rs = database.executeQuery(query, null);
        List<Meal> meals = null;

        try {
            while (rs.next()) {
                int mealId = rs.getInt(COLUMN_MEAL_ID);
                int consumableId = rs.getInt(COLUMN_ADDONS);
                int quantity = rs.getInt(COLUMN_QUANTITY);

                Meal meal = new Meal(mealId, null);

                if (meals == null) {
                    meals = new ArrayList<>();
                }
                else {
                    meals.add(meal);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace ();
        }

        return meals;
    }

//    public int editMeal(int id, Meal item) {
//        String query = "UPDATE " + TABLE_NAME + " "
//                     + "SET " + COLUMN_QUANTITY + " = ? "
//                     + "WHERE " + COLUMN_MEAL_ID + " = ?, " + COLUMN_ADDONS + " = ?;";
//
//        Integer mealId = null;
//        Integer addon = null;
//        if (item != null) {
//            mealId = item.getMealID();
//            addon = item.getAddOns();
//        }
//        else {
//            System.err.println("WARNING: Meal must not be NULL!");
//            return false;
//        }
//
//        int result = database.executeUpdate(query, new Object[] { name, id });
//
//        return result;
//        return -1;
//    }

    public int deleteMeal(int id) {
        String query = "DELETE FROM " + TABLE_NAME + " "
                     + "WHERE " + COLUMN_MEAL_ID + " = ?;";

        int result = database.executeUpdate(query, new Object[] {id});

        return result;
    }

}