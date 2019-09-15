package com.challenge.app.dto;

import java.util.Map;

/**
 * Printable - Interface for printable items.
 */
public interface Printable {

    /**
     * This method returns map for printable item.
     *
     * @return map for printable item
     */
    Map<String, String> getPrintMap();
}
