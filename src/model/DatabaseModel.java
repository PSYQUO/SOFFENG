package model;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

import model.food.*;
import model.transaction.Transaction;
import model.transaction.TransactionBuilder;

public class DatabaseModel
{
    private DBConnection dbc;

    public ArrayList<Category> getCategories()
    {
        dbc = DBConnection.getInstance();
        ArrayList<Category> data = new ArrayList<Category>();
        try
        {
            ResultSet rs = dbc.executeQuery("select * from category");
            while(rs.next())
            {
                Category c = new Category(
                    rs.getInt("category_id"), 
                    rs.getString("category_name"));
                data.add(c);
            }
        }
        catch(Exception e)
        {
            System.err.println(e);
        }
        return data;
    }

    public Category searchCategory(int id)
    {
        dbc = DBConnection.getInstance();
        try
        {
            ResultSet rs = dbc.executeQuery("select * from category where category_id=" + id);
            while(rs.next())
            {
                return new Category(
                    rs.getInt("category_id"), 
                    rs.getString("category_name"));
            }
        }
        catch(Exception e)
        {
            System.err.println(e);
        }
        return null;
    }

    public ArrayList<Role> getRoles()
    {
        dbc = DBConnection.getInstance();
        ArrayList<Role> data = new ArrayList<Role>();
        try
        {
            ResultSet rs = dbc.executeQuery("select * from role");
            while(rs.next())
            {
                Role r = new Role(
                    rs.getInt("role_id"), 
                    rs.getString("role_name"));
                data.add(r);
            }
        }
        catch(Exception e)
        {
            System.err.println(e);
        }
        return data;
    }

    public Role searchRole(int id)
    {
        dbc = DBConnection.getInstance();
        try
        {
            ResultSet rs = dbc.executeQuery("select * from role where role_id=" + id);
            while(rs.next())
            {
                return new Role(
                    rs.getInt("role_id"), 
                    rs.getString("role_name"));
            }
        }
        catch(Exception e)
        {
            System.err.println(e);
        }
        return null;
    }

    public ArrayList<RawItem> getRawItems()
    {
        dbc = DBConnection.getInstance();
        ArrayList<RawItem> data = new ArrayList<RawItem>();
        try
        {
            ResultSet rs = dbc.executeQuery("select * from rawitem");
            while(rs.next())
            {
                RawItem r = new RawItem(
                    rs.getInt("rawitem_id"), 
                    rs.getString("rawitem_name"), 
                    rs.getInt("rawitem_quantity"), 
                    rs.getDouble("rawitem_price"));
                data.add(r);
            }
        }
        catch(Exception e)
        {
            System.err.println(e);
        }
        return data;
    }

    public RawItem searchRawItem(int id)
    {
        dbc = DBConnection.getInstance();
        try
        {
            ResultSet rs = dbc.executeQuery("select * from rawitem where rawitem_id=" + id);
            while(rs.next())
            {
                return new RawItem(
                    rs.getInt("rawitem_id"), 
                    rs.getString("rawitem_name"), 
                    rs.getInt("rawitem_quantity"), 
                    rs.getDouble("rawitem_price"));
            }
        }
        catch(Exception e)
        {
            System.err.println(e);
        }
        return null;
    }

    public ArrayList<User> getUsers()
    {
        dbc = DBConnection.getInstance();
        ArrayList<User> data = new ArrayList<User>();
        try
        {
            ResultSet rs = dbc.executeQuery("select * from user");
            while(rs.next())
            {
                User u = new User(
                    rs.getInt("user_id"), 
                    rs.getString("user_username"), 
                    rs.getString("user_name"), 
                    rs.getString("user_password"), 
                    searchRole(rs.getInt("role_id")));
                data.add(u);
            }
        }
        catch(Exception e)
        {
            System.err.println(e);
        }
        return data;
    }

    public User searchUser(int id)
    {
        dbc = DBConnection.getInstance();
        //ArrayList<User> data = new ArrayList<User>();
        try
        {
            ResultSet rs = dbc.executeQuery("select * from user where user_id=" + id);
            while(rs.next())
            {
                return new User(
                    rs.getInt("user_id"), 
                    rs.getString("user_username"), 
                    rs.getString("user_name"), 
                    rs.getString("user_password"), 
                    searchRole(rs.getInt("role_id")));
            }
        }
        catch(Exception e)
        {
            System.err.println(e);
        }
        return null;
    }

