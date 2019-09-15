package com.challenge.app.searchers.mediators;

import com.challenge.app.dto.Printable;
import com.challenge.app.dto.Ticket;
import com.challenge.app.dto.TicketSearchResultHolder;
import com.challenge.app.searchers.jsons.OrganizationSearcher;
import com.challenge.app.searchers.jsons.TicketSearcher;
import com.challenge.app.searchers.jsons.UserSearcher;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This class tests the TicketSearchMediator class.
 */
public class TicketSearchMediatorTest {

    private TicketSearchMediator ticketSearchMediator;

    @Before
    public void setUp() throws Exception {
        UserSearcher userSearcher = mock(UserSearcher.class);
        TicketSearcher ticketSearcher = mock(TicketSearcher.class);
        when(ticketSearcher.search("key", "value")).thenReturn(Arrays.asList(new Ticket()));
        OrganizationSearcher organizationSearcher = mock(OrganizationSearcher.class);
        ticketSearchMediator = new TicketSearchMediator(userSearcher, ticketSearcher, organizationSearcher);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void Should_Return_SearchResults() {
        List<Printable> result = ticketSearchMediator.search("key", "value");
        Assert.assertEquals(1, result.size());
        Assert.assertTrue(result.get(0) instanceof TicketSearchResultHolder);
    }

}