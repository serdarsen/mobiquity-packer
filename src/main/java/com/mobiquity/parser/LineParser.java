package com.mobiquity.parser;

import com.mobiquity.exception.APIException;
import com.mobiquity.exception.Messages;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Parses the line string.
 *
 * @author Serdar ŞEN
 */
public class LineParser {
    private String line;
    private Double capacity;
    private List<String> itemTexts;

    private final String LINE_PARTS_SEPARATOR = ":";
    private final String ITEM_SEPARATOR = " ";

    private final Pattern COLON_PATTERN = Pattern.compile("[:]");
    private final Pattern DECIMAL_PATTERN = Pattern.compile("^\\d*\\.?\\d*$");

    private final Double MAX_CAPACITY = 100.0;

    public LineParser() {
    }

    /**
     * Parse the line string
     * Create the capacity and the item texts.
     * @param line the raw line string to parse
     * @throws APIException an exception occurred during parsing
     */
    public void parse(String line) throws APIException {
        this.line = line;

        checkNull();
        checkColon();

        String[] lineParts = splitLine();
        String capacityText = lineParts[0].trim();
        String itemsText = lineParts[1].trim();

        checkCapacityText(capacityText);
        createCapacity(capacityText);
        checkMaxCapacity();

        checkItemTexts(itemsText);
        createItemTexts(itemsText);
    }

    private void checkNull() throws APIException {
        if (line == null) {
            throw new APIException(Messages.lineIsInvalid);
        }
    }

    private void checkColon() throws APIException {
        Matcher matcher = COLON_PATTERN.matcher(line);
        if (matcher.results().count() != 1) {
            throw new APIException(Messages.numberOfColonIsInvalid + line);
        }
    }

    private String[] splitLine() {
        return line.split(LINE_PARTS_SEPARATOR);
    }

    private void checkCapacityText(String capacityText) throws APIException {
        Matcher matcher = DECIMAL_PATTERN.matcher(capacityText);
        if (!matcher.matches()) {
            throw new APIException(Messages.capacityTextIsInvalid + capacityText);
        }
    }

    private void createCapacity(String linePart) {
        this.capacity = Double.valueOf(linePart);
    }

    private void checkMaxCapacity() throws APIException {
        if (capacity > MAX_CAPACITY) {
            throw new APIException(Messages.maxCapacityExceeded + capacity);
        }
    }

    private void checkItemTexts(String itemsText) throws APIException {
        if (itemsText.isEmpty() || itemsText.isBlank()) {
            throw new APIException(Messages.itemTextsAreInvalid + itemsText);
        }
    }

    private void createItemTexts(String itemsText) {
        this.itemTexts = Stream.of(itemsText.split(ITEM_SEPARATOR))
                .collect(Collectors.toList());
    }

    /**
     * Return the package capacity
     */
    public Double getCapacity() {
        return capacity;
    }

    /**
     * Return the item texts
     */
    public List<String> getItemTexts() {
        return itemTexts;
    }
}