    /**
     * TODO: Consumable BUILDER
     */
    public ArrayList<Consumable> getConsumables()
    {
        dbc = DBConnection.getInstance();
        ArrayList<Consumable> data = new ArrayList<Consumable>();
        try
        {
            ResultSet rs = dbc.executeQuery("select * from consumable c, category cc where c.Category_ID = cc.Category_ID order by consumable_name");
            while(rs.next())
            {
                Consumable c;
                // Meal_ID column is nullable (can contain null values)
                // so we need to check first if rs.getString("meal_id") is null
                // else it's going to throw a NullPointerException.
                if (rs.getString("meal_id") ==  null || rs.getString("meal_id").equals(""))
                {
                    c = new Consumable(
                        rs.getInt("Consumable_ID"),
                        rs.getString("Consumable_Name"),
                        rs.getString("Consumable_CodeName"),
                        searchCategory(rs.getInt("Category_ID")),
                        rs.getDouble("Consumable_Price"),
                        searchIngredientByConsumableID(rs.getInt("consumable_id")));
                }
                else
                {
                    c = new Consumable(
                            rs.getInt("Consumable_ID"),
                            rs.getString("Consumable_Name"),
                            rs.getString("Consumable_CodeName"),
                            searchCategory(rs.getInt("Category_ID")),
                            rs.getDouble("Consumable_Price"),
                            searchIngredientByConsumableID(rs.getInt("consumable_id")),
                            new Meal(rs.getInt("meal_id"), getMealContent(rs.getInt("meal_ID"))));
                }
                data.add(c);
            }
        }
        catch(Exception e)
        {
            System.err.println(e);
        }
        return data;
    }

    /**
     * TODO: NULLS (meals)
     */
    public Consumable searchConsumable(int id)
    {
        dbc = DBConnection.getInstance();
        try
        {
            ResultSet rs = dbc.executeQuery("select * from consumable c, category cc where c.Category_ID=cc.Category_ID and c.consumable_id=" + id);
            while(rs.next())
            {
                return new Consumable(
                    rs.getInt("Consumable_ID"),
                    rs.getString("Consumable_Name"),
                    rs.getString("Consumable_CodeName"),
                    searchCategory(rs.getInt("Category_ID")),
                    rs.getDouble("Consumable_Price"),
                    searchIngredientByConsumableID(rs.getInt("consumable_id")),
                    null);
            }
        }
        catch(Exception e)
        {
            System.err.println(e);
        }
        return null;
    }

    /**
     * TODO: NULLS (meals)
     */
    public ArrayList<Consumable> searchConsumableByCategory(String category)
    {
        dbc = DBConnection.getInstance();
        ArrayList<Consumable> data = new ArrayList<Consumable>();
        try
        {
            ResultSet rs = dbc.executeQuery("select * from consumable c, category cc where c.Category_ID=cc.Category_ID and cc.Category_Name='" + category + "'");
            while(rs.next())
            {
                Consumable c = new Consumable(
                    rs.getInt("Consumable_ID"),
                    rs.getString("Consumable_Name"),
                    rs.getString("Consumable_CodeName"),
                    searchCategory(rs.getInt("Category_ID")),
                    rs.getDouble("Consumable_Price"),
                    searchIngredientByConsumableID(rs.getInt("consumable_id")),
                    null);
                data.add(c);
            }
        }
        catch(Exception e)
        {
            System.err.println(e);
        }
        return data;
    }

    /**
     * TODO: NULLS (meals)
     */
    public ArrayList<Consumable> searchConsumableByCategory(int id)
    {
        dbc = DBConnection.getInstance();
        ArrayList<Consumable> data = new ArrayList<Consumable>();
        try
        {
            ResultSet rs = dbc.executeQuery("select * from consumable c, category cc where c.Category_ID=cc.Category_ID and cc.category_id=" + id);
            while(rs.next())
            {
                Consumable c = new Consumable(
                    rs.getInt("Consumable_ID"),
                    rs.getString("Consumable_Name"),
                    rs.getString("Consumable_CodeName"),
                    searchCategory(rs.getInt("Category_ID")),
                    rs.getDouble("Consumable_Price"),
                    searchIngredientByConsumableID(rs.getInt("consumable_id")),
                    null);
                data.add(c);
            }
        }
        catch(Exception e)
        {
            System.err.println(e);
        }
        return data;
    }

