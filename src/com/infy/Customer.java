package com.infy;

import java.util.ArrayList;
import java.util.List;

public abstract class Customer {

    protected int customerId;
    protected String name;
    protected String address;
    protected String mobile;

    protected List<Order> orderHistory = new ArrayList<>();
    protected List<Payment> paymentHistory = new ArrayList<>();

    public Customer(int customerId, String name, String address, String mobile) {
        this.customerId = customerId;
        this.name = name;
        this.address = address;
        this.mobile = mobile;
    }

    public abstract double getDiscount(double amount);
    public abstract double getDeliveryFee();

    public String getName() {
        return name;
    }

    // NEW FEATURES
    public void addOrder(Order order) {
        orderHistory.add(order);
    }

    public void addPayment(Payment payment) {
        paymentHistory.add(payment);
    }

    public void viewOrderHistory() {
        System.out.println("\n--- Order History ---");
        for (Order order : orderHistory) {
            order.displayOrder();
        }
    }

    public void viewPaymentHistory() {
        System.out.println("\n--- Payment History ---");
        for (Payment payment : paymentHistory) {
            payment.displayPayment();
            System.out.println();
        }
    }
}
