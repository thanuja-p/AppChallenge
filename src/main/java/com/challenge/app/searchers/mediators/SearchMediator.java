package com.challenge.app.searchers.mediators;

import com.challenge.app.dto.Printable;

import java.util.List;

public interface SearchMediator {


    /**
     * This method searches and returns results.
     *
     * @param key   search key
     * @param value search value
     * @return list of search items
     */
    List<Printable> search(String key, String value);

}
