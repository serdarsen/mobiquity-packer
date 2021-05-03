package com.mobiquity.parser;

import com.mobiquity.exception.APIException;
import com.mobiquity.exception.Messages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ItemParserTest {
    private ItemParser itemParser;

    @BeforeEach
    public void setUp() {
        itemParser = new ItemParser();
    }

    @Test
    public void throwsWhenParenthesesAreInvalid() {
        String itemText = "(1,15.38,€5";
        String expected = Messages.parenthesesAreInvalid + itemText;

        Throwable exception = assertThrows(APIException.class,
                                           () -> itemParser.parse(itemText));
        assertEquals(expected, exception.getMessage());
    }

    @Test
    public void throwsWhenSeperatorIsInvalid() {
        String itemText = "(1,15.38€5)";
        String expected = Messages.seperatorIsInvalid + itemText;

        Throwable exception = assertThrows(APIException.class,
                                           () -> itemParser.parse(itemText));
        assertEquals(expected, exception.getMessage());
    }

    @Test
    public void throwsWhenIndexIsInvalid() {
        String itemText = "(a,15.38,€5)";
        String expectedItemText = "a,15.38,€5";
        String expected = Messages.indexTextIsInvalid + expectedItemText;

        Throwable exception = assertThrows(APIException.class,
                                           () -> itemParser.parse(itemText));
        assertEquals(expected, exception.getMessage());
    }

    @Test
    public void throwsWhenWeightIsInvalid() {
        String itemText = "(1,15.K8,€5)";
        String expectedItemText = "1,15.K8,€5";
        String expected = Messages.weightTextIsInvalid + expectedItemText;

        Throwable exception = assertThrows(APIException.class,
                                           () -> itemParser.parse(itemText));
        assertEquals(expected, exception.getMessage());
    }

    @Test
    public void throwsWhenCostIsInvalid() {
        String itemText = "(1,15.38,$5KKK)";
        String expectedItemText = "1,15.38,$5KKK";
        String expected = Messages.costTextIsInvalid + expectedItemText;

        Throwable exception = assertThrows(APIException.class,
                                           () -> itemParser.parse(itemText));
        assertEquals(expected, exception.getMessage());
    }

    @Test
    public void throwsWhenMaxWeightExceeded() {
        String itemText = "(1,150.38,€5)";
        String expectedItemText = "1,150.38,€5";
        String expected = Messages.maxWeightExceeded + expectedItemText;

        Throwable exception = assertThrows(APIException.class,
                                           () -> itemParser.parse(itemText));
        assertEquals(expected, exception.getMessage());
    }

    @Test
    public void throwsWhenMaxCostExceeded() {
        String itemText = "(1,15.38,€150)";
        String expectedItemText = "1,15.38,€150";
        String expected = Messages.maxCostExceeded + expectedItemText;

        Throwable exception = assertThrows(APIException.class,
                                           () -> itemParser.parse(itemText));
        assertEquals(expected, exception.getMessage());
    }
}