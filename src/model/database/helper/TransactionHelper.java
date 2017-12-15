package model.database.helper;

import model.User;
import model.database.DatabaseHelper;
import model.transaction.Transaction;
import model.transaction.TransactionBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Used to access the Transaction database table through specific data operations.
 */
public class TransactionHelper extends DatabaseHelper {

    // Constants referring to the database table columns.
    public final String TABLE_NAME = "Transaction";
    public final String COLUMN_TRANSACTION_ID = "Transaction_ID";
    public final String COLUMN_TRANSACTION_DATETIME = "Transaction_DateTime";
    public final String COLUMN_USER_ID = "User_ID";
    public final String COLUMN_CUSTOMER_NUMBER = "Customer_Number";
    public final String COLUMN_TRANSACTION_TYPE = "Transaction_Type";
    public final String COLUMN_CASH = "Cash";
    public final String COLUMN_CHANGE = "Change";
    public final String COLUMN_SUBTOTAL = "Subtotal";
    public final String COLUMN_SENIOR_DISCOUNT = "Senior_Discount";
    public final String COLUMN_TOTAL = "Total";

    /**
     * Inserts a Transaction into the database.
     *
     * @param transaction   The transaction to be inserted.
     * @return              Returns true if adding is successful.
     */
    public boolean addTransaction(Transaction transaction) {
        // Prepare the query.
        String query = "INSERT INTO " + TABLE_NAME + " ("
                     + COLUMN_TRANSACTION_DATETIME + ", "
                     + COLUMN_USER_ID + ", "
                     + COLUMN_CUSTOMER_NUMBER + ", "
                     + COLUMN_TRANSACTION_TYPE + ", "
                     + COLUMN_CASH + ", "
                     + COLUMN_CHANGE + ", "
                     + COLUMN_SUBTOTAL + ", "
                     + COLUMN_SENIOR_DISCOUNT + ", "
                     + COLUMN_TOTAL + ") "
                     + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

        // Prepare the variables for binding.
        LocalDateTime transactionDateTime = transaction.getDate();
        Timestamp transactionTimeStamp = Timestamp.valueOf(transactionDateTime);

        int customerNumber = transaction.getCustomerNo();
        int userId = transaction.getCashier().getUserID();
        String transactionType = transaction.getMode();
        double cash = transaction.getCashReceived();
        double change = transaction.getChange();
        double subtotal = transaction.getSubTotal();
        double seniorDiscount = transaction.getDiscount();
        double total = transaction.getTotal();

        // Execute the query and store the result.
		int result = database.executeUpdate(query, new Object[] { transactionTimeStamp,
                                                                  userId,
                                                                  customerNumber,
                                                                  transactionType,
                                                                  cash,
                                                                  change,
                                                                  subtotal,
                                                                  seniorDiscount,
                                                                  total });

        // Return the result. Adding is successful if result != -1.
		return result != -1;
    }

    /**
     * Retrieve a Transaction from the database.
     *
     * @param id    An id that refers to the desired Transaction in the database.
     * @return      The desired Transaction. Returns null if the Transaction is not found.
     */
    public Transaction getTransaction(int id) {
        // Prepare the query.
        String query = "SELECT " + COLUMN_TRANSACTION_ID + ", "
                                 + COLUMN_TRANSACTION_DATETIME + ", "
                                 + COLUMN_USER_ID + ", "
                                 + COLUMN_CUSTOMER_NUMBER + ", "
                                 + COLUMN_TRANSACTION_TYPE + ", "
                                 + COLUMN_CASH + ", "
                                 + COLUMN_CHANGE + ", "
                                 + COLUMN_SUBTOTAL + ", "
                                 + COLUMN_SENIOR_DISCOUNT + ", "
                                 + COLUMN_TOTAL + " "
                                 + "FROM " + TABLE_NAME + " "
                                 + "WHERE " + COLUMN_TRANSACTION_ID + " = ?;";

        // Execute the query and store the result.
		ResultSet rs = database.executeQuery(query, new Object[] {id});

        // Declare object to be returned.
		Transaction transaction = null;

		try {
			if (rs.next ()) {
                // Retrieve Transaction components from the result.
                int transactionId = rs.getInt(COLUMN_TRANSACTION_ID);
                LocalDateTime transactionDateTime = rs.getTimestamp(COLUMN_TRANSACTION_DATETIME).toLocalDateTime();
                int userId = rs.getInt(COLUMN_USER_ID);
                int customerNumber = rs.getInt(COLUMN_CUSTOMER_NUMBER);
                String transactionType = rs.getString(COLUMN_TRANSACTION_TYPE);
                double cash = rs.getDouble(COLUMN_CASH);
                double change = rs.getDouble(COLUMN_CHANGE);
                double subtotal = rs.getDouble(COLUMN_SUBTOTAL);
                double seniorDiscount = rs.getDouble(COLUMN_SENIOR_DISCOUNT);
                double total = rs.getDouble(COLUMN_TOTAL);

                User user = new User(userId, null, null, null, null);

                // Create a Transaction object from the components.
                TransactionBuilder transactionBuilder = new TransactionBuilder(transactionId);
                transactionBuilder.setDate(transactionDateTime)
                                  .setCashier(user)
                                  .setCustomerNo(customerNumber)
                                  .setMode(transactionType)
                                  .setCashReceived(cash)
                                  .setChange(change)
                                  .setSubTotal(subtotal)
                                  .setDiscount(seniorDiscount)
                                  .setTotal(total);

                transaction = transactionBuilder.build();
			}
		} catch (SQLException e) {
			e.printStackTrace ();
		}

        // Return the desired Transaction. NOTE: Can be null.
		return transaction;
    }

