package com.challenge.app.dto;

import java.util.List;

/**
 * Searchable - Interface for searchable items.
 */
public interface Searchable {

    /**
     * This method returns list of searchable fields for searchable items.
     *
     * @return list of searchable fields.
     */
    List<String> getSearchableFields();
}
