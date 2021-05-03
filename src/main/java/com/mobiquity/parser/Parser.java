package com.mobiquity.parser;

import com.mobiquity.exception.APIException;
import com.mobiquity.model.Container;
import com.mobiquity.model.Item;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Reads file, parses strings and creates the containers
 * using the file, line, and item parsers
 *
 * @author Serdar ŞEN
 */
public class Parser {
    private FileParser fileParser;
    private LineParser lineParser;
    private ItemParser itemParser;
    private List<Container> containers;

    public Parser() {
        fileParser = new FileParser();
        lineParser = new LineParser();
        itemParser = new ItemParser();
    }

    /**
     * Read given file
     * Parse the line string and the item texts
     * Create the list of {@link Container}
     * @param filePath the file path to parse
     * @throws APIException an exception occurred during parsing
     */
    public void parse(String filePath) throws APIException {
        Stream<String> lines = fileParser.lines(filePath);
        containers = lines.map(this::createContainer).collect(Collectors.toList());
    }

    private Container createContainer(String line) {
        try {
            lineParser.parse(line);
        } catch (APIException e) {
            e.printStackTrace();
        }

        List<String> itemTexts = lineParser.getItemTexts();

        List<Item> items = itemTexts.stream().map(this::createItem).collect(Collectors.toList());

        Container container = new Container();
        container.setPackageCapacity(lineParser.getCapacity());
        container.setItems(items);

        return container;
    }

    private Item createItem(String itemText) {
        try {
            itemParser.parse(itemText);
        } catch (APIException e) {
            e.printStackTrace();
        }

        return itemParser.getItem();
    }

    /**
     *  Return the list of {@link Container}
     */
    public List<Container> getContainers() {
        return containers;
    }
}
