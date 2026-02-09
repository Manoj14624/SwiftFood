package com.infy;

public class DeliveryPartner {

    private String name;
    private String mobile;
    private String vehicleNumber;

    public DeliveryPartner(String name, String mobile, String vehicleNumber) {
        this.name = name;
        this.mobile = mobile;
        this.vehicleNumber = vehicleNumber;
    }

    public void displayDetails() {
        System.out.println("Delivery Partner: " + name);
        System.out.println("Mobile Number  : " + mobile);
        System.out.println("Vehicle Number : " + vehicleNumber);
    }
}
