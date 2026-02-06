package com.infy;

import java.util.List;

public class Bill {

    private Customer customer;
    private List<FoodItem> foodItems;

    public Bill(Customer customer, List<FoodItem> foodItems) {
        this.customer = customer;
        this.foodItems = foodItems;
    }

    public void generateBill() {
        double total = foodItems.stream()
                                .mapToDouble(FoodItem::getTotalCost)
                                .sum();

        double discount = customer.getDiscount(total);
        double deliveryFee = customer.getDeliveryFee();
        double finalAmount = total - discount + deliveryFee;

        System.out.println("Customer Name : " + customer.getCustomerName());
        System.out.println("Total Amount  : ₹" + total);
        System.out.println("Discount      : ₹" + discount);
        System.out.println("Delivery Fee  : ₹" + deliveryFee);
        System.out.println("Final Payable : ₹" + finalAmount);
    }
}
