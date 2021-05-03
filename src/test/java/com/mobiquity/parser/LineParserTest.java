package com.mobiquity.parser;

import com.mobiquity.exception.APIException;
import com.mobiquity.exception.Messages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LineParserTest {
    private LineParser lineParser;

    @BeforeEach
    public void setUp() {
        lineParser = new LineParser();
    }

    @Test
    public void throwsWhenLineIsInvalid() {
        String line = null;
        String expected = Messages.lineIsInvalid;

        Throwable exception = assertThrows(APIException.class,
                                           () -> lineParser.parse(line));
        assertEquals(expected, exception.getMessage());
    }

    @Test
    public void throwsWhenNumberOfColonIsInvalid() {
        String line = "8 (1,15.3,€34)";
        String expected = Messages.numberOfColonIsInvalid + line;

        Throwable exception = assertThrows(APIException.class,
                                           () -> lineParser.parse(line));
        assertEquals(expected, exception.getMessage());
    }

    @Test
    public void throwsWhenCapacityTextIsInvalid() {
        String line = "invalid-capacity : (1,15.3,€34)";
        String expectedText = "invalid-capacity";
        String expected = Messages.capacityTextIsInvalid + expectedText;

        Throwable exception = assertThrows(APIException.class,
                                           () -> lineParser.parse(line));
        assertEquals(expected, exception.getMessage());
    }

    @Test
    public void throwsWhenMaxCapacityExeeded() {
        String line = "120 : (1,15.3,€34)";
        String expectedText = "120.0";
        String expected = Messages.maxCapacityExceeded + expectedText;

        Throwable exception = assertThrows(APIException.class,
                                           () -> lineParser.parse(line));
        assertEquals(expected, exception.getMessage());
    }
}