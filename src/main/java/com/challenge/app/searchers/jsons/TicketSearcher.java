package com.challenge.app.searchers.jsons;

import com.challenge.app.dto.Searchable;
import com.challenge.app.dto.Ticket;
import com.jayway.jsonpath.DocumentContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TicketSearcher - This class is used to search values in tickets json.
 */
public class TicketSearcher implements JsonSearcher {

    private final DocumentContext jsonContext;
    private Map<String, String> ticketIdVsNameMap;
    private Map<Integer, List<String>> organizationIdVsTicketIdsMap;
    private Map<Integer, List<String>> userIdVsSubmittedTicketIdsMap;
    private Map<Integer, List<String>> userIdVsAssignedTicketIdsMap;

    public TicketSearcher(DocumentContext jsonContext) {
        this.jsonContext = jsonContext;
        createTicketMaps();
    }

    /**
     * This method search and returns results for ticket.
     *
     * @param key   search key
     * @param value search value
     * @return list of Ticket objects
     */
    @Override
    public List<Searchable> search(String key, String value) {
        String query = getSearchQuery(key, value);
        return getSearchResults(query);
    }

    /**
     * This method returns ticket subject by id.
     *
     * @param id id
     * @return name
     */
    @Override
    public String searchNameById(Object id) {
        String ticketId = (String) id;
        return ticketIdVsNameMap.getOrDefault(ticketId, "");
    }

    /**
     * This method returns list of ticket Ids by organization id.
     *
     * @param organizationId organizationId
     * @return list of ticket Ids
     */
    public List<String> searchTicketIdsByOrganizationId(int organizationId) {
        return organizationIdVsTicketIdsMap.getOrDefault(organizationId, new ArrayList<>());
    }

    /**
     * This method returns list of submitted ticket ids.
     *
     * @param userId userId
     * @return list of submitted ticket ids.
     */
    public List<String> searchSubmittedTicketIdsByUserId(int userId) {
        return userIdVsSubmittedTicketIdsMap.getOrDefault(userId, new ArrayList<>());
    }

    /**
     * This method returns list of assigned ticket ids.
     *
     * @param userId userId
     * @return list of assigned ticket ids.
     */
    public List<String> searchAssignedTicketIdsByUserId(int userId) {
        return userIdVsAssignedTicketIdsMap.getOrDefault(userId, new ArrayList<>());
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
            case Ticket.SUBMITTER_ID:
            case Ticket.ASSIGNEE_ID:
            case Ticket.ORGANIZATION_ID:
            case Ticket.HAS_INCIDENTS:
                return QUERY_FOR_NON_STRINGS.replace(KEY_TAG, key).replace(VALUE_TAG, value);
            case Ticket.TAGS_KEY:
                return QUERY_FOR_STRING_ARRAY.replace(KEY_TAG, key).replace(VALUE_TAG, value);
            default:
                return QUERY_FOR_STRINGS.replace(KEY_TAG, key).replace(VALUE_TAG, value);
        }
    }

    /**
     * This method returns searched results.
     *
     * @param query search query
     * @return list of Ticket objects
     */
    private List<Searchable> getSearchResults(String query) {
        List<Searchable> tickets = new ArrayList<>();
        List<Map<String, Object>> ticketMaps = jsonContext.read(query);
        ticketMaps.forEach(m -> {
            Ticket ticket = new Ticket(m);
            tickets.add(ticket);
        });
        return tickets;
    }

    /**
     * This method creates the map of tickets.
     *
     * @return ticket map
     */
    private Map<String, String> createTicketMaps() {
        ticketIdVsNameMap = new HashMap<>();
        organizationIdVsTicketIdsMap = new HashMap<>();
        userIdVsAssignedTicketIdsMap = new HashMap<>();
        userIdVsSubmittedTicketIdsMap = new HashMap<>();
        List<Map<String, Object>> tickets = jsonContext.read("$");
        tickets.forEach(t -> {
            Ticket ticket = new Ticket(t);
            ticketIdVsNameMap.put(ticket.getId(), ticket.getSubject());
            addTicketForOrganization(ticket);
            addTicketForAssignee(ticket);
            addTicketForSubmitter(ticket);
        });
        return ticketIdVsNameMap;
    }

    /**
     * This method adds userId to the organizationIdVsUserIdsMap.
     *
     * @param ticket ticket
     */
    private void addTicketForOrganization(Ticket ticket) {
        List<String> ticketIdsList = new ArrayList<>();
        if (organizationIdVsTicketIdsMap.containsKey(ticket.getOrganizationId())) {
            ticketIdsList = organizationIdVsTicketIdsMap.get(ticket.getOrganizationId());
        }
        ticketIdsList.add(ticket.getId());
        organizationIdVsTicketIdsMap.put(ticket.getOrganizationId(), ticketIdsList);
    }

    /**
     * This method adds userId to the userIdVsAssignedTicketIdsMap.
     *
     * @param ticket ticket
     */
    private void addTicketForAssignee(Ticket ticket) {
        List<String> ticketIdsList = new ArrayList<>();
        if (userIdVsAssignedTicketIdsMap.containsKey(ticket.getAssigneeId())) {
            ticketIdsList = userIdVsAssignedTicketIdsMap.get(ticket.getAssigneeId());
        }
        ticketIdsList.add(ticket.getId());
        userIdVsAssignedTicketIdsMap.put(ticket.getAssigneeId(), ticketIdsList);
    }

    /**
     * This method adds userId to the userIdVsSubmittedTicketIdsMap.
     *
     * @param ticket ticket
     */
    private void addTicketForSubmitter(Ticket ticket) {
        List<String> ticketIdsList = new ArrayList<>();
        if (userIdVsSubmittedTicketIdsMap.containsKey(ticket.getSubmitterId())) {
            ticketIdsList = userIdVsSubmittedTicketIdsMap.get(ticket.getSubmitterId());
        }
        ticketIdsList.add(ticket.getId());
        userIdVsSubmittedTicketIdsMap.put(ticket.getSubmitterId(), ticketIdsList);
    }


}
