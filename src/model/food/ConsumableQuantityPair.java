package model.food;

import model.food.Consumable;

public class ConsumableQuantityPair
{
    private Consumable consumable;
    private int quantity;

    public ConsumableQuantityPair(Consumable consumable, int quantity)
    {
        this.consumable = consumable;
        this.quantity = quantity;
    }

    public Consumable getConsumable()
    {
        return consumable;
    }

    public int getQuantity()
    {
        return quantity;
    }
}