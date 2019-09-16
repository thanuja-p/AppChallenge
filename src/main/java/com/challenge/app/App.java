package com.challenge.app;

import com.challenge.app.exceptions.SearchException;
import com.challenge.app.menu.SearchMenu;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * App - Main
 */
public class App {

    final static Logger logger = Logger.getLogger(App.class);

    /**
     * Main
     *
     * @param args
     */
    public static void main(String[] args) {
        PropertyConfigurator.configure("log4j.properties");
        try {
            SearchMenu.getSearchMenu().printMainMenu();
        } catch (SearchException e) {
            logger.error("Error in search menu", e);
            System.out.println("Sorry! System doesn't work due to internal errors.");
        }
    }

}
