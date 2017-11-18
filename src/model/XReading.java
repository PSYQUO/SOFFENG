package model;

public class XReading
{
    private User user;
    private double total;

    public XReading(User user, double total)
    {
        this.user = user;
        this.total = total;
    }

    public User getUser()
    {
        return user;
    }

    public double getTotal()
    {
        return total;
    }
}