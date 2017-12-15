package model.database.helper;

import model.database.DatabaseHelper;
import model.food.Consumable;
import model.food.ConsumableQuantityPair;
import model.food.LineItem;
import model.transaction.Transaction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Used to access the Incoming database table through specific data operations.
 */
public class LineItemHelper extends DatabaseHelper {

    // Constants referring to the database table columns.
    public final String TABLE_NAME = "LineItem";
    public final String COLUMN_TRANSACTION_ID = "Transaction_ID";
    public final String COLUMN_CONSUMABLE_ID = "Consumable_ID";
    public final String COLUMN_QUANTITY = "Quantity";

    /**
     * Inserts a LineItem into the database.
     *
     * @param transaction               The Transaction where the LineItem belongs to.
     * @param consumableQuantityPair    Contains details about the LineItem.
     * @return                          Returns true if adding is successful.
     */
    public boolean addLineItem(Transaction transaction, ConsumableQuantityPair consumableQuantityPair) {
        // Prepare the query.
        String query = "INSERT INTO " + TABLE_NAME
                     + " (" + COLUMN_TRANSACTION_ID + ", "
                            + COLUMN_CONSUMABLE_ID + ", "
                            + COLUMN_QUANTITY + " "
                            + "VALUES (? ? ?);";

        // Prepare the variables for binding.
        int transactionId = transaction.getTransactionID();

        Integer consumableId = null;
        if (consumableQuantityPair.getConsumable() != null) {
            consumableId = consumableQuantityPair.getConsumable().getConsumableID();
        }
        else {
            System.err.println("WARNING: Consumable must not be NULL!");
            return false;
        }

        int quantity = consumableQuantityPair.getQuantity();

        // Execute the query and store the result.
        int result = database.executeUpdate(query, new Object[] { transactionId, consumableId, quantity });

        // Return the result. Adding is successful if result != -1.
        return result != -1;
    }

    /**
     * Retrieves a list of LineItems of a specified Transaction from the database.
     *
     * @param transaction   The Transaction where the LineItems belong to.
     * @return              A list of LineIte ms of a specified Transaction from the database.
     */
    public List<LineItem> getLineItemsByTransaction(Transaction transaction) {
        // Prepare the query.
        String query = "SELECT " + COLUMN_TRANSACTION_ID + ", "
                                 + COLUMN_CONSUMABLE_ID + ", "
                                 + COLUMN_QUANTITY + " "
                                 + "FROM " + TABLE_NAME + " "
                                 + "WHERE " + COLUMN_TRANSACTION_ID + " = ?;";

        // Prepare the variables for binding.
        Integer transactionId = null;
        if (transaction != null) {
            transactionId = transaction.getTransactionID();
        }

        // Execute the query and store the result.
        ResultSet rs = database.executeQuery(query, new Object[] { transactionId });

        // Declare object to be returned.
        List<LineItem> lineItems = null;

        try {
            while (rs.next ()) {
                // Retrieve LineItem components from the result.
                int consumableId = rs.getInt(COLUMN_CONSUMABLE_ID);
                int quantity = rs.getInt(COLUMN_QUANTITY);

                Consumable consumable = new Consumable(consumableId, null, null, null, -1, null, null);

                // Create a LineItem object from the components.
                LineItem lineItem = new LineItem(transactionId, consumable, quantity);

                // Initialize the list if null. Else, add the newly created LineItem to the list.
                if (lineItems == null) {
                    lineItems = new ArrayList<>();
                }
                else {
                    lineItems.add(lineItem);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace ();
        }

        // Return the list of LineItems. NOTE: Can be null.
        return lineItems;
    }

    /**
     * Retrieves a list of all LineItems from the database.
     *
     * @return  A list of all LineItems from the database.
     */
    public List<LineItem> getAllLineItems() {
        // Prepare the query.
        String query = "SELECT " + COLUMN_QUANTITY + ", "
                                 + COLUMN_TRANSACTION_ID + ", "
                                 + COLUMN_CONSUMABLE_ID + ", "
                                 + " FROM " + TABLE_NAME + ";";

        // Execute the query and store the result.
        ResultSet rs = database.executeQuery(query, null);

        // Declare list to be returned.
        List<LineItem> lineItems = null;

        try {
            while (rs.next ()) {
                // Retrieve LineItem components from the result.
                int quantity = rs.getInt(COLUMN_QUANTITY);
                int transactionId = rs.getInt(COLUMN_TRANSACTION_ID);
                int consumableId = rs.getInt(COLUMN_CONSUMABLE_ID);

                Consumable consumable = new Consumable(consumableId, null, null, null, -1, null);

                // Create a Category object from the components.
                LineItem lineItem = new LineItem(transactionId, consumable, quantity);

                // Initialize the list if null. Else, add the newly created LineItem to the list.
                if (lineItems == null) {
                    lineItems = new ArrayList<>();
                }
                else {
                    lineItems.add(lineItem);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace ();
        }

        // Return the list of LineItems. NOTE: Can be null.
        return lineItems;
    }

    /**
     * Updates an LineItem in the database with a specific id.
     *
     * @param transaction               The Transaction that the LineItem belongs to
     * @param consumableQuantityPair    The RawItemQuantity pair containing details about the LineItem.
     * @return                          The number of records affected by the update operation.
     */
    public int editLineItem(Transaction transaction, ConsumableQuantityPair consumableQuantityPair) {
        // Prepare the query.
        String query = "INSERT INTO " + TABLE_NAME
                     + " SET " + COLUMN_QUANTITY + " = ?"
                     + " WHERE " + COLUMN_TRANSACTION_ID + " = ? AND " + COLUMN_CONSUMABLE_ID + " = ?;";

        // Prepare the variables for binding.
        int transactionId = transaction.getTransactionID();

        Integer consumableId = null;
        if (consumableQuantityPair.getConsumable() != null) {
            consumableId = consumableQuantityPair.getConsumable().getConsumableID();
        }
        else {
            System.err.println("WARNING: Consumable must not be NULL!");
            return -1;
        }

        int quantity = consumableQuantityPair.getQuantity();

        // Execute the query and store the result.
        int result = database.executeUpdate(query, new Object[] { quantity, transactionId, consumableId });

        // Return the number of records affected by the update operation.
        return result;
    }

    /**
     * Deletes a LineItem in the database given a Transaction and LineItem details.
     *
     * @param transaction               The Transaction where the LineItem belongs to.
     * @param consumableQuantityPair    Contains details about the LineItem.
     * @return                          The number of records affected by the delete operation.
     */
    public int deleteLineItem(Transaction transaction, ConsumableQuantityPair consumableQuantityPair) {
        // Prepare the query.
        String query = "DELETE FROM " + TABLE_NAME + " "
                     + "WHERE " + COLUMN_TRANSACTION_ID + " = ? AND " + COLUMN_CONSUMABLE_ID + " = ?;";

        // Prepare the variables for binding.
        Integer transactionId = null;
        if (transaction != null) {
            transactionId = transaction.getTransactionID();
        }
        else {
            System.err.println("WARNING: Transaction must not be NULL!");
            return -1;
        }
        
        Integer consumableId = null;
        if (consumableQuantityPair.getConsumable() != null) {
            consumableId = consumableQuantityPair.getConsumable().getConsumableID();
        }
        else {
            System.err.println("WARNING: Consumable must not be NULL!");
            return -1;
        }

        // Execute the query and store the result.
        int result = database.executeUpdate(query, new Object[] { transactionId, consumableId });

        // Return the number of records affected by the delete.
        return result;
    }

}