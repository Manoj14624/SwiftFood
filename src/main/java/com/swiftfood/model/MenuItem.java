package com.swiftfood.model;

public class MenuItem {
    private int itemId;
    private String name, description, category, available;
    private double price;

    public int getItemId() { return itemId; }
    public void setItemId(int id) { this.itemId = id; }
    public String getName() { return name; }
    public void setName(String n) { this.name = n; }
    public String getDescription() { return description; }
    public void setDescription(String d) { this.description = d; }
    public String getCategory() { return category; }
    public void setCategory(String c) { this.category = c; }
    public double getPrice() { return price; }
    public void setPrice(double p) { this.price = p; }
    public String getAvailable() { return available; }
    public void setAvailable(String a) { this.available = a; }
}