package com.mobiquity.packer;

import com.mobiquity.exception.APIException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PackerTest {
    private static final String INPUT_PATH = "src/test/resources/example_input";

    @Test
    public void testPack() throws APIException {
        String output = Packer.pack(INPUT_PATH);

        assertEquals("4\n-\n2,7\n8,9\n2,3", output);
    }
}