package model;

import java.util.*;

public class Meal {
    private int mealID;
    private ArrayList<ConsumableQuantityPair> addOns;
    
    public Meal(int mealID, ArrayList<ConsumableQuantityPair> addOns) 
    {
        this.mealID = mealID;
        this.addOns = addOns;
    }

    public ArrayList<ConsumableQuantityPair> getAddOns() 
    {
        return addOns;
    }

    public void setAddOns(ArrayList<ConsumableQuantityPair> addOns) 
    {
        this.addOns = addOns;
    }

    public int getMealID()
    {
        return mealID;
    }

    public void setMealID(int mealID)
    {
        this.mealID = mealID;
    }
}