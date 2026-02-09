package com.infy;

import java.time.LocalDateTime;

public class Payment {

    private double amount;
    private String paymentMode;
    private LocalDateTime paymentDate;

    public Payment(double amount, String paymentMode) {
        this.amount = amount;
        this.paymentMode = paymentMode;
        this.paymentDate = LocalDateTime.now();
    }

    public void displayPayment() {
        System.out.println("Amount Paid : ₹" + amount);
        System.out.println("Payment Mode: " + paymentMode);
        System.out.println("Date        : " + paymentDate);
    }
}
