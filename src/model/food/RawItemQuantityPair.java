package model.food;

import model.food.RawItem;

public class RawItemQuantityPair
{
    private RawItem rawItem;
    private int quantity;

    public RawItemQuantityPair(RawItem rawItem, int quantity)
    {
        this.rawItem = rawItem;
        this.quantity = quantity;
    }

    public RawItem getRawItem()
    {
        return rawItem;
    }

    public int getQuantity()
    {
        return quantity;
    }
}