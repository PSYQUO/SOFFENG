package model.database.helper;

import model.Incoming;
import model.database.DatabaseHelper;
import model.food.RawItem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Used to access the Incoming database table through specific data operations.
 */
public class IncomingHelper extends DatabaseHelper {

    // Constants referring to the database table columns.
    public final String TABLE_NAME = "Incoming";
    public final String COLUMN_ID = "In_ID";
    public final String COLUMN_RAWITEM_ID = "RawItem_ID";
    public final String COLUMN_REMARKS = "In_Remarks";
    public final String COLUMN_QUANTITY = "In_Quantity";
    public final String COLUMN_DATETIME = "In_DateTime";

    /**
     * Inserts a Category into the database.
     *
     * @param incoming  The Incoming to be inserted.
     * @return          Returns true if adding is successful.
     */
    public boolean addIncoming(Incoming incoming) {
        // Prepare the query.
        String query = "INSERT INTO " + TABLE_NAME
                     + " (" + COLUMN_DATETIME + ", "
                            + COLUMN_QUANTITY + ", "
                            + COLUMN_REMARKS + ", "
                            + COLUMN_RAWITEM_ID + ") "
                            + "VALUES (?, ?, ?, ?)";

        // Prepare the variables for binding.
        LocalDateTime date = incoming.getInDate();
        Timestamp timestamp = Timestamp.valueOf(date); // TODO Check if this works.

        int quantity = incoming.getQuantity();
        String remarks = incoming.getRemarks();

        Integer rawItemId = null;
        if (incoming.getRawItem() != null) {
            rawItemId = incoming.getRawItem().getRawItemID();
        }
        else {
            System.err.println("WARNING: RawItem is NULL!");
            return false;
        }

        // Execute the query and store the result.
        int result = database.executeUpdate(query, new Object[] { timestamp, quantity, remarks, rawItemId });

        // Return the result. Adding is successful if result != -1.
        return result != -1;
    }

    /**
     * Retrieve an Incoming from the database.
     *
     * @param id    An id that refers to the desired Incoming in the database.
     * @return      The desired Incoming. Returns null if the Incoming is not found.
     */
    public Incoming getIncoming(int id) {
        // Prepare the query.
        String query = "SELECT " + COLUMN_REMARKS + ", "
                                 + COLUMN_QUANTITY + ", "
                                 + COLUMN_DATETIME
                                 + COLUMN_RAWITEM_ID + ", "
                                 + COLUMN_ID + " "
                                 + " FROM " + TABLE_NAME
                                 + " WHERE " + COLUMN_ID + " = ?;";

        // Execute the query and store the result.
        ResultSet rs = database.executeQuery(query, new Object[] {id});

        // Declare object to be returned.
        Incoming incoming = null;

        try {
            if (rs.next ()) {
                // Retrieve Incoming components from the result.
//                int columnId = rs.getInt(COLUMN_ID);
                int rawItemId = rs.getInt(COLUMN_RAWITEM_ID);
                String remarks = rs.getString(COLUMN_REMARKS);
                int quantity = rs.getInt(COLUMN_QUANTITY);
                LocalDateTime inDate = rs.getTimestamp(COLUMN_DATETIME).toLocalDateTime();

                RawItem rawItem = new RawItem(rawItemId, null, -1, -1);

                // Create an Incoming object from the components.
                incoming = new Incoming(inDate, quantity, remarks, rawItem);
            }
        } catch (SQLException e) {
            e.printStackTrace ();
        }

        // Return the desired Incoming. NOTE: Can be null.
        return incoming;
    }

    /**
     * Retrieves a list of all Incomings from the database.
     *
     * @return  A list of all Incomings from the database.
     */
    public List<Incoming> getAllIncomings() {
        // Prepare the query.
        String query = "SELECT " + COLUMN_REMARKS + ", "
                                 + COLUMN_QUANTITY + ", "
                                 + COLUMN_DATETIME
                                 + " FROM " + TABLE_NAME + ";";
//        COLUMN_ID + ", "
//                + COLUMN_RAWITEM_ID + ", "

        // Execute the query and store the result.
        ResultSet rs = database.executeQuery(query, null);

        // Declare list to be returned.
        List<Incoming> incomings = new ArrayList<>();

        try {
            while (rs.next ()) {
                // Retrieve Incoming components from the result.
//                int columnId = rs.getInt(COLUMN_ID);
                int rawItemId = rs.getInt(COLUMN_RAWITEM_ID);
                String remarks = rs.getString(COLUMN_REMARKS);
                int quantity = rs.getInt(COLUMN_QUANTITY);
                LocalDateTime inDate = rs.getTimestamp(COLUMN_DATETIME).toLocalDateTime();

                RawItem rawItem = new RawItem(rawItemId, null, -1, -1);

                // Create a Category object from the components.
                Incoming incoming = new Incoming(inDate, quantity, remarks, rawItem);

                // Initialize the list if null. Else, add the newly created Incoming to the list.
                if (incomings == null) {
                    incomings = new ArrayList<>();
                }
                else {
                    incomings.add(incoming);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace ();
        }

        // Return the list of Incomings. NOTE: Can be null.
        return incomings;
    }

    /**
     * Updates an Incoming in the database with a specific id.
     *
     * @param incoming  The category to be edited.
     * @return          The number of records affected by the update operation.
     */
    public int editIncoming(Incoming incoming) {
        // Prepare the query.
        String query = "UPDATE " + TABLE_NAME + " "
                     + "SET " + COLUMN_DATETIME + " = ?, "
                              + COLUMN_QUANTITY + " = ?, "
                              + COLUMN_REMARKS + " = ? "
                              + "WHERE " + COLUMN_ID + " = ?;";

        // Prepare the variables for binding.
        LocalDateTime date = incoming.getInDate();
        Timestamp timestamp = Timestamp.valueOf(date); // TODO Check if this works.

        int quantity = incoming.getQuantity();

        String remarks = incoming.getRemarks();

        Integer rawItemId = null;
        if (incoming.getRawItem() != null) {
            rawItemId = incoming.getRawItem().getRawItemID();
        }
        else {
            System.err.println("WARNING: RawItem is NULL!");
            return -1;
        }

        // Execute the query and store the result.
        int result = database.executeUpdate(query, new Object[] { timestamp, quantity, remarks, rawItemId });

        // Return the number of records affected by the update operation.
        return result;
    }

    /**
     * Deletes a Category in the database with a specific id.
     *
     * @param id    An id referring to the Category to be deleted from the database.
     * @return      The number of records affected by the delete operation.
     */
    public int deleteIncoming(int id) {
        // Prepare the query.
        String query = "DELETE FROM " + TABLE_NAME + " "
                     + "WHERE " + COLUMN_ID + " = ?;";

        // Execute the query and store the result.
        int result = database.executeUpdate(query, new Object[] {id});

        // Return the number of records affected by the delete.
        return result;
    }

}