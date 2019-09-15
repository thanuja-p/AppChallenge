package com.challenge.app.menu;

import com.challenge.app.dto.Printable;

import java.util.List;
import java.util.Map;

/**
 * Printer - This class is used to print.
 */
public class SearchPrinter {

    private static final String MIDDLE_SPACE = ":\t";
    private static final String ITEM_SEPARATOR = "-----------------------------------------";
    private static final String SEARCH_FIELDS_ITEM_TITLE = "Search _KEY_ with";
    private static final String SEARCH_ITEM_KEY = "_KEY_";
    private static final int PAD_LENGTH = 25;

    /**
     * This method prints search results of the printable items.
     *
     * @param printableItems list of printable items
     */
    public void printSearchResults(List<Printable> printableItems) {
        printableItems.forEach(p -> {
            Map<String, String> printMap = p.getPrintMap();
            printMap.forEach((key, val) ->
                    System.out.println(addRightPad(key, PAD_LENGTH) + MIDDLE_SPACE + val)
            );
            System.out.println(ITEM_SEPARATOR);
        });

    }

    /**
     * This method prints search fields for a single item.
     *
     * @param searchFieldsMap search item name vs fields map
     */
    public void printSearchFields(Map<String, List<String>> searchFieldsMap) {
        searchFieldsMap.forEach((key, val) -> {
            System.out.println(ITEM_SEPARATOR);
            String title = SEARCH_FIELDS_ITEM_TITLE.replace(SEARCH_ITEM_KEY, key);
            System.out.println(title);
            val.forEach(System.out::println);
        });

    }

    /**
     * This method adds left padding to the text.
     *
     * @param text   text
     * @param length pad length
     * @return left padded text
     */
    private String addLeftPad(String text, int length) {
        return String.format("%" + length + "." + length + "s", text);
    }

    /**
     * This method adds right padding to the text.
     *
     * @param text   text
     * @param length pad length
     * @return right padded text
     */
    private String addRightPad(String text, int length) {
        return String.format("%-" + length + "." + length + "s", text);
    }
}
