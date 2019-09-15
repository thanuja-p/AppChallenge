package com.challenge.app.searchers.jsons;

import com.challenge.app.dto.Searchable;

import java.util.List;

/**
 * JsonSearcher - This interface is used to search values in json contexts.
 */
public interface JsonSearcher {

    String QUERY_FOR_STRINGS = "$[?(@._KEY_ == '_VALUE_')]";
    String QUERY_FOR_NON_STRINGS = "$[?(@._KEY_ == _VALUE_)]";
    String QUERY_FOR_STRING_ARRAY = "$[?('_VALUE_' in @['_KEY_'])]";
    String KEY_TAG = "_KEY_";
    String VALUE_TAG = "_VALUE_";

    /**
     * This method searches and returns results.
     *
     * @param key   search key
     * @param value search value
     * @return list of Ticket objects
     */
    List<Searchable> search(String key, String value);

    /**
     * This method returns name by id.
     *
     * @param id id
     * @return name
     */
    String searchNameById(Object id);
}
