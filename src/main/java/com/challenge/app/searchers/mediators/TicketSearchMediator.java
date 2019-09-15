package com.challenge.app.searchers.mediators;

import com.challenge.app.dto.Printable;
import com.challenge.app.dto.Searchable;
import com.challenge.app.dto.Ticket;
import com.challenge.app.dto.TicketSearchResultHolder;
import com.challenge.app.searchers.jsons.OrganizationSearcher;
import com.challenge.app.searchers.jsons.TicketSearcher;
import com.challenge.app.searchers.jsons.UserSearcher;

import java.util.ArrayList;
import java.util.List;

/**
 * TicketSearchMediator - This class directly calls json searchers to construct ticket's search result.
 */
public class TicketSearchMediator implements SearchMediator {

    private final UserSearcher userSearcher;
    private final TicketSearcher ticketSearcher;
    private final OrganizationSearcher organizationSearcher;

    public TicketSearchMediator(UserSearcher userSearcher,
                                TicketSearcher ticketSearcher,
                                OrganizationSearcher organizationSearcher) {
        this.userSearcher = userSearcher;
        this.ticketSearcher = ticketSearcher;
        this.organizationSearcher = organizationSearcher;
    }

    /**
     * This method searches and returns ticket results.
     *
     * @param key   search key
     * @param value search value
     * @return list of tickets
     */
    @Override
    public List<Printable> search(String key, String value) {
        List<Searchable> tickets = ticketSearcher.search(key, value);
        List<Printable> ticketsResults = new ArrayList<>();
        tickets.forEach(t -> {
            Ticket ticket = (Ticket) t;
            String assigneeName = userSearcher.searchNameById(ticket.getAssigneeId());
            String submitterName = userSearcher.searchNameById(ticket.getSubmitterId());
            String organizationName = organizationSearcher.searchNameById(ticket.getOrganizationId());
            TicketSearchResultHolder ticketSearchResultHolder = new TicketSearchResultHolder(
                    ticket, assigneeName, submitterName, organizationName
            );
            ticketsResults.add(ticketSearchResultHolder);
        });
        return ticketsResults;
    }
}
