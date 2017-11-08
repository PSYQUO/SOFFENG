package model;

import java.util.*;

public class MealConsumable extends Consumable
{
    private int quantity;

    public MealConsumable(String name, String codeName, Category category, double price, ArrayList<Ingredient> ingredients, Meal meal, int quantity)
    {
        super(name, codeName, category, price, ingredients, meal);
        this.quantity = quantity;
    }

    public MealConsumable(int consumableID, String name, String codeName, Category category, double price, ArrayList<Ingredient> ingredients, Meal meal, int quantity)
    {
        super(consumableID, name, codeName, category, price, ingredients, meal);
        this.quantity = quantity;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int newQuantity)
    {
        this.quantity = newQuantity;
    }
}