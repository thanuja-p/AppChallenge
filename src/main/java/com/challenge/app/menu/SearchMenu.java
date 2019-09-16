package com.challenge.app.menu;

import com.challenge.app.dto.*;
import com.challenge.app.exceptions.SearchException;
import com.challenge.app.searchers.SearchFactory;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * SearchMenu - This class holds search menu.
 */
public class SearchMenu {

    private static final String SEARCHING_TEXT = "Searching _SEARCH_TYPE_ for _KEY_ with a value of _VALUE_\n";
    private static final String NO_RESULTS_FOUND = "No results found";
    private static final String SEARCH_TYPE = "_SEARCH_TYPE_";
    private static final String SEARCH_KEY = "_KEY_";
    private static final String SEARCH_VALUE = "_VALUE_";
    private static SearchMenu searchMenu = new SearchMenu();
    private SearchPrinter printer;
    private SearchFactory searchFactory;

    final static Logger logger = Logger.getLogger(SearchMenu.class);

    private SearchMenu() {
        try {
            String path = new File(".").getCanonicalPath() + "/JsonStore/";
            String userJsonFilePath = path + "users.json";
            String ticketJsonFilePath = path + "tickets.json";
            String organizationFilePath = path + "organizations.json";
            searchFactory = new SearchFactory(userJsonFilePath,
                    ticketJsonFilePath, organizationFilePath);
            printer = new SearchPrinter();
        } catch (IOException e) {
            throw new SearchException("Invalid json file paths.");
        }
    }

    public static synchronized SearchMenu getSearchMenu() {
        return searchMenu;
    }

    /**
     * This method prints main menu options and gets inputs from the user.
     */
    public void printMainMenu() {
        printMainMenuOptions();
        Scanner sc = new Scanner(System.in);
        String mainOption = sc.nextLine();
        if (mainOption.equals("quit")) {
            System.exit(0);
        } else {
            switch (mainOption.trim()) {
                case "1":
                    printSearchMenu();
                    break;
                case "2":
                    printSearchFields();
                    break;
                default:
                    System.out.println("Invalid option!");
                    printMainMenu();
            }
            System.out.println("\n\n\n\n");
            printMainMenu();
        }
    }

    /**
     * This method prints main menu options.
     */
    private void printMainMenuOptions() {
        System.out.println("Select search options:");
        System.out.println("* Press 1 to search");
        System.out.println("* Press 2 to view a list of searchable fields");
        System.out.println("* Type 'quit' to exit");
        System.out.println("\n: ");
    }


    /**
     * This method prints search menu and gets search arguments from the user.
     */
    private void printSearchMenu() {
        System.out.println("Select 1) Users or 2) Tickets or 3) Organizations");
        try {
            Scanner searchOptionScanner = new Scanner(System.in);
            int searchOption = searchOptionScanner.nextInt();
            System.out.println("Enter search term: ");
            Scanner searchTermScanner = new Scanner(System.in);
            String searchTerm = searchTermScanner.nextLine();
            System.out.println("Enter search value: ");
            Scanner searchValueScanner = new Scanner(System.in);
            String searchValue = searchValueScanner.nextLine();
            SearchType searchType = getSearchType(searchOption);
            printSearchResults(searchType, searchTerm, searchValue);
        } catch (InputMismatchException | SearchException e) {
            System.out.println("Invalid Option!");
            logger.error("Invalid Option!", e);
            printSearchMenu();
        }
    }

    /**
     * This method returns relevant SearchType according to the search option.
     *
     * @param searchOption searchOption
     * @return SearchType
     */
    private SearchType getSearchType(int searchOption) {
        SearchType searchType;
        switch (searchOption) {
            case 1:
                searchType = SearchType.USER;
                break;
            case 2:
                searchType = SearchType.TICKET;
                break;
            case 3:
                searchType = SearchType.ORHANIZATION;
                break;
            default:
                throw new SearchException("Invalid search type.");
        }
        return searchType;
    }

    /**
     * This method searches and prints searched results.
     *
     * @param searchType  searchType
     * @param searchTerm  searchTerm
     * @param searchValue searchValue
     */
    private void printSearchResults(SearchType searchType, String searchTerm, String searchValue) {
        List<Printable> results = searchFactory.getSearcher(searchType).search(searchTerm.trim(), searchValue.trim());
        String searchText = SEARCHING_TEXT.replace(SEARCH_TYPE, searchType.getName())
                .replace(SEARCH_KEY, searchTerm).replace(SEARCH_VALUE, searchValue);
        System.out.println(searchText);
        if (results.isEmpty()) {
            System.out.println(NO_RESULTS_FOUND);
        } else {
            printer.printSearchResults(results);
        }
    }

    /**
     * This method prints list of search fields.
     */
    private void printSearchFields() {
        Map<String, List<String>> searchFieldsMap = new LinkedHashMap<>();
        searchFieldsMap.put("users", new User().getSearchableFields());
        searchFieldsMap.put("tickets", new Ticket().getSearchableFields());
        searchFieldsMap.put("organizations", new Organization().getSearchableFields());
        printer.printSearchFields(searchFieldsMap);
    }
}
