package com.mobiquity.parser;

import com.mobiquity.exception.APIException;
import com.mobiquity.exception.Messages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FileParserTest {
    private static final String DOES_NOT_EXIST_INPUT_PATH = "src/test/resources/does_not_exist_input";
    private static final String INPUT_PATH = "src/test/resources/example_input";

    private FileParser fileParser;

    @BeforeEach
    public void setUp() {
        fileParser = new FileParser();
    }

    @Test
    public void testWhenBasicInput() throws APIException {
        int expected = 5;

        assertEquals(expected, fileParser.lines(INPUT_PATH).count());
    }

    @Test
    public void throwsWhenFileDoestNotExist() {
        String expected = Messages.fileIsInvalid;

        Throwable exception = assertThrows(APIException.class,
                                          () -> fileParser.lines(DOES_NOT_EXIST_INPUT_PATH));
        assertEquals(expected, exception.getMessage());
    }
}