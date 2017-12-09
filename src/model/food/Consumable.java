package model.food;

import java.util.*;

/**
 * The representation of a food item. 
 * It is an aggregation of ingredients and can be either a standalone food item or representing a meal. 
 * If representing a meal, it holds a reference to an object (Meal) in order to track 
 * which other food items are included in the actual meal. Also contains a category used 
 * to separate different classes of food or meals.
 */
public class Consumable {
    public int consumableID;
    private String name;
    private String codeName;
    private Category category;
    private double price;
    private ArrayList<Ingredient> ingredients;
    private Meal meal;

    public Consumable(String name, String codeName, Category category, double price, ArrayList<Ingredient> ingredients, Meal meal) {
        this.consumableID = -1;
        this.name = name;
        this.codeName = codeName;
        this.category = category;
        this.price = price;
        this.ingredients = ingredients;
        this.meal = meal;
    }

    public Consumable(String name, String codeName, Category category, double price, ArrayList<Ingredient> ingredients) {
        this.consumableID = -1;
        this.name = name;
        this.codeName = codeName;
        this.category = category;
        this.price = price;
        this.ingredients = ingredients;
    }

    public Consumable(int consumableID, String name, String codeName, Category category, double price, ArrayList<Ingredient> ingredients, Meal meal) {
        this.consumableID = consumableID;
        this.name = name;
        this.codeName = codeName;
        this.category = category;
        this.price = price;
        this.ingredients = ingredients;
        this.meal = meal;
    }

    public Consumable(int consumableID, String name, String codeName, Category category, double price, ArrayList<Ingredient> ingredients) {
        this.consumableID = consumableID;
        this.name = name;
        this.codeName = codeName;
        this.category = category;
        this.price = price;
        this.ingredients = ingredients;
    }

    public int getConsumableID(){
        return consumableID;
    }

    public String getName() {
        return name;
    }

    public String getCodeName() {
        return codeName;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price > 0)
            this.price = price;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Meal getMeal() {
        return meal;
    }
}