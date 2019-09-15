package com.challenge.app.searchers.jsons;

import com.challenge.app.dto.Organization;
import com.challenge.app.dto.Searchable;
import com.jayway.jsonpath.DocumentContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * OrganizationSearcher - This class is used to search values in organizations json.
 */
public class OrganizationSearcher implements JsonSearcher {

    private final DocumentContext jsonContext;
    private Map<Integer, String> organizationIdVsNameMap;

    public OrganizationSearcher(DocumentContext jsonContext) {
        this.jsonContext = jsonContext;
        createOrganizationMap();
    }

    /**
     * This method search and returns results for organization.
     *
     * @param key   search key
     * @param value search value
     * @return list of Organization objects
     */
    @Override
    public List<Searchable> search(String key, String value) {
        String query = getSearchQuery(key, value);
        return getSearchResults(query);
    }

    /**
     * This method returns organization name by id.
     *
     * @param id id
     * @return name
     */
    @Override
    public String searchNameById(Object id) {
        int organizationId = (int) id;
        return organizationIdVsNameMap.getOrDefault(organizationId, "");
    }

    /**
     * This method creates and returns search query.
     *
     * @param key   search key
     * @param value search value
     * @return search query
     */
    private String getSearchQuery(String key, String value) {
        switch (key) {
            case Organization.ID_KEY:
            case Organization.SHARED_TICKETS:
                return QUERY_FOR_NON_STRINGS.replace(KEY_TAG, key).replace(VALUE_TAG, value);
            case Organization.TAGS_KEY:
            case Organization.DOMAIN_NAMES:
                return QUERY_FOR_STRING_ARRAY.replace(KEY_TAG, key).replace(VALUE_TAG, value);
            default:
                return QUERY_FOR_STRINGS.replace(KEY_TAG, key).replace(VALUE_TAG, value);
        }
    }

    /**
     * This method returns searched results.
     *
     * @param query search query
     * @return list of Organization objects
     */
    private List<Searchable> getSearchResults(String query) {
        List<Searchable> organizations = new ArrayList<>();
        List<Map<String, Object>> organizationMaps = jsonContext.read(query);
        organizationMaps.forEach(m -> {
            Organization organization = new Organization(m);
            organizations.add(organization);
        });
        return organizations;
    }

    /**
     * This method creates the map of organization, id vs name.
     */
    private void createOrganizationMap() {
        organizationIdVsNameMap = new HashMap<>();
        List<Map<String, Object>> organizations = jsonContext.read("$");
        organizations.forEach(o -> {
            Organization organization = new Organization(o);
            organizationIdVsNameMap.put(organization.getId(), organization.getName());
        });
    }

}
