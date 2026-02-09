package com.infy;

import java.util.ArrayList;
import java.util.List;

public class Bill {

    private Customer customer;
    private List<FoodItem> orderList;

    private double totalAmount;
    private double discount;
    private double finalAmount;
    private double deliveryFee = 40;

    private static List<Bill> paymentHistory = new ArrayList<>();

    public Bill(Customer customer, List<FoodItem> orderList) {
        this.customer = customer;
        this.orderList = orderList;
        calculateBill();
        paymentHistory.add(this);
    }

    private void calculateBill() {
        for (FoodItem item : orderList) {
            totalAmount += item.getQuantity() * item.getCostPerUnit();
        }

        discount = customer.getDiscount(totalAmount);

        if (customer.isFreeDelivery()) {
            deliveryFee = 0;
        }

        finalAmount = totalAmount - discount + deliveryFee;
    }

    public void generateBill() {
        System.out.println("\n--------- BILL ---------");
        for (FoodItem item : orderList) {
            System.out.println(
                item.getFoodName() + " x " + item.getQuantity() +
                " = ₹" + (item.getQuantity() * item.getCostPerUnit())
            );
        }

        System.out.println("------------------------");
        System.out.println("Total Amount : ₹" + totalAmount);
        System.out.println("Discount     : ₹" + discount);
        System.out.println("Delivery Fee : ₹" + deliveryFee);
        System.out.println("Final Amount : ₹" + finalAmount);
        System.out.println("------------------------");
    }

    // NEW FEATURE
    public static void showPaymentHistory() {
        System.out.println("\n====== PAYMENT HISTORY ======");
        for (Bill bill : paymentHistory) {
            System.out.println(
                bill.customer.getCxName() +
                " | Paid ₹" + bill.finalAmount
            );
        }
    }
}