    public ArrayList<LineItem> getLineItems()
    {
        dbc = DBConnection.getInstance();
        ArrayList<LineItem> data = new ArrayList<LineItem>();
        try
        {
            ResultSet rs = dbc.executeQuery("select * from lineitem l, consumable cc, category c where l.Consumable_ID=cc.Consumable_ID and cc.Category_ID=c.Category_ID");
            while(rs.next())
            {
                LineItem l = new LineItem(
                    rs.getInt("transaction_id"), 
                    searchConsumable(rs.getInt("consumable_id")), 
                    rs.getInt("quantity"));
                data.add(l);
            }
        }
        catch(Exception e)
        {
            System.err.println(e);
        }
        return data;
    }

    public ArrayList<LineItem> searchLineItems(int id)
    {
        dbc = DBConnection.getInstance();
        ArrayList<LineItem> data = new ArrayList<LineItem>();
        try
        {
            ResultSet rs = dbc.executeQuery("select * from lineitem l, consumable cc, category c where l.transaction_id=" + id + " and l.Consumable_ID=cc.Consumable_ID and cc.Category_ID=c.Category_ID;");
            while(rs.next())
            {
                LineItem l = new LineItem(
                    rs.getInt("transaction_id"), 
                    searchConsumable(rs.getInt("consumable_id")), 
                    rs.getInt("quantity"));
                data.add(l);
            }
        }
        catch(Exception e)
        {
            System.err.println(e);
        }
        return data;
    }
    
    public ArrayList<XReading> getXReadAll()
    {
        dbc = DBConnection.getInstance();
        ArrayList<XReading> data = new ArrayList<XReading>();
        try
        {
            ResultSet rs = dbc.executeQuery("select date(t.Transaction_DateTime), u.*, sum(t.total) from user u, transaction t where u.User_ID=t.User_ID group by date(t.Transaction_DateTime), u.User_ID order by t.Transaction_DateTime desc");
            while(rs.next())
            {
                XReading x = new XReading(
                    searchUser(rs.getInt("user_id")), 
                    rs.getDouble("sum(t.total)"),
                    rs.getString(1));
                data.add(x);
            }
        }
        catch(Exception e)
        {
            System.err.println(e);
        }
        return data;
    }

    public ArrayList<XReading> getXReadToday()
    {
        dbc = DBConnection.getInstance();
        ArrayList<XReading> data = new ArrayList<XReading>();
        try
        {
            ResultSet rs = dbc.executeQuery("select u.*, sum(t.total) from user u, transaction t where u.User_ID=t.User_ID and t.Transaction_DateTime=curdate() group by u.User_ID");
            while(rs.next())
            {
                XReading x = new XReading(
                    searchUser(rs.getInt("user_id")), 
                    rs.getDouble("sum(t.total)"));
                data.add(x);
            }
        }
        catch(Exception e)
        {
            System.err.println(e);
        }
        return data;
    }

    public ArrayList<XReading> getXReadDate(LocalDate date)
    {
        dbc = DBConnection.getInstance();
        ArrayList<XReading> data = new ArrayList<XReading>();
        try
        {
            ResultSet rs = dbc.executeQuery("select u.user_name, sum(t.total) from user u, transaction t where u.User_ID=t.User_ID and t.Transaction_DateTime=='" + date + "' group by u.User_ID");
            while(rs.next())
            {
                XReading x = new XReading(
                    searchUser(rs.getInt("user_id")), 
                    rs.getDouble("sum(t.total)"));
                data.add(x);
            }
        }
        catch(Exception e)
        {
            System.err.println(e);
        }
        return data;
    }

    // TODO: date to be formatted
    public ArrayList<XReading> getXReadRangeDate(LocalDate dateStart, LocalDate dateEnd)
    {
        dbc = DBConnection.getInstance();
        ArrayList<XReading> data = new ArrayList<XReading>();
        try
        {
            ResultSet rs = dbc.executeQuery("select u.user_name, sum(t.total) from user u, transaction t where u.User_ID=t.User_ID and t.Transaction_DateTime>='" + dateStart + "' and t.Transaction_DateTime<='" + dateEnd + "' group by u.User_ID;");
            while(rs.next())
            {
                XReading x = new XReading(
                    searchUser(rs.getInt("user_id")), 
                    rs.getDouble("sum(t.total)"));
                data.add(x);
            }
        }
        catch(Exception e)
        {
            System.err.println(e);
        }
        return data;
    }

