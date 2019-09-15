package com.challenge.app.searchers.jsons;

import com.challenge.app.dto.Searchable;
import com.challenge.app.dto.Ticket;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * This class is used to test the TicketSearcher class.
 */
public class TicketSearcherTest {

    private TicketSearcher ticketSearcher;

    @Before
    public void setUp() throws Exception {
        DocumentContext jsonContext = JsonPath.parse(getJsonContext());
        ticketSearcher = new TicketSearcher(jsonContext);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void Should_Return_NoSearchResults_When_InvalidKeyIsGiven() {
        List<Searchable> result = ticketSearcher.search("name1", "Francisca Rasmussen");
        Assert.assertEquals(0, result.size());
    }

    @Test
    public void Should_Return_NoSearchResults_When_InvalidValueIsGiven() {
        List<Searchable> result = ticketSearcher.search("name", "Francisca Rasmussen1");
        Assert.assertEquals(0, result.size());
    }

    @Test
    public void Should_Return_SearchResults_When_TagIsGiven() {
        List<Searchable> result = ticketSearcher.search("tags", "American Samoa");
        Assert.assertEquals(1, result.size());
        Assert.assertEquals(4, ((Ticket) result.get(0)).getTags().size());
    }

    @Test
    public void Should_Return_SearchResults_When_SubmitterIdIsGiven() {
        List<Searchable> result = ticketSearcher.search("submitter_id", "38");
        Assert.assertEquals(1, result.size());
        Assert.assertEquals(38, ((Ticket) result.get(0)).getSubmitterId());
    }

    @Test
    public void Should_Return_SearchResults_When_AssigneeIdIsGiven() {
        List<Searchable> result = ticketSearcher.search("assignee_id", "24");
        Assert.assertEquals(1, result.size());
        Assert.assertEquals(24, ((Ticket) result.get(0)).getAssigneeId());
    }

    @Test
    public void Should_Return_SearchResults_When_OrganizationIdIsGiven() {
        List<Searchable> result = ticketSearcher.search("organization_id", "116");
        Assert.assertEquals(1, result.size());
        Assert.assertEquals(116, ((Ticket) result.get(0)).getOrganizationId());
    }

    @Test
    public void Should_Return_SearchResults_When_HasIncidentsIsGiven() {
        List<Searchable> result = ticketSearcher.search("has_incidents", "false");
        Assert.assertEquals(2, result.size());
        Assert.assertFalse(((Ticket) result.get(0)).isHasIncidents());
    }

    @Test
    public void Should_Return_Subject_When_TicketIdIsGiven() {
        String result = ticketSearcher.searchNameById("436bf9b0-1147-4c0a-8439-6f79833bff5b");
        Assert.assertEquals("A Catastrophe in Korea (North)", result);
    }

    @Test
    public void Should_Return_ListOfSingleTicketId_When_OrganizationIdIsGiven() {
        List<String> result = ticketSearcher.searchTicketIdsByOrganizationId(116);
        Assert.assertEquals(1, result.size());
        Assert.assertEquals("436bf9b0-1147-4c0a-8439-6f79833bff5b", result.get(0));
    }

    @Test
    public void Should_Return_ListOfTicketIds_When_OrganizationIdIsGiven() {
        List<String> result = ticketSearcher.searchTicketIdsByOrganizationId(112);
        Assert.assertEquals(2, result.size());
        Assert.assertEquals("1a227508-9f39-427c-8f57-1b72f3fab87c", result.get(0));
        Assert.assertEquals("5507c3f7-27fe-48f1-b01e-46d31715cc62", result.get(1));
    }

    @Test
    public void Should_Return_ListOfAssignedTicketId_When_userIdIsGiven() {
        List<String> result = ticketSearcher.searchAssignedTicketIdsByUserId(24);
        Assert.assertEquals(1, result.size());
        Assert.assertEquals("436bf9b0-1147-4c0a-8439-6f79833bff5b", result.get(0));
    }

    @Test
    public void Should_Return_ListOfSubmittedTicketId_When_userIdIsGiven() {
        List<String> result = ticketSearcher.searchSubmittedTicketIdsByUserId(38);
        Assert.assertEquals(1, result.size());
        Assert.assertEquals("436bf9b0-1147-4c0a-8439-6f79833bff5b", result.get(0));
    }

    private String getJsonContext() {
        return "[\n" +
                "  {\n" +
                "    \"_id\": \"436bf9b0-1147-4c0a-8439-6f79833bff5b\",\n" +
                "    \"url\": \"http://initech.tokoin.io.com/api/v2/tickets/436bf9b0-1147-4c0a-8439-6f79833bff5b.json\",\n" +
                "    \"external_id\": \"9210cdc9-4bee-485f-a078-35396cd74063\",\n" +
                "    \"created_at\": \"2016-04-28T11:19:34 -10:00\",\n" +
                "    \"type\": \"incident\",\n" +
                "    \"subject\": \"A Catastrophe in Korea (North)\",\n" +
                "    \"description\": \"Nostrud ad sit velit cupidatat laboris ipsum nisi amet laboris ex exercitation amet et proident. Ipsum fugiat aute dolore tempor nostrud velit ipsum.\",\n" +
                "    \"priority\": \"high\",\n" +
                "    \"status\": \"pending\",\n" +
                "    \"submitter_id\": 38,\n" +
                "    \"assignee_id\": 24,\n" +
                "    \"organization_id\": 116,\n" +
                "    \"tags\": [\n" +
                "      \"Ohio\",\n" +
                "      \"Pennsylvania\",\n" +
                "      \"American Samoa\",\n" +
                "      \"Northern Mariana Islands\"\n" +
                "    ],\n" +
                "    \"has_incidents\": false,\n" +
                "    \"due_at\": \"2016-07-31T02:37:50 -10:00\",\n" +
                "    \"via\": \"web\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"_id\": \"1a227508-9f39-427c-8f57-1b72f3fab87c\",\n" +
                "    \"url\": \"http://initech.tokoin.io.com/api/v2/tickets/1a227508-9f39-427c-8f57-1b72f3fab87c.json\",\n" +
                "    \"external_id\": \"3e5ca820-cd1f-4a02-a18f-11b18e7bb49a\",\n" +
                "    \"created_at\": \"2016-04-14T08:32:31 -10:00\",\n" +
                "    \"type\": \"incident\",\n" +
                "    \"subject\": \"A Catastrophe in Micronesia\",\n" +
                "    \"description\": \"Aliquip excepteur fugiat ex minim ea aute eu labore. Sunt eiusmod esse eu non commodo est veniam consequat.\",\n" +
                "    \"priority\": \"low\",\n" +
                "    \"status\": \"hold\",\n" +
                "    \"submitter_id\": 71,\n" +
                "    \"assignee_id\": 38,\n" +
                "    \"organization_id\": 112,\n" +
                "    \"tags\": [\n" +
                "      \"Puerto Rico\",\n" +
                "      \"Idaho\",\n" +
                "      \"Oklahoma\",\n" +
                "      \"Louisiana\"\n" +
                "    ],\n" +
                "    \"has_incidents\": false,\n" +
                "    \"due_at\": \"2016-08-15T05:37:32 -10:00\",\n" +
                "    \"via\": \"chat\"\n" +
                "   },\n" +
                "   {\n" +
                "    \"_id\": \"5507c3f7-27fe-48f1-b01e-46d31715cc62\",\n" +
                "    \"url\": \"http://initech.tokoin.io.com/api/v2/tickets/5507c3f7-27fe-48f1-b01e-46d31715cc62.json\",\n" +
                "    \"external_id\": \"84c43214-2b6b-4631-a4bb-64af712e920b\",\n" +
                "    \"created_at\": \"2016-01-22T02:20:40 -11:00\",\n" +
                "    \"type\": \"question\",\n" +
                "    \"subject\": \"A Problem in French Guiana\",\n" +
                "    \"description\": \"Dolor anim dolore quis sint excepteur esse pariatur. Occaecat duis incididunt culpa non magna fugiat laborum.\",\n" +
                "    \"priority\": \"normal\",\n" +
                "    \"status\": \"solved\",\n" +
                "    \"submitter_id\": 10,\n" +
                "    \"assignee_id\": 53,\n" +
                "    \"organization_id\": 112,\n" +
                "    \"tags\": [\n" +
                "      \"Missouri\",\n" +
                "      \"Alabama\",\n" +
                "      \"Virginia\",\n" +
                "      \"Virgin Islands\"\n" +
                "    ],\n" +
                "    \"has_incidents\": true,\n" +
                "    \"via\": \"chat\"\n" +
                "  }" +
                "]";
    }
}