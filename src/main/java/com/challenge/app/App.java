package com.challenge.app;

import com.challenge.app.exceptions.SearchException;
import com.challenge.app.menu.SearchMenu;

/**
 * App - Main
 */
public class App {


    /**
     * Main
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            SearchMenu.getSearchMenu().printMainMenu();
        } catch (SearchException e) {
            System.out.println("Sorry! System doesn't work due to internal errors.");
        }
    }

}
