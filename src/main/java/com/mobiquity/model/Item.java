package com.mobiquity.model;

/**
 * @author Serdar ŞEN
 */
public class Item implements Comparable<Item>{
    private Integer index;
    private Double weight;
    private Double cost;

    public Item(Integer index, Double weight, Double cost) {
        this.index = index;
        this.weight = weight;
        this.cost = cost;
    }

    public Integer getIndex() {
        return index;
    }

    public Double getWeight() {
        return weight;
    }

    public Double getCost() {
        return cost;
    }

    @Override
    public int compareTo(Item item) {
        return this.weight.compareTo(item.getWeight());
    }

    @Override
    public String toString() {
        return "Item{" +
                "index=" + index +
                ", weight=" + weight +
                ", cost=" + cost +
                '}';
    }
}
