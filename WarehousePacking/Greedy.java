package WarehousePacking;

public class Greedy {

    public static void packProducts(Product[] products, int count, double capacity) {

        if (count == 0) {
            System.out.println("No products available!");
            return;
        }

        if (capacity <= 0 || !Double.isFinite(capacity)) {
            System.out.println("Invalid container capacity!");
            return;
        }

        double originalCapacity = capacity;

        // Create temporary array
        Product[] sorted = new Product[count];

        for (int i = 0; i < count; i++) {
            sorted[i] = products[i];
        }

        // Sort by value-to-weight ratio
        for (int i = 0; i < count - 1; i++) {

            for (int j = 0; j < count - i - 1; j++) {

                double ratio1 = sorted[j].value / sorted[j].weight;

                double ratio2 = sorted[j + 1].value / sorted[j + 1].weight;

                if (ratio1 < ratio2) {

                    Product temp = sorted[j];

                    sorted[j] = sorted[j + 1];

                    sorted[j + 1] = temp;
                }
            }
        }

        double totalValue = 0;
        double totalWeight = 0;

        System.out.println("\n GREEDY PACKING RESULTS");

        System.out.printf("%-15s %-12s %-12s%n",
                "Product", "Weight", "Value");

        // Select products
        for (int i = 0; i < count; i++) {

            if (capacity <= 0) {
                break;
            }

            if (sorted[i].weight <= capacity) {

                System.out.printf("%-15s %-12.2f %-12.2f%n",
                        sorted[i].name,
                        sorted[i].weight,
                        sorted[i].value);

                capacity -= sorted[i].weight;

                totalWeight += sorted[i].weight;

                totalValue += sorted[i].value;

            } else {

                double fraction = capacity / sorted[i].weight;

                System.out.printf("%-15s %-12.2f %-12.2f (Partial)%n",
                        sorted[i].name,
                        capacity,
                        sorted[i].value * fraction);

                totalWeight += capacity;

                totalValue += sorted[i].value * fraction;

                capacity = 0;
            }
        }

        System.out.println("---");

        System.out.printf("Total Weight: %.2f kg%n", totalWeight);

        System.out.printf("Total Value: %.2f%n", totalValue);

        System.out.printf("Remaining Capacity: %.2f kg%n", capacity);

        System.out.printf("Packing Efficiency: %.2f%%%n",
                (totalWeight / originalCapacity) * 100);

        System.out.println("==");
    }
}