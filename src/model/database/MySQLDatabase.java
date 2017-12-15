package model.database;

import java.sql.*;

public class MySQLDatabase extends DatabaseManager {

	private static final DatabaseManager instance = new MySQLDatabase ();

	private Connection connection;
	private PreparedStatement preparedStatement;
	private Savepoint savepoint;

	private MySQLDatabase () {}

	public static DatabaseManager getInstance () {
		return instance;
	}

	@Override
	public void setConnection (String host, String port, String dbname, String user, String pass) {
		try {
			Class.forName ("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection
					(
							"jdbc:mysql://" + host +
									":" + port +
									"/" + dbname +
									"?useSSL=false",
							user, pass
					);
			connection.setAutoCommit (false);
			savepoint = connection.setSavepoint ();
		} catch (Exception e) { e.printStackTrace (); }
	}

	@Override
	public void setConnection (String host, int port, String dbname, String user, String pass) {
		setConnection (host, port + "", dbname, user, pass);
	}

	@Override
	public ResultSet executeQuery (String query, Object[] contentValues) {
		ResultSet rs = null;

		try {
			preparedStatement = connection.prepareStatement (query);

			if (contentValues != null) {
				for (int i = 0; i < contentValues.length; i++) {
					preparedStatement.setObject (i + 1, contentValues[i]);
				}
			}

			rs = preparedStatement.executeQuery ();
			connection.commit ();
			connection.setSavepoint ();
		} catch (SQLException e) {
			try {
				connection.rollback (savepoint);
			} catch (SQLException e1) {
				e1.printStackTrace ();
			}
		}

		return rs;
	}

	@Override
	public int executeUpdate (String query, Object[] contentValues) {
		int result = -1;

		try {
			preparedStatement = connection.prepareStatement (query);

			for (int i = 0; i < contentValues.length; i++) {
				preparedStatement.setObject (i + 1, contentValues[i]);
			}

			result = preparedStatement.executeUpdate ();
			connection.commit ();
			savepoint = connection.setSavepoint ();
		} catch (SQLException e) {
			try {
				connection.rollback (savepoint);
			} catch (SQLException e1) {
				e1.printStackTrace ();
			}
		}

		return result;
	}

	@Override
	public boolean executeUpdate (String[] query, Object[][] contentValues) {
		boolean result = false;

		try {
			for (int i = 0; i < query.length; i++) {
				preparedStatement = connection.prepareStatement (query[i]);

				for (int j = 0; j < contentValues[i].length; j++) {
					preparedStatement.setObject (j + 1, contentValues[i][j]);
				}

				preparedStatement.executeUpdate ();
			}

			connection.commit ();
			savepoint = connection.setSavepoint ();
			result = true;
		} catch (Exception e) {
			try {
				connection.rollback (savepoint);
			} catch (SQLException e1) {
				e1.printStackTrace ();
			}
		}

		return result;
	}

	@Override
	public void close () {
		if (preparedStatement !=null) {
			try {
				preparedStatement.close ();
			} catch (SQLException e) {
				e.printStackTrace ();
			}
		}

		if (connection != null) {
			try {
				connection.close ();
			} catch (SQLException e) {
				e.printStackTrace ();
			}
		}
	}

}
