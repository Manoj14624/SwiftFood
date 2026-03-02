package com.infy;

public class FoodItem {

    private String foodName;
    private String foodType;
    private String cuisine;
    private int quantity;
    private double costPerUnit;

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

    public String getFoodType() {
        return foodType;
    }

    public String getCuisine() {
        return cuisine;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getCostPerUnit() {
        return costPerUnit;
    }
}
