package com.challenge.app.dto;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

/**
 * This class tests the TicketSearchResultHolder class.
 */
public class TicketSearchResultHolderTest {

    private TicketSearchResultHolder ticketSearchResultHolder;

    @Before
    public void setUp() throws Exception {
        ticketSearchResultHolder = new TicketSearchResultHolder(
                getTicket(),
                "assignee",
                "submitter",
                "Org Test"
        );
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void Should_Return_PrintMap() {
        Map<String, String> result = ticketSearchResultHolder.getPrintMap();
        Assert.assertEquals("123", result.get("_id"));
        Assert.assertEquals("Org Test", result.get("organization_name"));
        Assert.assertEquals("assignee", result.get("assignee_name"));
        Assert.assertEquals("submitter", result.get("submitter_name"));
    }

    private Ticket getTicket(){
        Ticket ticket = new Ticket();
        ticket.setId("123");
        return ticket;
    }
}