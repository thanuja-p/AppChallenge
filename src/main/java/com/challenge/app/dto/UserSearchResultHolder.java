package com.challenge.app.dto;

import java.util.List;
import java.util.Map;

/**
 * UserSearchResultHolder - This class is used to hold User search results
 */
public class UserSearchResultHolder implements Printable {

    private static final String ASSIGNEE_TICKET_SUBJECTS_KEY = "assignee_ticket_subjects";
    private static final String SUBMITTED_TICKET_SUBJECTS_KEY = "submitted_ticket_subjects";
    private static final String ORGANIZATION_NAME = "organization_name";

    private User user;
    private List<String> assigneeTicketSubjects;
    private List<String> submittedTicketSubjects;
    private String organizationName;

    public UserSearchResultHolder(User user, List<String> assignedTicketSubjects,
                                  List<String> submittedTicketSubjects, String organizationName) {
        this.user = user;
        this.assigneeTicketSubjects = assignedTicketSubjects;
        this.submittedTicketSubjects = submittedTicketSubjects;
        this.organizationName = organizationName;
    }

    /**
     * This method returns map for user search results.
     *
     * @return map for user search results
     */
    @Override
    public Map<String, String> getPrintMap() {
        Map<String, String> printMap = user.getPrintMap();
        printMap.put(ASSIGNEE_TICKET_SUBJECTS_KEY, String.valueOf(assigneeTicketSubjects));
        printMap.put(SUBMITTED_TICKET_SUBJECTS_KEY, String.valueOf(submittedTicketSubjects));
        printMap.put(ORGANIZATION_NAME, organizationName);
        return printMap;
    }

}
