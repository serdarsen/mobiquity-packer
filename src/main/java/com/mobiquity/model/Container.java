package com.mobiquity.model;

import java.util.List;

/**
 * Represents a temporary package of all {@link Item}s.
 *
 * @author Serdar ŞEN
 */
public class Container {
    private Double packageCapacity;
    private List<Item> items;

    public Double getPackageCapacity() {
        return packageCapacity;
    }

    public void setPackageCapacity(Double packageCapacity) {
        this.packageCapacity = packageCapacity;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
