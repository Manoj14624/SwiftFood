package com.infy;

public abstract class Customer {

    protected int customerId;
    protected String customerName;
    protected String address;
    protected String mobileNumber;

    public Customer(int customerId, String customerName, String address, String mobileNumber) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.mobileNumber = mobileNumber;
    }

    public abstract double getDiscount(double amount);
    public abstract double getDeliveryFee();

    public String getCustomerName() {
        return customerName;
    }
}