    /**
     * TODO: rs.getString(1) to Date data type
     */
    public ArrayList<ZReading> getZReadAll()
    {
        dbc = DBConnection.getInstance();
        ArrayList<ZReading> data = new ArrayList<ZReading>();
        try
        {
            ResultSet rs = dbc.executeQuery("select date(Transaction_DateTime), sum(total) from transaction group by date(Transaction_datetime) order by transaction_datetime desc");
            while(rs.next())
            {
                ZReading z = new ZReading(
                    rs.getString(1), //must be 1, will have an error if transaction_datetime
                    rs.getDouble("sum(total)"));
                data.add(z);
            }
        }
        catch(Exception e)
        {
            System.err.println(e);
        }
        return data;
    }

    /**
     * TODO: NULLS
     */
    public ArrayList<Transaction> getTransactions()
    {
        dbc = DBConnection.getInstance();
        ArrayList<Transaction> data = new ArrayList<Transaction>();
        try
        {
            ResultSet rs = dbc.executeQuery("select * from transaction");
            while(rs.next())
            {
                TransactionBuilder builder = new TransactionBuilder(rs.getInt("transaction_id"));
                builder.setDate(null)
                       .setCashier(searchUser(rs.getInt("user_id")))
                       .setMode(null)
                       .setCashReceived(rs.getDouble("cash"))
                       .setTotal(rs.getDouble("total"))
                       .setLineItems(searchLineItems(rs.getInt("transaction_id")))
                       .setCustomerNo(rs.getInt("customer_number"));
                data.add(builder.build());
            }
        }
        catch(Exception e)
        {
            System.err.println(e);
        }
        return data;
    }

    /**
     * TODO: NULLS
     */
    public Transaction searchTransaction(int id)
    {
        dbc = DBConnection.getInstance();
        //ArrayList<Transaction> data = new ArrayList<Transaction>();
        try
        {
            ResultSet rs = dbc.executeQuery("select * from transaction where transaction_id=" + id);
            while(rs.next())
            {
                TransactionBuilder builder = new TransactionBuilder(rs.getInt("transaction_id"));
                builder.setDate(null)
                       .setCashier(searchUser(rs.getInt("user_id")))
                       .setMode(null)
                       .setCashReceived(rs.getDouble("cash"))
                       .setTotal(rs.getDouble("total"))
                       .setLineItems(searchLineItems(rs.getInt("transaction_id")))
                       .setCustomerNo(rs.getInt("customer_number"));

                return builder.build();
            }
        }
        catch(Exception e)
        {
            System.err.println(e);
        }
        return null;
    }

    public ArrayList<Ingredient> getIngredients()
    {
        dbc = DBConnection.getInstance();
        ArrayList<Ingredient> data = new ArrayList<Ingredient>();
        try
        {
            ResultSet rs = dbc.executeQuery("select * from ingredient");
            while(rs.next())
            {
                Ingredient i = new Ingredient(
                    searchRawItem(rs.getInt("rawitem_id")), 
                    rs.getInt("quantity"));
                data.add(i);
            }
        }
        catch(Exception e)
        {
            System.err.println(e);
        }
        return data;
    }

