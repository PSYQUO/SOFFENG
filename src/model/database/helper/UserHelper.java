package model.database.helper;

import java.util.List;
import java.util.ArrayList;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Role;
import model.User;
import model.database.DatabaseHelper;
import model.database.DataAccessObject;
import model.food.Consumable;

/**
 * Used to access the Incoming database table through specific data operations.
 */
public class UserHelper extends DatabaseHelper {

    public final String TABLE_NAME = "User";
    public final String COLUMN_ID = "User_ID";
    public final String COLUMN_USERNAME = "User_Username";
    public final String COLUMN_NAME = "User_Name";
    public final String COLUMN_PASSWORD = "User_Password";
    public final String COLUMN_ROLE_ID = "Role_ID";

    public boolean addUser(User user) {
        String query = "INSERT INTO " + TABLE_NAME
                     + " (" + COLUMN_USERNAME + ", "
                            + COLUMN_NAME + ", "
                            + COLUMN_PASSWORD + ", "
                            + COLUMN_ROLE_ID + ") "
                            + "VALUES (?, ?, ?, ?);";

        String uname = user.getUsername();
        String name = user.getUserLoginName();
        String password = user.getPassword();

        Integer roleId = null;
        if (user.getRole() != null) {
            roleId = user.getRole().getRoleID();
        }
        else {
            System.err.println("WARNING: Role must not be NULL! Foreign key constraint will fail!");
        }

		int result = database.executeUpdate(query, new Object[] { uname, name, password, roleId });

		return result != -1;
    }

    public User getUser(int id) {
        String query = "SELECT " + COLUMN_USERNAME + ", "
                                 + COLUMN_NAME + ", "
                                 + COLUMN_PASSWORD + ", "
                                 + COLUMN_ROLE_ID
                                 + " FROM " + TABLE_NAME
                                 + " WHERE " + COLUMN_ID + " = ?;";

		ResultSet rs = database.executeQuery(query, new Object[] {id});
		User user = null;

		try {
			if (rs.next ()) {
                String uname = rs.getString(COLUMN_USERNAME);
                String name = rs.getString(COLUMN_NAME);
                String password = rs.getString(COLUMN_PASSWORD);
                Role role = new Role(rs.getInt(COLUMN_ROLE_ID), null);

                user = new User(id, uname, name, password, role);
			}
		} catch (SQLException e) {
			e.printStackTrace ();
		}

		return user;
    }

    public List<User> getAllUsers() {
        String query = "SELECT " + COLUMN_ID + ", "
                                 + COLUMN_USERNAME + ", "
                                 + COLUMN_NAME + ", "
                                 + COLUMN_PASSWORD + ", "
                                 + COLUMN_ROLE_ID
                                 + " FROM " + TABLE_NAME + ";";

		ResultSet rs = database.executeQuery (query, null);
		List<User> users = null;

		try {
			while (rs.next()) {
                int id = rs.getInt(COLUMN_ID);
                String uname = rs.getString(COLUMN_USERNAME);
                String name = rs.getString(COLUMN_NAME);
                String password = rs.getString(COLUMN_PASSWORD);

                User user = new User(id, uname, name, password, null);

                if (users == null) {
                    users = new ArrayList<>();
                }
                else {
                    users.add(user);
                }
			}
		} catch (SQLException e) {
			e.printStackTrace ();
		}

		return users;
    }

    public int editUser(User user) {
        String query = "UPDATE " + TABLE_NAME + " "
                     + "SET " + COLUMN_USERNAME + " = ?, "
                              + COLUMN_NAME + " = ?, "
                              + COLUMN_PASSWORD + " = ?, "
                              + COLUMN_ROLE_ID + " = ? "
                              + "WHERE " + COLUMN_ID + " = ?;";

        String uname = user.getUsername();
        String name = user.getUserLoginName();
        String password = user.getPassword();
        int id = user.getUserID();

        Integer roleId = null;
        if (user.getRole() != null) {
            roleId = user.getRole().getRoleID();
        }
        else {
            System.err.println("WARNING: Role must not be NULL! Foreign key constraint will fail!");
        }

		int result = database.executeUpdate(query, new Object[] { uname, name, password, roleId, id });

		return result;
    }

    public int deleteUser(int id) {
        String query = "DELETE FROM " + TABLE_NAME + " "
                      + "WHERE " + COLUMN_ID + " = ?;";

        int result = database.executeUpdate(query, new Object[] {id});

        return result;
    }

}