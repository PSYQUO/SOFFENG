package model;

import java.sql.*;

public class DBConnection
{

    private static final DBConnection dbc = new DBConnection();

    private Connection con;
    private Statement stmt;
    private PreparedStatement pstmt;

    private DBConnection()
    {
    }

    public static DBConnection getConnection()
    {
        return dbc;
    }

    public void setConnection(String db, String user, String pass)
    {
        setConnection("localhost", "3306", db, user, pass);
    }

    public void setConnection(String host, String port, String db, String user, String pass)
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + db + "?useSSL=false", user, pass);
            stmt = con.createStatement();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void setInt(int p, int x) throws SQLException
    {
        pstmt.setInt(p, x);
    }
    
    public void setString(int p, String x) throws SQLException
    {
        pstmt.setString(p, x);
    }

    public void setDouble(int p, double x) throws SQLException
    {
        pstmt.setDouble(p, x);
    }

    public void prepareStatement(String query) throws SQLException
    {
        pstmt = con.prepareStatement(query);
    }

    public void closePS() throws SQLException
    {
        pstmt.close();
    }

    public int executeUpdate() throws SQLException
    {
        return pstmt.executeUpdate();
    }

    public int executeUpdate(String query) throws SQLException
    {
        return stmt.executeUpdate(query);
    }

    public ResultSet executeQuery(String query) throws SQLException
    {
        return stmt.executeQuery(query);
    }
    
    public void closeConnection()
    {
        if(con == null)
            return;
        try
        {
            con.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}