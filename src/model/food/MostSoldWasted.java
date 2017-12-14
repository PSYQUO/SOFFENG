package model.food;

public class MostSoldWasted
{
    private String name;
    private String quantity;

    public MostSoldWasted(String name, String quantity)
    {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName()
    {
        return name;
    }

    public String getQuantity()
    {
        return quantity;
    }
}