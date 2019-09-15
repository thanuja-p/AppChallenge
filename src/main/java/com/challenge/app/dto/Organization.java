package com.challenge.app.dto;

import java.util.*;

/**
 * Organization - This class is used to hold Organization Json
 */
public class Organization implements Searchable, Printable {

    public static final String ID_KEY = "_id";
    public static final String URL_KEY = "url";
    public static final String EXTERNAL_ID = "external_id";
    public static final String NAME_KEY = "name";
    public static final String DOMAIN_NAMES = "domain_names";
    public static final String CREATED_AT = "created_at";
    public static final String DETAILS_KEY = "details";
    public static final String SHARED_TICKETS = "shared_tickets";
    public static final String TAGS_KEY = "tags";

    private int id;
    private String url;
    private String externalId;
    private String name;
    private List<String> domainNames;
    private String createdAt;
    private String details;
    private boolean sharedTickets;
    private List<String> tags;

    public Organization() {
    }

    public Organization(Map<String, Object> organizationMap) {
        this.id = (int) organizationMap.get(ID_KEY);
        this.url = (String) organizationMap.getOrDefault(URL_KEY, "");
        this.externalId = (String) organizationMap.getOrDefault(EXTERNAL_ID, "");
        this.name = (String) organizationMap.getOrDefault(NAME_KEY, "");
        this.domainNames = (List<String>) organizationMap.getOrDefault(DOMAIN_NAMES, new ArrayList<>());
        this.createdAt = (String) organizationMap.getOrDefault(CREATED_AT, "");
        this.details = (String) organizationMap.getOrDefault(DETAILS_KEY, "");
        this.sharedTickets = (boolean) organizationMap.getOrDefault(SHARED_TICKETS, false);
        this.tags = (List<String>) organizationMap.getOrDefault(TAGS_KEY, new ArrayList<>());
    }

    /**
     * This method returns list of searchable fields for organization.
     *
     * @return list of searchable fields
     */
    @Override
    public List<String> getSearchableFields() {
        return Arrays.asList(ID_KEY, URL_KEY, EXTERNAL_ID, NAME_KEY, DOMAIN_NAMES,
                CREATED_AT, DETAILS_KEY, SHARED_TICKETS, TAGS_KEY);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getDomainNames() {
        return domainNames;
    }

    public void setDomainNames(List<String> domainNames) {
        this.domainNames = domainNames;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public boolean isSharedTickets() {
        return sharedTickets;
    }

    public void setSharedTickets(boolean sharedTickets) {
        this.sharedTickets = sharedTickets;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    /**
     * This method returns map for organization.
     *
     * @return map for organization
     */
    @Override
    public Map<String, String> getPrintMap() {
        Map<String, String> printMap = new LinkedHashMap<>();
        printMap.put(ID_KEY, String.valueOf(id));
        printMap.put(URL_KEY, url);
        printMap.put(EXTERNAL_ID, externalId);
        printMap.put(NAME_KEY, name);
        printMap.put(DOMAIN_NAMES, String.valueOf(domainNames));
        printMap.put(CREATED_AT, createdAt);
        printMap.put(DETAILS_KEY, details);
        printMap.put(SHARED_TICKETS, String.valueOf(sharedTickets));
        printMap.put(TAGS_KEY, String.valueOf(tags));
        return printMap;
    }

}
