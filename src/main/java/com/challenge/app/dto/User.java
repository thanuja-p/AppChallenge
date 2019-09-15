package com.challenge.app.dto;

import java.util.*;

/**
 * User - This class is used to hold User Json
 */
public class User implements Searchable, Printable {

    public static final String ID_KEY = "_id";
    public static final String URL_KEY = "url";
    public static final String EXTERNAL_ID = "external_id";
    public static final String NAME_KEY = "name";
    public static final String ALIAS_KEY = "alias";
    public static final String CREATED_AT = "created_at";
    public static final String ACTIVE_KEY = "active";
    public static final String VERIFIED_KEY = "verified";
    public static final String SHARED_KEY = "shared";
    public static final String LOCALE_KEY = "locale";
    public static final String TIMEZONE_KEY = "timezone";
    public static final String LAST_LOGIN_AT = "last_login_at";
    public static final String EMAIL_KEY = "email";
    public static final String PHONE_KEY = "phone";
    public static final String SIGNATURE_KEY = "signature";
    public static final String ORGANIZATION_ID = "organization_id";
    public static final String TAGS_KEY = "tags";
    public static final String SUSPENDED_KEY = "suspended";
    public static final String ROLE_KEY = "role";

    private int id;
    private String url;
    private String externalId;
    private String name;
    private String alias;
    private String createdAt;
    private boolean active;
    private boolean verified;
    private boolean shared;
    private String locale;
    private String timezone;
    private String lastLoginAt;
    private String email;
    private String phone;
    private String signature;
    private int organizationId;
    private List<String> tags;
    private boolean suspended;
    private String role;

    public User() {
    }

    public User(Map<String, Object> userMap) {
        this.id = (int) userMap.get(ID_KEY);
        this.url = (String) userMap.getOrDefault(URL_KEY, "");
        this.externalId = (String) userMap.getOrDefault(EXTERNAL_ID, "");
        this.tags = (List<String>) userMap.getOrDefault(TAGS_KEY, new ArrayList<>());
        this.name = (String) userMap.getOrDefault(NAME_KEY, "");
        this.alias = (String) userMap.getOrDefault(ALIAS_KEY, "");
        this.createdAt = (String) userMap.getOrDefault(CREATED_AT, "");
        this.active = (boolean) userMap.getOrDefault(ACTIVE_KEY, false);
        this.verified = (boolean) userMap.getOrDefault(VERIFIED_KEY, false);
        this.shared = (boolean) userMap.getOrDefault(SHARED_KEY, false);
        this.locale = (String) userMap.getOrDefault(LOCALE_KEY, "");
        this.timezone = (String) userMap.getOrDefault(TIMEZONE_KEY, "");
        this.lastLoginAt = (String) userMap.getOrDefault(LAST_LOGIN_AT, "");
        this.lastLoginAt = (String) userMap.getOrDefault(LAST_LOGIN_AT, "");
        this.email = (String) userMap.getOrDefault(EMAIL_KEY, "");
        this.phone = (String) userMap.getOrDefault(PHONE_KEY, "");
        this.signature = (String) userMap.getOrDefault(SIGNATURE_KEY, "");
        this.organizationId = (int) userMap.getOrDefault(ORGANIZATION_ID, 0);
        this.suspended = (boolean) userMap.getOrDefault(SUSPENDED_KEY, false);
        this.role = (String) userMap.getOrDefault(ROLE_KEY, "");
    }

    /**
     * This method returns list of searchable fields for user.
     *
     * @return list of searchable fields for user
     */
    @Override
    public List<String> getSearchableFields() {
        return Arrays.asList(ID_KEY, URL_KEY, EXTERNAL_ID, NAME_KEY,
                ALIAS_KEY, CREATED_AT, ACTIVE_KEY, VERIFIED_KEY, SHARED_KEY,
                LOCALE_KEY, TIMEZONE_KEY, LAST_LOGIN_AT, EMAIL_KEY, PHONE_KEY,
                SIGNATURE_KEY, ORGANIZATION_ID, TAGS_KEY, SUSPENDED_KEY, ROLE_KEY);
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

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public boolean isShared() {
        return shared;
    }

    public void setShared(boolean shared) {
        this.shared = shared;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getLastLoginAt() {
        return lastLoginAt;
    }

    public void setLastLoginAt(String lastLoginAt) {
        this.lastLoginAt = lastLoginAt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public boolean isSuspended() {
        return suspended;
    }

    public void setSuspended(boolean suspended) {
        this.suspended = suspended;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    /**
     * This method returns map for user.
     *
     * @return map for user
     */
    @Override
    public Map<String, String> getPrintMap() {
        Map<String, String> printMap = new LinkedHashMap<>();
        printMap.put(ID_KEY, String.valueOf(id));
        printMap.put(URL_KEY, url);
        printMap.put(EXTERNAL_ID, externalId);
        printMap.put(NAME_KEY, name);
        printMap.put(ALIAS_KEY, alias);
        printMap.put(CREATED_AT, createdAt);
        printMap.put(ACTIVE_KEY, String.valueOf(active));
        printMap.put(VERIFIED_KEY, String.valueOf(verified));
        printMap.put(SHARED_KEY, String.valueOf(shared));
        printMap.put(LOCALE_KEY, locale);
        printMap.put(TIMEZONE_KEY, timezone);
        printMap.put(LAST_LOGIN_AT, lastLoginAt);
        printMap.put(EMAIL_KEY, email);
        printMap.put(PHONE_KEY, phone);
        printMap.put(SIGNATURE_KEY, signature);
        printMap.put(ORGANIZATION_ID, String.valueOf(organizationId));
        printMap.put(TAGS_KEY, String.valueOf(tags));
        printMap.put(SUSPENDED_KEY, String.valueOf(suspended));
        printMap.put(ROLE_KEY, getRole());
        return printMap;
    }
}
