package com.mobiquity.parser;

import com.mobiquity.exception.APIException;
import com.mobiquity.model.Container;
import com.mobiquity.model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    private static final String INPUT_PATH = "src/test/resources/example_input";

    private Parser parser;

    @BeforeEach
    public void setUp() {
        parser = new Parser();
    }

    @Test
    public void testParse() throws APIException {
        parser.parse(INPUT_PATH);
        List<Container> containers = parser.getContainers();
        List<Item> items = containers.get(0).getItems();
        Item item = items.get(2);

        assertEquals(3, item.getIndex());
        assertEquals(78.48, item.getWeight());
        assertEquals(3, item.getCost());
    }
}