package com.infy;

public class MemberCustomer extends Customer {

    public MemberCustomer(int customerId, String customerName, String address, String mobileNumber) {
        super(customerId, customerName, address, mobileNumber);
    }

    @Override
    public double getDiscount(double amount) {
        return amount * 0.10;
    }

    @Override
    public double getDeliveryFee() {
        return 0;
    }
}
