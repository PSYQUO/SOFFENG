package model;

import java.util.List;

public class Consumable {
    public int consumableId;
    private String name;
    private String codeName;
    private Category category;
    private double price;
    private List<RawItem> rawItems;
    private Meal meal;

    public Consumable(String name, String codeName, Category category, double price, List<RawItem> rawItem, Meal meal) {
        this.consumableId = -1;
        this.name = name;
        this.codeName = codeName;
        this.category = category;
        this.price = price;
        this.rawItems = rawItems;
        this.meal = meal;
    }

    public Consumable(int consumableId, String name, String codeName, Category category, double price, List<RawItem> rawItem, Meal meal) {
        this.consumableId = consumableId;
        this.name = name;
        this.codeName = codeName;
        this.category = category;
        this.price = price;
        this.rawItems = rawItems;
        this.meal = meal;
    }

    public int getConsumableID(){
        return consumableId;
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

    public List<RawItem> getRawItems() {
        return rawItems;
    }

    public void setRawItems(List<RawItem> rawItems) {
        this.rawItems = rawItems;
    }

    public Meal getMeal() {
        return meal;
    }
}