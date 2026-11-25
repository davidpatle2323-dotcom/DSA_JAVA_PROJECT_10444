package WarehousePacking;

public class Sorting {

    // Sort products by weight using Bubble Sort
    public static void sortByWeight(Product[] products, int count) {

        if (count == 0) {
            System.out.println("No products available!");
            return;
        }

        for (int i = 0; i < count - 1; i++) {

            for (int j = 0; j < count - i - 1; j++) {

                if (products[j].weight > products[j + 1].weight) {

                    // Swap products
                    Product temp = products[j];

                    products[j] = products[j + 1];

                    products[j + 1] = temp;
                }
            }
        }

        System.out.println("Products sorted by weight successfully!");
    }
}