    /**
     * Retrieves a list of all Transactions from the database.
     *
     * @return  A list of all Transactions from the database.
     */
    public List<Transaction> getAllTransactions() {
        // Prepare the query.
        String query = "SELECT " + COLUMN_TRANSACTION_ID + ", "
                                 + COLUMN_TRANSACTION_DATETIME + ", "
                                 + COLUMN_USER_ID + ", "
                                 + COLUMN_CUSTOMER_NUMBER + ", "
                                 + COLUMN_TRANSACTION_TYPE + ", "
                                 + COLUMN_CASH + ", "
                                 + COLUMN_CHANGE + ", "
                                 + COLUMN_SUBTOTAL + ", "
                                 + COLUMN_SENIOR_DISCOUNT + ", "
                                 + COLUMN_TOTAL + " "
                                 + "FROM " + TABLE_NAME + ";";

        // Execute the query and store the result.
		ResultSet rs = database.executeQuery (query, null);

        // Declare list to be returned.
		List<Transaction> transactions = null;

		try {
			while (rs.next()) {
                // Retrieve Transaction components from the result.
                int transactionId = rs.getInt(COLUMN_TRANSACTION_ID);
                LocalDateTime transactionDateTime = rs.getTimestamp(COLUMN_TRANSACTION_DATETIME).toLocalDateTime();
                int userId = rs.getInt(COLUMN_USER_ID);
                int customerNumber = rs.getInt(COLUMN_CUSTOMER_NUMBER);
                String transactionType = rs.getString(COLUMN_TRANSACTION_TYPE);
                double cash = rs.getDouble(COLUMN_CASH);
                double change = rs.getDouble(COLUMN_CHANGE);
                double subtotal = rs.getDouble(COLUMN_SUBTOTAL);
                double seniorDiscount = rs.getDouble(COLUMN_SENIOR_DISCOUNT);
                double total = rs.getDouble(COLUMN_TOTAL);

                User user = new User(userId, null, null, null, null);

                // Create a Transaction object from the components.
                TransactionBuilder transactionBuilder = new TransactionBuilder(transactionId);
                transactionBuilder.setDate(transactionDateTime)
                        .setCashier(user)
                        .setCustomerNo(customerNumber)
                        .setMode(transactionType)
                        .setCashReceived(cash)
                        .setChange(change)
                        .setSubTotal(subtotal)
                        .setDiscount(seniorDiscount)
                        .setTotal(total);

                Transaction transaction = transactionBuilder.build();

                // Initialize the list if null. Else, add the newly created Transaction to the list.
                if (transactions == null) {
                    transactions = new ArrayList<>();
                }
                else {
                    transactions.add(transaction);
                }
			}
		} catch (SQLException e) {
			e.printStackTrace ();
		}

        // Return the list of Categories. NOTE: Can be null.
		return transactions;
    }

    /**
     * Updates a Transaction in the database with a specific id.
     *
     * @param transaction   The category to be edited.
     * @return              The number of records affected by the update operation.
     */
    public int editTransaction(Transaction transaction) {
        String query = "UPDATE " + TABLE_NAME + " "
                     + "SET " + COLUMN_TRANSACTION_DATETIME + " = ?, "
                              + COLUMN_USER_ID + " = ?, "
                              + COLUMN_CUSTOMER_NUMBER + " = ?, "
                              + COLUMN_TRANSACTION_TYPE + " = ?, "
                              + COLUMN_CASH + " = ?, "
                              + COLUMN_CHANGE + " = ?, "
                              + COLUMN_SUBTOTAL + " = ?, "
                              + COLUMN_SENIOR_DISCOUNT + " = ?, "
                              + COLUMN_TOTAL + " = ? "
                              + "WHERE " + COLUMN_TRANSACTION_ID + " = ?;";

        // Prepare the variables for binding.
        LocalDateTime transactionDateTime = transaction.getDate();
        Timestamp transactionTimeStamp = Timestamp.valueOf(transactionDateTime);

        int customerNumber = transaction.getCustomerNo();
        int userId = transaction.getCashier().getUserID();
        String transactionType = transaction.getMode();
        double cash = transaction.getCashReceived();
        double change = transaction.getChange();
        double subtotal = transaction.getSubTotal();
        double seniorDiscount = transaction.getDiscount();
        double total = transaction.getTotal();
        int transactionId = transaction.getTransactionID();

        // Execute the query and store the result.
        int result = database.executeUpdate(query, new Object[] { transactionTimeStamp,
                                                                  userId,
                                                                  customerNumber,
                                                                  transactionType,
                                                                  cash,
                                                                  change,
                                                                  subtotal,
                                                                  seniorDiscount,
                                                                  total,
                                                                  transactionId });

        // Return the number of records affected by the update operation.
		return result;
    }

    /**
     * Deletes a Transaction in the database with a specific id.
     *
     * @param id    An id referring to the Transaction to be deleted from the database.
     * @return      The number of records affected by the delete operation.
     */
    public int deleteTransaction(int id) {
        // Prepare the query.
        String query = "DELETE FROM " + TABLE_NAME + " "
                     + "WHERE " + COLUMN_TRANSACTION_ID + " = ?;";

        // Execute the query and store the result.
        int result = database.executeUpdate(query, new Object[] {id});

        // Return the number of records affected by the delete.
        return result;
    }
}