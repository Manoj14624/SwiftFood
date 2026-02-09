package com.infy;

import java.util.List;

public class Order {

    private static int counter = 1000;
    private int orderId;
    private List<FoodItem> items;
    private OrderStatus status;
    private DeliveryPartner deliveryPartner;

    public Order(List<FoodItem> items, DeliveryPartner deliveryPartner) {
        this.orderId = ++counter;
        this.items = items;
        this.deliveryPartner = deliveryPartner;
        this.status = OrderStatus.PLACED;
    }

    public void updateStatus(OrderStatus status) {
        this.status = status;
    }

    public void displayOrder() {
        System.out.println("\nOrder ID: " + orderId);
        System.out.println("Order Status: " + status);

        System.out.println("Items:");
        for (FoodItem item : items) {
            System.out.println(
                item.foodName + " x " + item.quantity +
                " = ₹" + item.getTotalCost()
            );
        }

        deliveryPartner.displayDetails();
    }
}
