package com.infy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SwiftFood {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to SwiftFood 🍽️");

        System.out.print("Enter Customer ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Customer Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Address: ");
        String address = sc.nextLine();

        System.out.print("Enter Mobile Number: ");
        String mobile = sc.nextLine();

        System.out.println("\nSelect Customer Type:");
        System.out.println("1. Guest");
        System.out.println("2. Member");
        System.out.println("3. Premium");
        System.out.print("Enter choice: ");
        int type = sc.nextInt();

        Customer customer;

        if (type == 1) {
            customer = new GuestCx(id, name, address, mobile);
        } else if (type == 2) {
            customer = new MemberCustomer(id, name, address, mobile);
        } else {
            customer = new PremiumCustomer(id, name, address, mobile);
        }

        FoodItem[] menu = {
                new FoodItem("Paneer Biryani", "Veg", "Indian", 0, 180),
                new FoodItem("Chicken Biryani", "Non-Veg", "Indian", 0, 220),
                new FoodItem("Veg Burger", "Veg", "American", 0, 120),
                new FoodItem("Chicken Burger", "Non-Veg", "American", 0, 150)
        };

        List<FoodItem> orderList = new ArrayList<>();

        char more;

        do {
            System.out.println("\nAvailable Food Items:");
            for (int i = 0; i < menu.length; i++) {
                System.out.println(
                        (i + 1) + ". " +
                                menu[i].getFoodName() +
                                " | ₹" + menu[i].getCostPerUnit()
                );
            }

            System.out.print("Select food item number: ");
            int choice = sc.nextInt();

            System.out.print("Enter quantity: ");
            int qty = sc.nextInt();

            FoodItem selected = menu[choice - 1];

            orderList.add(
                    new FoodItem(
                            selected.getFoodName(),
                            selected.getFoodType(),
                            selected.getCuisine(),
                            qty,
                            selected.getCostPerUnit()
                    )
            );

            System.out.print("Do you want to add more items? (y/n): ");
            more = sc.next().charAt(0);

        } while (more == 'y' || more == 'Y');

        Bill bill = new Bill(customer, orderList);
        bill.generateBill();

        sc.close();
    }
}
