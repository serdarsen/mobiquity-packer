package com.mobiquity.resolver;

import com.mobiquity.model.Item;
import com.mobiquity.model.Container;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Resolver calculates to pack the {@link Item}s with the highest cost and
 * the least weight within capacity using bottom up dynamic programming.
 *
 * @author Serdar ŞEN
 */
public class Resolver {
    private int capacity;
    private int[] indexes;
    private int[] weights;
    private int[] costs;
    private String output;
    private final int COEFFICIENT = 100;
    private final String PLACEHOLDER = "-";
    public static final int MAX_ITEM_SIZE = 15;

    public Resolver() {
    }

    /**
     * Extract the package capacity and the {@link Item}s from {@link Container}.
     * Calculates to pack the items with the highest cost and
     * the least weight within capacity and max item size.
     * @param container the current container that contains the capacity and items.
     * @return a string of the selected item indexes separated by comma.
     */
    public String resolve(Container container){
        createCapacity(container.getPackageCapacity());
        createIndexesWeightsAndCosts(container.getItems());
        doBottomUpCalculation();

        return output;
    }

    private void createCapacity(Double capacity){
        this.capacity = (int) (capacity * COEFFICIENT);
    }

    private void createIndexesWeightsAndCosts(List<Item> items) {
        Collections.sort(items);
        indexes = new int[items.size() + 1];
        weights = new int[items.size() + 1];
        costs = new int[items.size() + 1];

        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            indexes[i + 1] = item.getIndex();
            weights[i + 1] = (int) (item.getWeight() * COEFFICIENT);
            costs[i + 1] = (int) (item.getCost() * COEFFICIENT);
        }
    }

    private int doBottomUpCalculation() {
        // base checks
        if (capacity <= 0 || costs.length == 0 || weights.length != costs.length) {
            return 0;
        }

        int n = costs.length;
        int[][] dp = new int[n][capacity + 1];

        // populate the capacity=0 columns, with '0' capacity we have '0' cost
        for (int i = 0; i < n; i++) {
            dp[i][0] = 0;
        }

        // if we have only one weight, we will take it if it is not more than the capacity
        for (int c = 0; c <= capacity; c++) {
            if (weights[0] <= c) {
                dp[0][c] = costs[0];
            }
        }

        // process all sub-arrays for all the capacities
        for (int i = 1; i < n; i++) {
            for (int c = 1; c <= capacity; c++) {
                int cost1 = 0, cost2 = 0;
                // include the item, if it is not more than the capacity
                if (weights[i] <= c) {
                    cost1 = costs[i] + dp[i - 1][c - weights[i]];
                }
                // exclude the item
                cost2 = dp[i - 1][c];
                // take maximum
                dp[i][c] = Math.max(cost1, cost2);
            }
        }

        createOutput(dp, weights, costs, capacity);

        // maximum cost will be at the bottom-right corner.
        return dp[n - 1][capacity];
    }

    private void createOutput(int dp[][], int[] weights, int[] costs, int capacity) {
        List<Integer> selected = new ArrayList<>();
        int totalProfit = dp[weights.length - 1][capacity];
        for (int i = weights.length - 1; i > 0; i--) {
            if (totalProfit != dp[i - 1][capacity]) {
                selected.add(indexes[i]);
                capacity -= weights[i];
                totalProfit -= costs[i];
            }
        }

        String output = selected.stream()
                                .sorted((Integer::compareTo))
                                .limit(MAX_ITEM_SIZE)
                                .map(String::valueOf)
                                .collect(Collectors.joining(","));

        if(output.isEmpty() || output.isBlank()){
            output = PLACEHOLDER;
        }

        this.output = output;
    }
}