    public ArrayList<Ingredient> searchIngredientByConsumableID(int id)
    {
        dbc = DBConnection.getInstance();
        ArrayList<Ingredient> data = new ArrayList<Ingredient>();
        try
        {
            ResultSet rs = dbc.executeQuery("select * from ingredient where consumable_id=" + id);
            while(rs.next())
            {
                Ingredient i = new Ingredient(
                    searchRawItem(rs.getInt("rawitem_id")), 
                    rs.getInt("quantity"));
                data.add(i);
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return data;
    }

    public ArrayList<Col2> getMostandLeastSold()
    {
        dbc = DBConnection.getInstance();
        ArrayList<Col2> data = new ArrayList<Col2>();
        try
        {
            ResultSet rs = dbc.executeQuery("select c.consumable_name, sum(l.quantity) from lineitem l, consumable c where l.consumable_id=c.consumable_id group by l.quantity desc");
            while(rs.next())
            {
                Col2 c = new Col2(
                    rs.getString("consumable_name"), 
                    rs.getString("sum(l.quantity)"));
                data.add(c);
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return data;
    }

    public boolean changePassword(User user, String newPassword)
    {
        try
        {
            dbc = DBConnection.getInstance();
            dbc.prepareStatement("UPDATE user SET User_Password= ? WHERE User_ID= ?");
            dbc.setString(1, newPassword);
            dbc.setInt(2, user.getUserID());

            if(dbc.executeUpdate() == 1)
            {
                return true;
            }
            dbc.closePreparedStatement();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return false;
    }

    public boolean changeRole(User user, Role newRole)
    {
        try
        {
            dbc = DBConnection.getInstance();
            dbc.prepareStatement("UPDATE user SET role_id= ? WHERE User_ID= ?");
            dbc.setInt(1, newRole.getRoleID());
            dbc.setInt(2, user.getUserID());

            if(dbc.executeUpdate() == 1)
            {
                return true;
            }
            dbc.closePreparedStatement();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return false;
    }

    public boolean addRawItem(RawItem newRawItem)
    {
        try
        {
            dbc = DBConnection.getInstance();
            dbc.prepareStatement("INSERT INTO rawitem (RawItem_Name, RawItem_Price, RawItem_Quantity) VALUES (?, ?, ?)");
            dbc.setString(1, newRawItem.getName());
            dbc.setDouble(2, newRawItem.getPrice());
            dbc.setInt(3, newRawItem.getQuantity());

            if(dbc.executeUpdate() == 1)
            {
                return true;
            }
            dbc.closePreparedStatement();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return false;
    }

    public boolean updateRawItem(RawItem newRawItem)
    {
        try
        {
            dbc = DBConnection.getInstance();
            dbc.prepareStatement("UPDATE rawitem SET RawItem_Name = ?, RawItem_Quantity = ?, RawItem_Price = ? WHERE RawItem_ID = ?;");
            dbc.setString(1, newRawItem.getName());
            dbc.setInt(2, newRawItem.getQuantity());
            dbc.setDouble(3, newRawItem.getPrice());
            dbc.setInt(4, newRawItem.rawItemID);

            if(dbc.executeUpdate() == 1)
            {
                return true;
            }
            dbc.closePreparedStatement();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return false;
    }

    public boolean deleteRawItem(RawItem rawItem)
    {
        try
        {
            dbc = DBConnection.getInstance();
            dbc.prepareStatement("DELETE FROM rawitem WHERE rawitem_ID=?");
            dbc.setInt(1, rawItem.getRawItemID());

            if(dbc.executeUpdate() == 1)
            {
                return true;
            }
            dbc.closePreparedStatement();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return false;
    }

    public boolean addCategory(Category newCategory)
    {
        try
        {
            dbc = DBConnection.getInstance();
            dbc.prepareStatement("INSERT INTO category (Category_Name) VALUES (?)");
            dbc.setString(1, newCategory.getCategoryName());

            if(dbc.executeUpdate() == 1)
            {
                return true;
            }
            dbc.closePreparedStatement();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return false;
    }

    /**
     * TODO: NULLS (DATE), subtotal
     */
    public boolean addTransaction(Transaction newTransaction)
    {
        try
        {
            dbc = DBConnection.getInstance();
            dbc.prepareStatement("INSERT INTO transaction (Transaction_DateTime, User_ID, Customer_Number, Transaction_Type, Cash, Change, Subtotal, Senior_Discount, Total) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            dbc.setString(1, null);
            dbc.setInt(2, newTransaction.getCashier().getUserID());
            dbc.setInt(3, newTransaction.getCustomerNo());
            dbc.setString(4, newTransaction.getMode().toString());
            dbc.setDouble(5, newTransaction.getCashReceived());
            dbc.setDouble(6, newTransaction.getChange());
            dbc.setDouble(7, newTransaction.getTotal()); //must be subtotal
            dbc.setDouble(8, newTransaction.getDiscount());
            dbc.setDouble(9, newTransaction.getTotal());

            if(dbc.executeUpdate() == 1)
            {
                return true;
            }
            dbc.closePreparedStatement();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return false;
    }

    public boolean deleteCategory(Category category)
    {
        try
        {
            dbc = DBConnection.getInstance();
            dbc.prepareStatement("DELETE FROM category WHERE category_ID=?");
            dbc.setInt(1, category.getCategoryID());

            if(dbc.executeUpdate() == 1)
            {
                return true;
            }
            dbc.closePreparedStatement();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return false;
    }

    public boolean addUser(User newUser)
    {
        try
        {
            dbc = DBConnection.getInstance();
            dbc.prepareStatement("INSERT INTO user (User_Username, User_Name, User_Password, Role) VALUES (?, ?, ?, ?)");
            dbc.setString(1, newUser.getUsername());
            dbc.setString(2, newUser.getUserLoginName());
            dbc.setString(3, newUser.getPassword());
            dbc.setInt(4, newUser.getRole().getRoleID());

            if(dbc.executeUpdate() == 1)
            {
                return true;
            }
            dbc.closePreparedStatement();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return false;
    }

    public boolean deleteUser(User user)
    {
        try
        {
            dbc = DBConnection.getInstance();
            dbc.prepareStatement("DELETE FROM user WHERE User_ID=?");
            dbc.setInt(1, user.getUserID());

            if(dbc.executeUpdate() == 1)
            {
                return true;
            }
            dbc.closePreparedStatement();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return false;
    }

    public boolean deleteConsumable(Consumable consumable)
    {
        try
        {
            dbc = DBConnection.getInstance();
            dbc.prepareStatement("DELETE FROM consumable WHERE consumable_ID=?");
            dbc.setInt(1, consumable.getConsumableID());

            if(dbc.executeUpdate() == 1)
            {
                return true;
            }

            dbc.closePreparedStatement();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return false;
    }

    public boolean deleteIngredients(Consumable consumable)
    {
        try
        {
            dbc = DBConnection.getInstance();
            dbc.prepareStatement("DELETE FROM ingredient WHERE consumable_ID=?");
            dbc.setInt(1, consumable.getConsumableID());

            if(dbc.executeUpdate() == 1)
            {
                return true;
            }
            dbc.closePreparedStatement();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return false;
    }


    /**
     * TODO: with NULL LINE
     */
    public boolean addIncoming(Incoming incoming, RawItem rawItem)
    {
        try
        {
            dbc = DBConnection.getInstance();
            dbc.prepareStatement("INSERT INTO incoming (In_DateTime, In_Quantity, In_Remarks, RawItem_ID) VALUES (?, ?, ?, ?)");
            dbc.setString(1, null); // set date
            dbc.setInt(2, incoming.getQuantity());
            dbc.setString(3, incoming.getRemarks());
            dbc.setInt(4, rawItem.getRawItemID());

            if(dbc.executeUpdate() == 1)
            {
                return true;
            }
            dbc.closePreparedStatement();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return false;
    }

    /**
     * TODO: with NULL LINE
     */
    public boolean addOutgoing(Outgoing outgoing, RawItem rawItem)
    {
        try
        {
            dbc = DBConnection.getInstance();
            dbc.prepareStatement("INSERT INTO outgoing (Out_DateTime, Out_Quantity, Out_Remarks, RawItem_ID) VALUES (?, ?, ?, ?)");
            dbc.setString(1, null); // set date
            dbc.setInt(2, outgoing.getQuantity());
            dbc.setString(3, outgoing.getRemarks());
            dbc.setInt(4, rawItem.getRawItemID());

            if(dbc.executeUpdate() == 1)
            {
                return true;
            }
            dbc.closePreparedStatement();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return false;
    }

    public ArrayList<ConsumableQuantityPair> getMealContent(int mealID)
    {
        dbc = DBConnection.getInstance();
        ArrayList<ConsumableQuantityPair> data = new ArrayList<ConsumableQuantityPair>();
        try
        {
            ResultSet rs = dbc.executeQuery("select * from meal where meal_id="+mealID);
            while(rs.next())
            {
                ConsumableQuantityPair c = new ConsumableQuantityPair(
                    searchConsumable(rs.getInt("addons")), 
                    rs.getInt("quantity"));
                data.add(c);
            }
        }
        catch(Exception e)
        {
            System.err.println(e);
        }
        return data;
    }

    public RestoInfo getRestoInfo(int restoID)
    {
        dbc = DBConnection.getInstance();
        try
        {
            ResultSet rs = dbc.executeQuery("select * from resto_info where resto_id="+restoID);
            while(rs.next())
            {
                return new RestoInfo(
                    rs.getString("telephone"), 
                    rs.getString("address"));
            }
        }
        catch(Exception e)
        {
            System.err.println(e);
        }
        return null;
    }

    public boolean deleteTransaction(Transaction transaction)
    {
        try
        {
            dbc = DBConnection.getInstance();
            dbc.prepareStatement("DELETE FROM transaction WHERE transaction_ID=?");
            dbc.setInt(1, transaction.getTransactionID());

            if(dbc.executeUpdate() == 1)
            {
                return true;
            }
            dbc.closePreparedStatement();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return false;
    }

    public boolean addConsumable(Consumable newConsumable)
    {
        try
        {
            dbc = DBConnection.getInstance();
            dbc.prepareStatement("INSERT INTO consumable (Consumable_Name, Consumable_CodeName, Consumable_Price, Meal_ID, Category_ID) VALUES (?, ?, ?, ?, ?)");
            dbc.setString(1, newConsumable.getName());
            dbc.setString(2, newConsumable.getCodeName());
            dbc.setDouble(3, newConsumable.getPrice());
            dbc.setInt(4, newConsumable.getMeal().getMealID());
            dbc.setInt(5, newConsumable.getCategory().getCategoryID());

            if(dbc.executeUpdate() == 1)
            {
                return true;
            }
            dbc.closePreparedStatement();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return false;
    }

    public boolean addMeal(Consumable consumable, ConsumableQuantityPair cqp) // 1:1
    {
        try
        {
            dbc = DBConnection.getInstance();
            dbc.prepareStatement("INSERT INTO meal (meal_id, consumable_id, quantity) VALUES (?, ?, ?)");
            dbc.setInt(1, consumable.getMeal().getMealID());
            dbc.setInt(2, cqp.getConsumable().getConsumableID());
            dbc.setInt(3, cqp.getQuantity());

            if(dbc.executeUpdate() == 1)
            {
                return true;
            }
            dbc.closePreparedStatement();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return false;
    }

    public void addMeals(Consumable consumable, ArrayList<ConsumableQuantityPair> cqps) // many
    {
        for(int i=0; i<cqps.size(); i++)
        {
            addMeal(consumable, cqps.get(i));
        }
    }

    public boolean addLineItem(Transaction transaction, ConsumableQuantityPair cqp) // 1:1
    {
        try
        {
            dbc = DBConnection.getInstance();
            dbc.prepareStatement("INSERT INTO lineitem (transaction_id, consumable_id, quantity) VALUES (?, ?, ?)");
            dbc.setInt(1, transaction.getTransactionID());
            dbc.setInt(2, cqp.getConsumable().getConsumableID());
            dbc.setInt(3, cqp.getQuantity());

            if(dbc.executeUpdate() == 1)
            {
                return true;
            }
            dbc.closePreparedStatement();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return false;
    }

    public void addLineItems(Transaction transaction, ArrayList<ConsumableQuantityPair> cqps) // many
    {
        for(int i=0; i<cqps.size(); i++)
        {
            addLineItem(transaction, cqps.get(i));
        }
    }

    public boolean addIngredient(Consumable consumable, RawItemQuantityPair rqp) // 1:1
    {
        try
        {
            dbc = DBConnection.getInstance();
            dbc.prepareStatement("INSERT INTO ingredient (Consumable_id, rawitem_id, quantity) VALUES (?, ?, ?)");
            dbc.setInt(1, consumable.getConsumableID());
            dbc.setInt(2, rqp.getRawItem().getRawItemID());
            dbc.setInt(3, rqp.getQuantity());

            if(dbc.executeUpdate() == 1)
            {
                return true;
            }
            dbc.closePreparedStatement();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return false;
    }

    public void addIngredients(Consumable consumable, ArrayList<RawItemQuantityPair> rqps) // many
    {
        for(int i=0; i<rqps.size(); i++)
        {
            addIngredient(consumable, rqps.get(i));
        }
    }

    /**
     * delete all line item for 1 transaction
     */
     public boolean deleteLineItems(Transaction transaction)
    {
        try
        {
            dbc = DBConnection.getInstance();
            dbc.prepareStatement("DELETE FROM lineitem WHERE transaction_ID=?");
            dbc.setInt(1, transaction.getTransactionID());

            if(dbc.executeUpdate() == 1)
            {
                return true;
            }
            dbc.closePreparedStatement();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return false;
    }
}