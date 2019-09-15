package com.challenge.app.searchers.mediators;

import com.challenge.app.dto.Organization;
import com.challenge.app.dto.OrganizationSearchResultHolder;
import com.challenge.app.dto.Printable;
import com.challenge.app.dto.Searchable;
import com.challenge.app.searchers.jsons.OrganizationSearcher;
import com.challenge.app.searchers.jsons.TicketSearcher;
import com.challenge.app.searchers.jsons.UserSearcher;

import java.util.ArrayList;
import java.util.List;

/**
 * OrganizationSearchMediator - This class directly calls json searchers to construct organization's search result.
 */
public class OrganizationSearchMediator implements SearchMediator {

    private final UserSearcher userSearcher;
    private final TicketSearcher ticketSearcher;
    private final OrganizationSearcher organizationSearcher;

    public OrganizationSearchMediator(UserSearcher userSearcher,
                                      TicketSearcher ticketSearcher,
                                      OrganizationSearcher organizationSearcher) {
        this.userSearcher = userSearcher;
        this.ticketSearcher = ticketSearcher;
        this.organizationSearcher = organizationSearcher;
    }

    /**
     * This method searches and returns organization results.
     *
     * @param key   search key
     * @param value search value
     * @return list of organization
     */
    @Override
    public List<Printable> search(String key, String value) {
        List<Searchable> organizations = organizationSearcher.search(key, value);
        List<Printable> organizationsResults = new ArrayList<>();
        organizations.forEach(o -> {
            Organization organization = (Organization) o;
            List<String> ticketSubjects = getTicketSubjectsList(organization.getId());
            List<String> userNames = getUserNamesList(organization.getId());
            OrganizationSearchResultHolder organizationSearchResultHolder = new OrganizationSearchResultHolder(
                    organization, ticketSubjects, userNames
            );
            organizationsResults.add(organizationSearchResultHolder);
        });
        return organizationsResults;
    }

    /**
     * This method returns list of ticket subjects by organizationId.
     *
     * @param organizationId organizationId
     * @return list of ticket subjects
     */
    private List<String> getTicketSubjectsList(int organizationId) {
        List<String> ticketSubjects = new ArrayList<>();
        List<String> ticketsIds = ticketSearcher.searchTicketIdsByOrganizationId(organizationId);
        ticketsIds.forEach(tid ->
                ticketSubjects.add(ticketSearcher.searchNameById(tid))
        );
        return ticketSubjects;
    }

    /**
     * This method returns list of user names by organizationId.
     *
     * @param organizationId organizationId
     * @return list of user names
     */
    private List<String> getUserNamesList(int organizationId) {
        List<String> userNames = new ArrayList<>();
        List<Integer> userIds = userSearcher.searchUserIdsByOrganizationId(organizationId);
        userIds.forEach(uid ->
                userNames.add(userSearcher.searchNameById(uid))
        );
        return userNames;
    }
}
