package model.database.helper;

import model.database.DatabaseHelper;
import model.food.Category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Used to access the Category database table through specific data operations.
 */
public class CategoryHelper extends DatabaseHelper {

    // Constants referring to the database table columns.
    public final String TABLE_NAME = "Category";
    public final String COLUMN_CATEGORY_ID = "Category_ID";
    public final String COLUMN_CATEGORY_NAME = "Category_Name";

    /**
     * Inserts a Category into the database.
     * 
     * @param category  The category to be inserted.
     * @return          Returns true if adding is successful.
     */
    public boolean addCategory(Category category) {
        // Prepare the query.
        String query = "INSERT INTO " + TABLE_NAME + " (" + COLUMN_CATEGORY_NAME + ") " + "VALUES (?);";
        
        // Prepare the variables for binding.
        String categoryName = category.getCategoryName();
        
        // Execute the query and store the result.
        int result = database.executeUpdate(query, new Object[] { categoryName });

        // Return the result. Adding is successful if result != -1.
        return result != -1;
    }

    /**
     * Retrieve a Category from the database.
     *
     * @param id    An id that refers to the desired Category in the database.
     * @return      The desired Category. Returns null if the Category is not found.
     */
    public Category getCategory(int id) {
        // Prepare the query.
        String query = "SELECT " + COLUMN_CATEGORY_ID + ", " + COLUMN_CATEGORY_NAME + " "
                     + "FROM " + TABLE_NAME + "WHERE " + COLUMN_CATEGORY_ID + " = ?;";

        // Execute the query and store the result.
        ResultSet rs = database.executeQuery(query, new Object[] {id});

        // Declare object to be returned.
        Category category = null;

        try {
            if (rs.next()) {
                // Retrieve Category components from the result.
                int categoryId = rs.getInt(COLUMN_CATEGORY_ID);
                String categoryName = rs.getString(COLUMN_CATEGORY_NAME);

                // Create a Category object from the components.
                category = new Category(categoryId, categoryName);
            }
        } catch (SQLException e) {
            e.printStackTrace ();
        }

        // Return the desired Category. NOTE: Can be null.
        return category;
    }

    /**
     * Retrieves a list of all Categories from the database.
     *
     * @return  A list of all Categories from the database.
     */
    public List<Category> getAllCategories() {
        // Prepare the query.
        String query = "SELECT " + COLUMN_CATEGORY_ID + ", " + COLUMN_CATEGORY_NAME + " "
                     + "FROM " + TABLE_NAME + ";";

        // Execute the query and store the result.
        ResultSet rs = database.executeQuery(query, null);

        // Declare list to be returned.
        List<Category> categories = null;

        try {
            while (rs.next()) {
                // Retrieve Category components from the result.
                int categoryId = rs.getInt(COLUMN_CATEGORY_ID);
                String categoryName = rs.getString(COLUMN_CATEGORY_NAME);

                // Create a Category object from the components.
                Category category = new Category(categoryId, categoryName);

                // Initialize the list if null. Else, add the newly created Category to the list.
                if (categories == null) {
                    categories = new ArrayList<>();
                }
                else {
                    categories.add(category);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace ();
        }

        // Return the list of Categories. NOTE: Can be null.
        return categories;
    }

    /**
     * Updates a Category in the database with a specific id.
     *
     * @param category  The category to be edited.
     * @return          The number of records affected by the update operation.
     */
    public int editCategory(Category category) {
        // Prepare the query.
        String query = "UPDATE " + TABLE_NAME + " SET " + COLUMN_CATEGORY_NAME + " = ? "
                     + "WHERE " + COLUMN_CATEGORY_ID + " = ?;";

        // Prepare the variables for binding.
        int categoryId = category.getCategoryID();
        String categoryName = category.getCategoryName();

        // Execute the query and store the result.
        int result = database.executeUpdate(query, new Object[] { categoryName, categoryId });

        // Return the number of records affected by the update operation.
        return result;
    }

    /**
     * Deletes a Category in the database with a specific id.
     *
     * @param id    An id referring to the Category to be deleted from the database.
     * @return      The number of records affected by the delete operation.
     */
    public int deleteCategory(int id) {
        // Prepare the query.
        String query = "DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_CATEGORY_ID + " = ?;";

        // Execute the query and store the result.
        int result = database.executeUpdate(query, new Object[] {id});

        // Return the number of records affected by the delete.
        return result;
    }
}