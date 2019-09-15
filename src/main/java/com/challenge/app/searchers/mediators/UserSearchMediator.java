package com.challenge.app.searchers.mediators;

import com.challenge.app.dto.Printable;
import com.challenge.app.dto.Searchable;
import com.challenge.app.dto.User;
import com.challenge.app.dto.UserSearchResultHolder;
import com.challenge.app.searchers.jsons.OrganizationSearcher;
import com.challenge.app.searchers.jsons.TicketSearcher;
import com.challenge.app.searchers.jsons.UserSearcher;

import java.util.ArrayList;
import java.util.List;

/**
 * UserSearchMediator - This class directly calls json searchers to construct user's search result.
 */
public class UserSearchMediator implements SearchMediator {

    private final UserSearcher userSearcher;
    private final TicketSearcher ticketSearcher;
    private final OrganizationSearcher organizationSearcher;

    public UserSearchMediator(UserSearcher userSearcher,
                              TicketSearcher ticketSearcher,
                              OrganizationSearcher organizationSearcher) {
        this.userSearcher = userSearcher;
        this.ticketSearcher = ticketSearcher;
        this.organizationSearcher = organizationSearcher;
    }

    /**
     * This method searches and returns user results.
     *
     * @param key   search key
     * @param value search value
     * @return list of users
     */
    @Override
    public List<Printable> search(String key, String value) {
        List<Searchable> users = userSearcher.search(key, value);
        List<Printable> usersResults = new ArrayList<>();
        users.forEach(u -> {
            User user = (User) u;
            List<String> assignedTicketIds = ticketSearcher.searchAssignedTicketIdsByUserId(user.getId());
            List<String> submittedTicketIds = ticketSearcher.searchSubmittedTicketIdsByUserId(user.getId());
            List<String> assignedTicketSubjects = getTicketSubjects(assignedTicketIds);
            List<String> submittedTicketSubjects = getTicketSubjects(submittedTicketIds);
            String organizationName = organizationSearcher.searchNameById(user.getOrganizationId());
            UserSearchResultHolder userSearchResultHolder = new UserSearchResultHolder(
                    user, assignedTicketSubjects, submittedTicketSubjects, organizationName);
            usersResults.add(userSearchResultHolder);
        });
        return usersResults;
    }

    /**
     * This method returns list of ticket's subjects.
     *
     * @param ticketIdList list of ticket ids.
     * @return list of ticket's subjects
     */
    private List<String> getTicketSubjects(List<String> ticketIdList) {
        List<String> ticketSubjects = new ArrayList<>();
        ticketIdList.forEach(tid -> ticketSubjects.add(ticketSearcher.searchNameById(tid)));
        return ticketSubjects;
    }
}
