package WarehousePacking;

public class Knapsack {

    public static void optimize(Product[] products, int count, double capacity) {

        if (count == 0) {
            System.out.println("No products available!");
            return;
        }

        if (capacity <= 0 || !Double.isFinite(capacity)) {
            System.out.println("Invalid container capacity!");
            return;
        }

        // Convert weight into units of 0.01 kg
        int capacityUnits = (int) Math.round(capacity * 100);

        if (capacityUnits <= 0) {
            System.out.println("Container capacity is too small!");
            return;
        }

        int[] weights = new int[count];

        for (int i = 0; i < count; i++) {

            weights[i] = (int) Math.round(products[i].weight * 100);

            if (weights[i] <= 0) {
                System.out.println("Invalid product weight!");
                return;
            }
        }

        // Create Dynamic Programming table
        double[][] dp = new double[count + 1][capacityUnits + 1];

        // Knapsack calculation
        for (int i = 1; i <= count; i++) {

            for (int w = 0; w <= capacityUnits; w++) {

                if (weights[i - 1] <= w) {

                    dp[i][w] = Math.max(
                            dp[i - 1][w],
                            products[i - 1].value +
                                    dp[i - 1][w - weights[i - 1]]
                    );

                } else {

                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        // Display results
        System.out.println("\n KNAPSACK RESULTS");

        double totalWeight = 0;
        double totalValue = 0;

        int w = capacityUnits;

        System.out.printf("%-15s %-12s %-12s%n",
                "Product", "Weight", "Value");

        // Find selected products
        for (int i = count; i > 0; i--) {

            if (dp[i][w] > dp[i - 1][w] + 0.000001) {

                Product p = products[i - 1];

                System.out.printf("%-15s %-12.2f %-12.2f%n",
                        p.name, p.weight, p.value);

                totalWeight += p.weight;

                totalValue += p.value;

                w -= weights[i - 1];
            }
        }

        double remainingCapacity = capacity - totalWeight;

        System.out.println("---");

        System.out.printf("Total Weight: %.2f kg%n", totalWeight);

        System.out.printf("Total Value: %.2f%n", totalValue);

        System.out.printf("Remaining Capacity: %.2f kg%n",
                remainingCapacity);

        System.out.printf("Packing Efficiency: %.2f%%%n",
                (totalWeight / capacity) * 100);

        System.out.println("==");
    }
}