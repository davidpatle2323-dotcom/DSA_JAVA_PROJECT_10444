package WarehousePacking;

import java.util.Scanner;

public class Main {

    static Product[] products = new Product[100];

    static int count = 0;

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("   WAREHOUSE PACKING OPTIMIZER");
            System.out.println("1. Add Product");
            System.out.println("2. Display Products");
            System.out.println("3. Exit");
            System.out.println("4. Sort Products by Weight");
            System.out.println("5. Greedy Packing");
            System.out.println("6. Knapsack Optimization");

            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {

                // Add Product
                case 1:

                    addProduct();

                    break;

                // Display Products
                case 2:

                    displayProducts();

                    break;

                // Exit
                case 3:

                    System.out.println(
                            "Thank you for using Warehouse Packing Optimizer!"
                    );

                    break;

                // Sorting
                case 4:

                    Sorting.sortByWeight(products, count);

                    displayProducts();

                    break;

                // Greedy Algorithm
                case 5:

                    if (count == 0) {

                        System.out.println("No products available!");

                        break;
                    }

                    System.out.print("Enter Container Capacity: ");

                    double capacity = sc.nextDouble();

                    if (capacity <= 0 || !Double.isFinite(capacity)) {

                        System.out.println("Invalid container capacity!");

                        break;
                    }

                    Greedy.packProducts(products, count, capacity);

                    break;

                // Knapsack Algorithm
                case 6:

                    if (count == 0) {

                        System.out.println("No products available!");

                        break;
                    }

                    System.out.print("Enter Container Capacity: ");

                    double knapsackCapacity = sc.nextDouble();

                    if (knapsackCapacity <= 0 ||
                            !Double.isFinite(knapsackCapacity)) {

                        System.out.println("Invalid container capacity!");

                        break;
                    }

                    Knapsack.optimize(
                            products, count, knapsackCapacity
                    );

                    break;

                default:

                    System.out.println("Invalid choice! Try again.");
            }

        } while (choice != 3);
    }

    // Add Product
    static void addProduct() {

        if (count >= products.length) {

            System.out.println("Product storage is full!");

            return;
        }

        System.out.print("Enter Product ID: ");

        int id = sc.nextInt();

        sc.nextLine();

        // Check duplicate ID
        for (int i = 0; i < count; i++) {

            if (products[i].id == id) {

                System.out.println("Product ID already exists!");

                return;
            }
        }

        System.out.print("Enter Product Name: ");

        String name = sc.nextLine().trim();

        if (name.isEmpty()) {

            System.out.println("Product name cannot be empty!");

            return;
        }

        System.out.print("Enter Product Weight: ");

        double weight = sc.nextDouble();

        System.out.print("Enter Product Value: ");

        double value = sc.nextDouble();

        if (!Double.isFinite(weight) ||
                !Double.isFinite(value) ||
                weight <= 0 ||
                value < 0) {

            System.out.println("Invalid weight or value!");

            return;
        }

        products[count] = new Product(id, name, weight, value);

        count++;

        System.out.println("Product added successfully!");
    }

    // Display Products
    static void displayProducts() {

        if (count == 0) {

            System.out.println("No products available!");

            return;
        }

        System.out.println("\n---");

        System.out.printf("%-10s %-15s %-12s %-12s%n",
                "ID", "Name", "Weight", "Value");

        System.out.println("---");

        for (int i = 0; i < count; i++) {

            products[i].display();
        }

        System.out.println("---");
    }
}