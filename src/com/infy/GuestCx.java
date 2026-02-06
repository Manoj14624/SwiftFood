package com.infy;

public class GuestCx extends Customer {

    public GuestCx(int customerId, String customerName, String address, String mobileNumber) {
        super(customerId, customerName, address, mobileNumber);
    }

    @Override
    public double getDiscount(double amount) {
        return 0;
    }

    @Override
    public double getDeliveryFee() {
        return 50;
    }
}
