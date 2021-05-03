package com.mobiquity.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {

    @Test
    public void testComparisonByWeightEquals() {
        Item itemOne = new Item(1, 40.0, 5.0);
        Item itemTwo = new Item(2, 40.0, 4.0);

        int result = itemOne.compareTo(itemTwo);

        assertTrue(result == 0);
    }

    @Test
    public void testComparisonByWeightGreaterThan() {
        Item itemOne = new Item(1, 15.38, 5.0);
        Item itemTwo = new Item(2, 5.2, 4.0);

        int result = itemOne.compareTo(itemTwo);

        assertTrue(result >= 1);
    }

    @Test
    public void testComparisonByWeightLessThan() {
        Item itemOne = new Item(1, 15.38, 5.0);
        Item itemTwo = new Item(2, 5.2, 4.0);

        int result = itemTwo.compareTo(itemOne);

        assertTrue(result >= -1);
    }

    @Test
    public void testCollectionsSort() {
        Item itemOne = new Item(1, 15.38, 5.0);
        Item itemTwo = new Item(2, 5.2, 4.0);

        List<Item> items = new ArrayList<>();
        items.add(itemOne);
        items.add(itemTwo);

        Collections.sort(items);

        assertTrue(items.get(0).getIndex() == itemTwo.getIndex());
    }
}