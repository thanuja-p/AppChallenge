package com.challenge.app.searchers.mediators;

import com.challenge.app.dto.Printable;
import com.challenge.app.dto.User;
import com.challenge.app.dto.UserSearchResultHolder;
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
 * This class tests the UserSearchMediator class.
 */
public class UserSearchMediatorTest {

    private UserSearchMediator userSearchMediator;

    @Before
    public void setUp() throws Exception {
        UserSearcher userSearcher = mock(UserSearcher.class);
        TicketSearcher ticketSearcher = mock(TicketSearcher.class);
        when(userSearcher.search("key", "value")).thenReturn(Arrays.asList(new User()));
        OrganizationSearcher organizationSearcher = mock(OrganizationSearcher.class);
        userSearchMediator = new UserSearchMediator(userSearcher, ticketSearcher, organizationSearcher);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void Should_Return_SearchResults() {
        List<Printable> result = userSearchMediator.search("key", "value");
        Assert.assertEquals(1, result.size());
        Assert.assertTrue(result.get(0) instanceof UserSearchResultHolder);
    }

}