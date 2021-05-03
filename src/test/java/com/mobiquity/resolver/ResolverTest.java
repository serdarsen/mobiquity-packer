package com.mobiquity.resolver;

import com.mobiquity.model.Container;
import com.mobiquity.model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ResolverTest {
    private Resolver resolver;

    @BeforeEach
    public void setUp() {
       resolver = new Resolver();
    }

    @Test
    public void testResolve() {
        Container container = new Container();
        Double capacity = 16.0;
        List<Item> items = new ArrayList<>();
        Resolver resolver = new Resolver();

        items.add(new Item(1, 15.38, 5.0));
        items.add(new Item(2, 5.2, 4.0));
        items.add(new Item(3, 10.1, 2.0));

        container.setPackageCapacity(capacity);
        container.setItems(items);

        String output = resolver.resolve(container);

        assertEquals("2,3", output);
    }

    @Test
    public void testResolveWithSameCosts() {
        Container container = new Container();
        Double capacity = 56.0;
        List<Item> items = new ArrayList<>();
        Resolver resolver = new Resolver();

        items.add(new Item(1, 90.72, 13.0));
        items.add(new Item(2, 33.80, 40.0));
        items.add(new Item(3, 43.15, 10.0));
        items.add(new Item(4, 37.97, 16.0));
        items.add(new Item(5, 46.81, 36.0));
        items.add(new Item(6, 48.77, 79.0));
        items.add(new Item(7, 81.80, 45.0));
        items.add(new Item(8, 19.36, 79.0));
        items.add(new Item(9, 6.76, 64.0));

        container.setPackageCapacity(capacity);
        container.setItems(items);

        String output = resolver.resolve(container);

        assertEquals("8,9", output);
    }

    @Test
    public void testResolveWithMoreThanMaximumItemSize() {
        Container container = new Container();
        Double weight = 5.0;
        Double capacity = weight * (Resolver.MAX_ITEM_SIZE + 1);
        List<Item> items = new ArrayList<>();

        for (int i = 0; i < Resolver.MAX_ITEM_SIZE + 1; i++) {
            items.add(new Item(i + 1, weight, 13.0));
        }

        container.setPackageCapacity(capacity);
        container.setItems(items);

        String output = resolver.resolve(container);

        assertEquals("1,2,3,4,5,6,7,8,9,10,11,12,13,14,15", output);
    }
}
