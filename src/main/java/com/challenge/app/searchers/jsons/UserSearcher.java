package com.challenge.app.searchers.jsons;

import com.challenge.app.dto.Searchable;
import com.challenge.app.dto.User;
import com.jayway.jsonpath.DocumentContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * UserSearcher - This class is used to search values in users json.
 */
public class UserSearcher implements JsonSearcher {

    private final DocumentContext jsonContext;
    private Map<Integer, String> userIdVsNameMap;
    private Map<Integer, List<Integer>> organizationIdVsUserIdsMap;

    public UserSearcher(DocumentContext jsonContext) {
        this.jsonContext = jsonContext;
        createUserMaps();
    }

    /**
     * This method search and returns results for user.
     *
     * @param key   search key
     * @param value search value
     * @return list of User objects
     */
    @Override
    public List<Searchable> search(String key, String value) {
        String query = getSearchQuery(key, value);
        return getSearchResults(query);

    }

    /**
     * This method returns user name by id.
     *
     * @param id id
     * @return name
     */
    @Override
    public String searchNameById(Object id) {
        int userId = (int) id;
        return userIdVsNameMap.getOrDefault(userId, "");
    }

    /**
     * This method returns list of user Ids by organization id.
     *
     * @param organizationId organizationId
     * @return list of user Ids
     */
    public List<Integer> searchUserIdsByOrganizationId(int organizationId) {
        return organizationIdVsUserIdsMap.getOrDefault(organizationId, new ArrayList<>());
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
            case User.ID_KEY:
            case User.ACTIVE_KEY:
            case User.SHARED_KEY:
            case User.VERIFIED_KEY:
            case User.ORGANIZATION_ID:
            case User.SUSPENDED_KEY:
                return QUERY_FOR_NON_STRINGS.replace(KEY_TAG, key).replace(VALUE_TAG, value);
            case User.TAGS_KEY:
                return QUERY_FOR_STRING_ARRAY.replace(KEY_TAG, key).replace(VALUE_TAG, value);
            default:
                return QUERY_FOR_STRINGS.replace(KEY_TAG, key).replace(VALUE_TAG, value);
        }
    }

    /**
     * This method returns searched results.
     *
     * @param query search query
     * @return list of User objects
     */
    private List<Searchable> getSearchResults(String query) {
        List<Searchable> users = new ArrayList<>();
        List<Map<String, Object>> userMaps = jsonContext.read(query);
        userMaps.forEach(m -> {
            User user = new User(m);
            users.add(user);
        });
        return users;
    }

    /**
     * This method creates the map of users.
     */
    private void createUserMaps() {
        userIdVsNameMap = new HashMap<>();
        organizationIdVsUserIdsMap = new HashMap<>();
        List<Map<String, Object>> users = jsonContext.read("$");
        users.forEach(u -> {
            User user = new User(u);
            userIdVsNameMap.put(user.getId(), user.getName());
            addUserForOrganization(user);
        });
    }

    /**
     * This method adds userId to the organizationIdVsUserIdsMap.
     *
     * @param user user
     */
    private void addUserForOrganization(User user) {
        List<Integer> userIdsList = new ArrayList<>();
        if (organizationIdVsUserIdsMap.containsKey(user.getOrganizationId())) {
            userIdsList = organizationIdVsUserIdsMap.get(user.getOrganizationId());
        }
        userIdsList.add(user.getId());
        organizationIdVsUserIdsMap.put(user.getOrganizationId(), userIdsList);
    }

}
