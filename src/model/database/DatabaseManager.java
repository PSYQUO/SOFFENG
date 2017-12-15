package model.database;

import java.sql.*;

/**
 * abstract class DatabaseManager
 * purpose: creates a common parent for database access
 * 		-> allows different types of DBMS i.e. MySQL, Oracle, etc.
 * 	subclasses must implement specific methods which allow the ff.
 * 		-> set up connection to database
 * 		-> perform CRUD operations
 * 		-> close the connection
 */
public abstract class DatabaseManager {

	/**
	 * method setConnection
	 * purpose: sets up the connection to the specific DBMS
	 * method must ensure that connections do not perform autocommit
	 * 		to allow rollbacks
	 * @param host hostname e.g. localhost or ip address of server
	 * @param port port used for db connection
	 * @param dbname database or schema name
	 * @param user username
	 * @param pass password
	 */
	public abstract void setConnection (String host, String port, String dbname, String user, String pass);

	/**
	 * method overload setConnection
	 * @param host hostname e.g. localhost or ip address of server
	 * @param port port used for db connection
	 * @param dbname database or schema name
	 * @param user username
	 * @param pass password
	 */
	public abstract void setConnection (String host, int port, String dbname, String user, String pass);

	/**
	 * executes a retrieve operation
	 * @param query the query requested must use ? wildcards for values to be searched
	 * @param contentValues the values which are to be searched
	 * @return ResultSet of the query
	 */
	public abstract ResultSet executeQuery (String query, Object[] contentValues);

	/**
	 * executes a create, update or delete operation
	 * method must perform rollback in the event of an exception
	 * method must commit changes
	 * method must set save points after commits
	 * @param query the query requested must use ? wildcards for values to be searched
	 * @param contentValues the values which are to be searched
	 * @return results can either be (1) -1 error (2) 0 no affected rows (3) number of rows affected
	 */
	public abstract int executeUpdate (String query, Object[] contentValues);

	/**
	 * executes multiple updates before committing
	 * method must perform rollback in the event of an exception
	 * method must commit changes only after executing all updates
	 * method must only create a save point after committing
	 * @param query an array of queries which will be executed sequentially
	 * @param contentValues an array of values for each query
	 * @return results can either be (1) false - commit error (2) true - committed successfully
	 */
	public abstract boolean executeUpdate (String[] query, Object[][] contentValues);

	/**
	 * closes the connection and statements
	 */
	public abstract void close ();

}
