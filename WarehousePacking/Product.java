package WarehousePacking;

public class Product {

    int id;
    String name;
    double weight;
    double value;

    // Constructor
    public Product(int id, String name, double weight, double value) {

        this.id = id;
        this.name = name;
        this.weight = weight;
        this.value = value;
    }

    // Display product details
    public void display() {

        System.out.printf("%-10d %-15s %-12.2f %-12.2f%n",
                id, name, weight, value);
    }
}