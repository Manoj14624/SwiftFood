package com.infy;

public class FoodItem {

    public String foodName;
    public String foodType;   // Veg / Non-Veg
    public String cuisine;
    private int quantity;
    public double costPerUnit;

    public FoodItem(String foodName, String foodType, String cuisine,
                    int quantity, double costPerUnit) {
        this.foodName = foodName;
        this.foodType = foodType;
        this.cuisine = cuisine;
        this.quantity = quantity;
        this.costPerUnit = costPerUnit;
    }

    public double getTotalCost() {
        return quantity * costPerUnit;
    }

    public String getFoodName() {
        return foodName;
    }
}
