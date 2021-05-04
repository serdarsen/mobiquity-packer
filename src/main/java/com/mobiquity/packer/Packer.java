package com.mobiquity.packer;

import com.mobiquity.exception.APIException;
import com.mobiquity.model.Container;
import com.mobiquity.parser.Parser;
import com.mobiquity.resolver.Resolver;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Serdar ŞEN
 */
public class Packer {

    private Packer() {
    }

    /**
     * Read given file
     * Parse using parser
     * Resolve using resolver
     * @param filePath
     * @return a string of the selected item indexes of each package separated by comma and line
     * @throws APIException an exception occurred during packing
     */
    public static String pack(String filePath) throws APIException {
        Parser parser = new Parser();
        Resolver resolver = new Resolver();

        parser.parse(filePath);
        List<Container> containers = parser.getContainers();

        return containers.stream().map(resolver::resolve).collect(Collectors.joining("\n"));
    }
}
