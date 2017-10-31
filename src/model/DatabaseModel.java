package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.time.LocalDate;

public class DatabaseModel
{
    private DBConnection dbc;

    public DatabaseModel()
    {
    }

    public ArrayList<Category> getCategories()
    {
        dbc = DBConnection.getConnection();
        ArrayList<Category> data = new ArrayList<Category>();
        try
        {
            ResultSet rs = dbc.executeQuery("select * from category");
            while(rs.next())
            {
                Category c = new Category(rs.getInt(1), rs.getString(2));
                data.add(c);
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        finally
        {
            dbc.closeConnection();
        }
        return data;
    }

    public Category searchCategory(int id)
    {
        dbc = DBConnection.getConnection();
        try
        {
            ResultSet rs = dbc.executeQuery("select * from category where category_id="+id);
            while(rs.next())
            {
                return new Category(rs.getInt(1), rs.getString(2));
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        finally
        {
            dbc.closeConnection();
        }
        return null;
    }

    public ArrayList<Role> getRoles()
    {
        dbc = DBConnection.getConnection();
        ArrayList<Role> data = new ArrayList<Role>();
        try
        {
            ResultSet rs = dbc.executeQuery("select * from role");
            while(rs.next())
            {
                Role r = new Role(rs.getInt(1), rs.getString(2));
                data.add(r);
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        finally
        {
            dbc.closeConnection();
        }
        return data;
    }

    public Role searchRole(int id)
    {
        dbc = DBConnection.getConnection();
        try
        {
            ResultSet rs = dbc.executeQuery("select * from role where role_id="+id);
            while(rs.next())
            {
                return new Role(rs.getInt(1), rs.getString(2));
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        finally
        {
            dbc.closeConnection();
        }
        return null;
    }

    public ArrayList<RawItem> getRawItems()
    {
        dbc = DBConnection.getConnection();
        ArrayList<RawItem> data = new ArrayList<RawItem>();
        try
        {
            ResultSet rs = dbc.executeQuery("select * from rawitem");
            while(rs.next())
            {
                RawItem r = new RawItem(rs.getInt(1), rs.getString(2), rs.getInt(4), rs.getDouble(3));
                data.add(r);
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        finally
        {
            dbc.closeConnection();
        }
        return data;
    }

    public RawItem searchRawItem(int id)
    {
        dbc = DBConnection.getConnection();
        try
        {
            ResultSet rs = dbc.executeQuery("select * from rawitem where rawitem_id="+id);
            while(rs.next())
            {
                return new RawItem(rs.getInt(1), rs.getString(2), rs.getInt(4), rs.getDouble(3));
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        finally
        {
            dbc.closeConnection();
        }
        return null;
    }

    public ArrayList<User> getUsers()
    {
        dbc = DBConnection.getConnection();
        ArrayList<User> data = new ArrayList<User>();
        try
        {
            ResultSet rs = dbc.executeQuery("select * from user");
            while(rs.next())
            {
                User u = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), searchRole(rs.getInt(5)));
                data.add(u);
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        finally
        {
            dbc.closeConnection();
        }
        return data;
    }

    public User searchUser(int id)
    {
        dbc = DBConnection.getConnection();
        ArrayList<User> data = new ArrayList<User>();
        try
        {
            ResultSet rs = dbc.executeQuery("select * from user where user_id="+id);
            while(rs.next())
            {
                return new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), searchRole(rs.getInt(5)));
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        finally
        {
            dbc.closeConnection();
        }
        return null;
    }

    /**
     * TODO: NULLS
     */
    public ArrayList<Consumable> getConsumables()
    {
        dbc = DBConnection.getConnection();
        ArrayList<Consumable> data = new ArrayList<Consumable>();
        try
        {
            ResultSet rs = dbc.executeQuery("select * from consumable c, category cc where c.Category_ID=cc.Category_ID");
            while(rs.next())
            {
                Consumable c = new Consumable(rs.getInt(1), rs.getString(2), rs.getString(3), searchCategory(rs.getInt(6)), rs.getDouble(4), null, null);
                data.add(c);
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        finally
        {
            dbc.closeConnection();
        }
        return data;
    }

    /**
     * TODO: NULLS
     */
    public Consumable searchConsumable(int id)
    {
        dbc = DBConnection.getConnection();
        try
        {
            ResultSet rs = dbc.executeQuery("select * from consumable c, category cc where c.Category_ID=cc.Category_ID and c.consumable_id="+id);
            while(rs.next())
            {
                return new Consumable(rs.getInt(1), rs.getString(2), rs.getString(3), searchCategory(rs.getInt(6)), rs.getDouble(4), null, null);
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        finally
        {
            dbc.closeConnection();
        }
        return null;
    }

    /**
     * TODO: NULLS
     */
    public ArrayList<Consumable> searchConsumableByCategory(String category)
    {
        dbc = DBConnection.getConnection();
        ArrayList<Consumable> data = new ArrayList<Consumable>();
        try
        {
            ResultSet rs = dbc.executeQuery("select * from consumable c, category cc where c.Category_ID=cc.Category_ID and cc.Category_Name='" + category + "'");
            while(rs.next())
            {
                Consumable c = new Consumable(rs.getInt(1), rs.getString(2), rs.getString(3), searchCategory(rs.getInt(6)), rs.getDouble(4), null, null);
                data.add(c);
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        finally
        {
            dbc.closeConnection();
        }
        return data;
    }

    /**
     * TODO: NULLS
     */
    public ArrayList<Consumable> searchConsumableByCategory(int id)
    {
        dbc = DBConnection.getConnection();
        ArrayList<Consumable> data = new ArrayList<Consumable>();
        try
        {
            ResultSet rs = dbc.executeQuery("select * from consumable c, category cc where c.Category_ID=cc.Category_ID and cc.category_id="+id);
            while(rs.next())
            {
                Consumable c = new Consumable(rs.getInt(1), rs.getString(2), rs.getString(3), searchCategory(rs.getInt(6)), rs.getDouble(4), null, null);
                data.add(c);
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        finally
        {
            dbc.closeConnection();
        }
        return data;
    }

    public ArrayList<LineItem> getLineItems()
    {
        dbc = DBConnection.getConnection();
        ArrayList<LineItem> data = new ArrayList<LineItem>();
        try
        {
            ResultSet rs = dbc.executeQuery("select * from lineitem l, consumable cc, category c where l.Consumable_ID=cc.Consumable_ID and cc.Category_ID=c.Category_ID;");
            while(rs.next())
            {
                LineItem l = new LineItem(rs.getInt(1), searchConsumable(rs.getInt(2)), rs.getInt(3));
                data.add(l);
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        finally
        {
            dbc.closeConnection();
        }
        return data;
    }

    public ArrayList<LineItem> searchLineItems(int id)
    {
        dbc = DBConnection.getConnection();
        ArrayList<LineItem> data = new ArrayList<LineItem>();
        try
        {
            ResultSet rs = dbc.executeQuery("select * from lineitem l, consumable cc, category c where l.transaction_id=" + id + " and l.Consumable_ID=cc.Consumable_ID and cc.Category_ID=c.Category_ID;");
            while(rs.next())
            {
                LineItem l = new LineItem(rs.getInt(1), searchConsumable(rs.getInt(2)), rs.getInt(3));
                data.add(l);
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        finally
        {
            dbc.closeConnection();
        }
        return data;
    }

    public ArrayList<XReading> getXReadToday()
    {
        dbc = DBConnection.getConnection();
        ArrayList<XReading> data = new ArrayList<XReading>();
        try
        {
            ResultSet rs = dbc.executeQuery("select u.*, sum(t.total) from user u, transaction t where u.User_ID=t.User_ID and t.Trans_DateTime=curdate() group by u.User_ID");
            while(rs.next())
            {
                XReading x = new XReading(searchUser(rs.getInt(1)), rs.getDouble(8));
                data.add(x);
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        finally
        {
            dbc.closeConnection();
        }
        return data;
    }

    public ArrayList<XReading> getXReadDate(LocalDate date)
    {
        dbc = DBConnection.getConnection();
        ArrayList<XReading> data = new ArrayList<XReading>();
        try
        {
            ResultSet rs = dbc.executeQuery("select u.user_name, sum(t.total) from user u, transaction t where u.User_ID=t.User_ID and t.Trans_DateTime=='"+date+"' group by u.User_ID");
            while(rs.next())
            {
                XReading x = new XReading(searchUser(rs.getInt(1)), rs.getDouble(8));
                data.add(x);
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        finally
        {
            dbc.closeConnection();
        }
        return data;
    }

    public ArrayList<XReading> getXReadRangeDate(LocalDate dateStart, LocalDate dateEnd)
    {
        dbc = DBConnection.getConnection();
        ArrayList<XReading> data = new ArrayList<XReading>();
        try
        {
            ResultSet rs = dbc.executeQuery("select u.user_name, sum(t.total) from user u, transaction t where u.User_ID=t.User_ID and t.Trans_DateTime>='"+dateStart+"' and t.Trans_DateTime<='"+dateEnd+"' group by u.User_ID;");
            while(rs.next())
            {
                XReading x = new XReading(searchUser(rs.getInt(1)), rs.getDouble(8));
                data.add(x);
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        finally
        {
            dbc.closeConnection();
        }
        return data;
    }

    /**
     * TODO: transaction mode line 438 null
     */
    public ArrayList<Transaction> getTransactions()
    {
        dbc = DBConnection.getConnection();
        ArrayList<Transaction> data = new ArrayList<Transaction>();
        try
        {
            ResultSet rs = dbc.executeQuery("select * from transaction");
            while(rs.next())
            {
                Transaction t = new Transaction(rs.getInt(1), null, searchUser(rs.getInt(3)), null, rs.getDouble(6), rs.getDouble(7), rs.getDouble(9), rs.getDouble(10), searchLineItems(rs.getInt(1)), rs.getInt(4));
                data.add(t);
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        finally
        {
            dbc.closeConnection();
        }
        return data;
    }

    /**
     * TODO: transaction mode line 465 null
     */
    public Transaction searchTransaction(int id)
    {
        dbc = DBConnection.getConnection();
        ArrayList<Transaction> data = new ArrayList<Transaction>();
        try
        {
            ResultSet rs = dbc.executeQuery("select * from transaction where transaction_id="+id);
            while(rs.next())
            {
                return new Transaction(rs.getInt(1), null, searchUser(rs.getInt(3)), null, rs.getDouble(6), rs.getDouble(7), rs.getDouble(9), rs.getDouble(10), searchLineItems(rs.getInt(1)), rs.getInt(4));
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        finally
        {
            dbc.closeConnection();
        }
        return null;
    }

    public ArrayList<Ingredient> getIngredients()
    {
        dbc = DBConnection.getConnection();
        ArrayList<Ingredient> data = new ArrayList<Ingredient>();
        try
        {
            ResultSet rs = dbc.executeQuery("select * from ingredient");
            while(rs.next())
            {
                Ingredient i = new Ingredient(searchRawItem(rs.getInt(1)), rs.getInt(2));
                data.add(i);
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        finally
        {
            dbc.closeConnection();
        }
        return data;
    }

    public ArrayList<Ingredient> searchIngredientByConsumable(int id)
    {
        dbc = DBConnection.getConnection();
        ArrayList<Ingredient> data = new ArrayList<Ingredient>();
        try
        {
            ResultSet rs = dbc.executeQuery("select * from ingredient where consumable_id="+id);
            while(rs.next())
            {
                Ingredient i = new Ingredient(searchRawItem(rs.getInt(1)), rs.getInt(2));
                data.add(i);
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        finally
        {
            dbc.closeConnection();
        }
        return data;
    }

    public boolean changePassword(User user, String newPassword)
    {
        try
        {
            dbc = DBConnection.getConnection();
            if(dbc.executeUpdate("UPDATE user SET User_Password="+newPassword+" WHERE User_ID="+user.getUserID())==1)
            {
                return true;
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        finally
        {
            dbc.closeConnection();
        }
        return false;
    }
}