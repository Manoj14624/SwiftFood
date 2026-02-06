package com.infy;

public class PremiumCustomer extends Customer {

    public PremiumCustomer(int customerId, String customerName, String address, String mobileNumber) {
        super(customerId, customerName, address, mobileNumber);
    }

    @Override
    public double getDiscount(double amount) {
        return amount * 0.20;
    }

    @Override
    public double getDeliveryFee() {
        return 0;
    }
}
