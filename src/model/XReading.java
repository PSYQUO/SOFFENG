package model;

public class XReading
{
    private User user;
    private double total;
    private String date;

    public XReading(User user, double total)
    {
        this.user = user;
        this.total = total;
    }


    public XReading(User user, double total, String date)
    {
        this.user = user;
        this.total = total;
        this.date = date;
    }

    public User getUser()
    {
        return user;
    }

    public double getTotal()
    {
        return total;
    }

    public String getDate()
    {
        return date;
    }
}