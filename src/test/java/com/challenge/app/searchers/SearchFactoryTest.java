package com.challenge.app.searchers;

import com.challenge.app.dto.SearchType;
import com.challenge.app.exceptions.SearchException;
import com.challenge.app.searchers.mediators.OrganizationSearchMediator;
import com.challenge.app.searchers.mediators.SearchMediator;
import com.challenge.app.searchers.mediators.TicketSearchMediator;
import com.challenge.app.searchers.mediators.UserSearchMediator;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.io.File;

/**
 * This class tests the SearchFactory class
 */
public class SearchFactoryTest {

    private SearchFactory searchFactory;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        String path = new File(".").getCanonicalPath() + "/JsonStore/";
        String userJsonFilePath = path + "users.json";
        String ticketJsonFilePath = path + "tickets.json";
        String organizationFilePath = path + "organizations.json";
        searchFactory = new SearchFactory(userJsonFilePath, ticketJsonFilePath, organizationFilePath);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void Should_Return_UserSearchMediator_When_UserIsGiven() {
        SearchMediator searchMediator = searchFactory.getSearcher(SearchType.USER);
        Assert.assertTrue(searchMediator instanceof UserSearchMediator);
    }

    @Test
    public void Should_Return_UserSearchMediator_When_TicketIsGiven() {
        SearchMediator searchMediator = searchFactory.getSearcher(SearchType.TICKET);
        Assert.assertTrue(searchMediator instanceof TicketSearchMediator);
    }

    @Test
    public void Should_Return_UserSearchMediator_When_OrganizationIsGiven() {
        SearchMediator searchMediator = searchFactory.getSearcher(SearchType.ORHANIZATION);
        Assert.assertTrue(searchMediator instanceof OrganizationSearchMediator);
    }

    @Test
    public void Should_ThrowException_When_UnsupportedSearchTypeIsGiven(){
        expectedException.expect(SearchException.class);
        expectedException.expectMessage("Unsupported search type");
        searchFactory.getSearcher(SearchType.DEFAULT);
    }

    @Test
    public void Should_ThrowException_When_InvalidFilePathsAreGiven(){
        expectedException.expect(SearchException.class);
        expectedException.expectMessage("Invalid file path");
        new SearchFactory("", "", "");
    }
}