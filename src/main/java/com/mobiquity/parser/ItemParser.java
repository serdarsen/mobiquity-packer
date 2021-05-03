package com.mobiquity.parser;

import com.mobiquity.exception.APIException;
import com.mobiquity.exception.Messages;
import com.mobiquity.model.Item;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parses the item text.
 *
 * @author Serdar ŞEN
 */
public class ItemParser {

    private String itemText;
    private Item item;

    private final String SEPARATOR = ",";
    private final String CURRENCY = "€";
    private final String PARENTHESES_REGEX = "[()]";
    private final Pattern PARENTHESES_PATTERN = Pattern.compile("\\([^()]+\\)");
    private final Pattern SEPARATOR_PATTERN = Pattern.compile("[,]");
    private final Pattern DECIMAL_PATTERN = Pattern.compile("^\\d*\\.?\\d*$");
    private final Pattern NUMBER_PATTERN = Pattern.compile("[0-9]");
    private final Pattern CURRENCY_PATTERN = Pattern.compile("^\\€(0|[1-9][0-9]{0,2})(,\\d{3})*(\\.\\d{1,2})?$");

    private final Double MAX_WEIGHT = 100.0;
    private final Double MAX_COST = 100.0;

    public ItemParser() {
    }

    /**
     * Parse the item text
     * Create an {@link Item}
     * @param itemText the raw item text to parse
     * @throws APIException an exception occurred during parsing
     */
    public void parse(String itemText) throws APIException {
        this.itemText = itemText;

        checkParentheses();
        checkSeperator();

        extractParentheses();
        parseItemText();
    }

    private void checkParentheses() throws APIException {
        Matcher matcher = PARENTHESES_PATTERN.matcher(itemText);
        if (!matcher.matches()) {
            throw new APIException(Messages.parenthesesAreInvalid + itemText);
        }
    }

    private void checkSeperator() throws APIException {
        Matcher matcher = SEPARATOR_PATTERN.matcher(itemText);
        if (matcher.results().count() != 2) {
            throw new APIException(Messages.seperatorIsInvalid + itemText);
        }
    }


    private void extractParentheses() {
        itemText = itemText.replaceAll(PARENTHESES_REGEX, "");
    }

    private void parseItemText() throws APIException {
        String[] itemPropsText = itemText.split(SEPARATOR);
        String indexText = itemPropsText[0].trim();
        String weightText = itemPropsText[1].trim();
        String costText = itemPropsText[2].trim();

        checkIndexText(indexText);
        checkWeightText(weightText);
        checkCostText(costText);

        Integer index = createIndex(indexText);

        Double weight = createWeight(weightText);
        checkWeight(weight);

        costText = extractCurrency(costText);

        Double cost = createCost(costText);
        checkCost(cost);

        createItem(index, weight, cost);
    }

    private void checkIndexText(String indexText) throws APIException {
        Matcher matcher = NUMBER_PATTERN.matcher(indexText);
        if (!matcher.matches()) {
            throw new APIException(Messages.indexTextIsInvalid + itemText);
        }
    }

    private void checkWeightText(String weightText) throws APIException {
        Matcher matcher = DECIMAL_PATTERN.matcher(weightText);
        if (!matcher.matches()) {
            throw new APIException(Messages.weightTextIsInvalid + itemText);
        }
    }

    private void checkWeight(Double weight) throws APIException {
        if (weight > MAX_WEIGHT) {
            throw new APIException(Messages.maxWeightExceeded + itemText);
        }
    }

    private void checkCostText(String costText) throws APIException {
        Matcher matcher = CURRENCY_PATTERN.matcher(costText);
        if (!matcher.matches()) {
            throw new APIException(Messages.costTextIsInvalid + itemText);
        }
    }

    private void checkCost(Double cost) throws APIException {
        if (cost > MAX_WEIGHT) {
            throw new APIException(Messages.maxCostExceeded + itemText);
        }
    }

    private Integer createIndex(String indexText) {
        return Integer.valueOf(indexText);
    }

    private Double createWeight(String indexWeight) {
        return Double.valueOf(indexWeight);
    }

    private String extractCurrency(String costText) {
        return costText.replace(CURRENCY, "");
    }

    private Double createCost(String indexCost) {
        return Double.valueOf(indexCost);
    }

    private void createItem(Integer index, Double weight, Double cost) {
        this.item = new Item(index, weight, cost);
    }

    /**
     * Return the {@link Item}
     */
    public Item getItem() {
        return item;
    }
}
