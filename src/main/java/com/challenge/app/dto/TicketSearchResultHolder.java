package com.challenge.app.dto;

import java.util.Map;

/**
 * TicketSearchResultHolder - This class is used to hold ticket search results
 */
public class TicketSearchResultHolder implements Printable {

    private static final String ASSIGNEE_NAME = "assignee_name";
    private static final String SUBMITTER_NAME = "submitter_name";
    private static final String ORGANIZATION_NAME = "organization_name";

    private Ticket ticket;
    private String assigneeName;
    private String submitterName;
    private String organizationName;

    public TicketSearchResultHolder(Ticket ticket, String assigneeName,
                                    String submitterName, String organizationName) {
        this.ticket = ticket;
        this.assigneeName = assigneeName;
        this.submitterName = submitterName;
        this.organizationName = organizationName;
    }

    /**
     * This method returns map for ticket search results.
     *
     * @return map for ticket search results
     */
    @Override
    public Map<String, String> getPrintMap() {
        Map<String, String> printMap = ticket.getPrintMap();
        printMap.put(ASSIGNEE_NAME, assigneeName);
        printMap.put(SUBMITTER_NAME, submitterName);
        printMap.put(ORGANIZATION_NAME, organizationName);
        return printMap;
    }

}
