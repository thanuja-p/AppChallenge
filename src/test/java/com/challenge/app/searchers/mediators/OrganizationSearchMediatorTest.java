package com.challenge.app.searchers.mediators;

import com.challenge.app.dto.Organization;
import com.challenge.app.dto.OrganizationSearchResultHolder;
import com.challenge.app.dto.Printable;
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
 * This class tests the OrganizationSearchMediator class.
 */
public class OrganizationSearchMediatorTest {

    private OrganizationSearchMediator organizationSearchMediator;

    @Before
    public void setUp() throws Exception {
        UserSearcher userSearcher = mock(UserSearcher.class);
        TicketSearcher ticketSearcher = mock(TicketSearcher.class);
        OrganizationSearcher organizationSearcher = mock(OrganizationSearcher.class);
        when(organizationSearcher.search("key", "value")).thenReturn(Arrays.asList(getOrganization()));
        when(ticketSearcher.searchTicketIdsByOrganizationId(12)).thenReturn(Arrays.asList("1"));
        when(userSearcher.searchUserIdsByOrganizationId(12)).thenReturn(Arrays.asList(2));
        organizationSearchMediator = new OrganizationSearchMediator(userSearcher, ticketSearcher, organizationSearcher);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void Should_Return_SearchResults() {
        List<Printable> result = organizationSearchMediator.search("key", "value");
        Assert.assertEquals(1, result.size());
        Assert.assertTrue(result.get(0) instanceof OrganizationSearchResultHolder);
    }

    private Organization getOrganization(){
        Organization organization = new Organization();
        organization.setId(12);
        return organization;
    }

}