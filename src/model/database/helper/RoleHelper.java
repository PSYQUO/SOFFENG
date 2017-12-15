package model.database.helper;

import java.util.List;
import java.util.ArrayList;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Role;
import model.database.DatabaseHelper;
import model.database.DataAccessObject;

/**
 * Used to access the Incoming database table through specific data operations.
 */
public class RoleHelper extends DatabaseHelper {
    
    public final String TABLE_NAME = "Role";
    public final String COLUMN_ID = "Role_ID";
    public final String COLUMN_NAME = "Role_Name";

    public boolean addRole(Role item) {
        String query = "INSERT INTO " + TABLE_NAME 
                     + " (" + COLUMN_NAME + ") "
                            + "VALUES (?);";
        
        String name = item.getRoleName();
                            
        int result = database.executeUpdate(query, new Object[] { name });
        
        return result != -1;
    }

    public Role getRole(int id) {
        String query = "SELECT " + COLUMN_NAME
                                 + " FROM " + TABLE_NAME 
                                 + " WHERE " + COLUMN_ID + " = ?;";

		ResultSet rs = database.executeQuery(query, new Object[] {id});
		Role role = null;

		try {
			if (rs.next ()) {
                String name = rs.getString(COLUMN_NAME);

                role = new Role(id, name);
			}
		} catch (SQLException e) {
			e.printStackTrace ();
        }
        
        return role;
    }

    public List<Role> getAllRoles() {
        String query = "SELECT " + COLUMN_ID + ", "
                                 + COLUMN_NAME
                                 + " FROM " + TABLE_NAME + ";";

		ResultSet rs = database.executeQuery (query, null);
		List<Role> roles = null;

		try {
			while (rs.next()) {
                int id = rs.getInt(COLUMN_ID);
                String name = rs.getString(COLUMN_NAME);

                Role role = new Role(id, name);
                
                if (roles == null) {
                    roles = new ArrayList<>();
                }
                else {
                    roles.add(role);
                }
			}
		} catch (SQLException e) {
			e.printStackTrace ();
        }
        
        return roles;
    }

    public int editRole(int id, Role item) {
        String query = "UPDATE " + TABLE_NAME + " "
                     + "SET " + COLUMN_NAME + " = ?, "
                              + "WHERE " + COLUMN_ID + " = ?;";
                            
        String name = item.getRoleName();
                            
		int result = database.executeUpdate(query, new Object[] { name, id });

		return result;
    }

    public int deleteRole(int id) {
        String query = "DELETE FROM " + TABLE_NAME + " "
                      + "WHERE " + COLUMN_ID + " = ?;";
        
        int result = database.executeUpdate(query, new Object[] {id});
        
        return result;
    }

}