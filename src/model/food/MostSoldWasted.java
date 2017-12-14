package model.food;

public class MostSoldWasted
{
    private String name;
    private int quantity;
    private double price;

    public MostSoldWasted(String name, double price, int quantity)
    {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getName()
    {
        return name;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public double getPrice()
    {
        return price;
    }
}