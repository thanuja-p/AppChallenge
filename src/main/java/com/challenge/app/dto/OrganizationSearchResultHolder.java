package com.challenge.app.dto;

import java.util.List;
import java.util.Map;

/**
 * OrganizationSearchResultHolder - This class is used to hold Organization search results
 */
public class OrganizationSearchResultHolder implements Printable {

    private static final String TICKET_SUBJECTS = "ticket_subjects";
    private static final String USER_NAMES = "user_names";

    private Organization organization;
    private List<String> ticketSubjects;
    private List<String> userNames;

    public OrganizationSearchResultHolder(Organization organization,
                                          List<String> ticketSubjects, List<String> userNames) {
        this.organization = organization;
        this.ticketSubjects = ticketSubjects;
        this.userNames = userNames;
    }

    /**
     * This method returns map for organization search results.
     *
     * @return map for organization search results
     */
    @Override
    public Map<String, String> getPrintMap() {
        Map<String, String> printMap = organization.getPrintMap();
        printMap.put(TICKET_SUBJECTS, String.valueOf(ticketSubjects));
        printMap.put(USER_NAMES, String.valueOf(userNames));
        return printMap;
    }

}
