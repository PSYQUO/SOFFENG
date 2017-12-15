package model.database;

import java.util.List;

/**
 * Used to access the database through specific data operations.
 */
public interface DataAccessObject<T> {

    /**
     * Inserts an item into the database.
     * 
     * @param item  An entity in the database.
     * @return      A boolean that is true if adding is successful.
     */
    public boolean addItem(T item);

    /**
     * Retrieves an item from the database.
     * 
     * @param id    Refers to a specific record in a database table.
     * @return      An entity in the database.
     */
    public T getItem(int id);
    
    /**
     * Retrieves all items from the database.
     * 
     * @return      A list of all records of a specific type in the database.
     */
    public List<T> getAllItems();
    
    /**
     * Updates an item in the database.
     * 
     * @param item  An entity in the database.
     * @return      The number of rows affected by the operation.
     */
    public int editItem(int id, T item);
    
    /**
     * Deletes an item from the database.
     * 
     * @param id    A generic entity in the database.
     * @return      The number of rows affected by the operation.
     */
    public int deleteItem(int id);

}