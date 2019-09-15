package com.challenge.app.dto;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * This class tests the Ticket class
 */
public class TicketTest {

    Ticket ticket;

    @Before
    public void setUp() throws Exception {
        ticket = new Ticket(getTicketMap());
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void Should_Return_PrintMap() {
        Map<String, String> result = ticket.getPrintMap();
        Assert.assertEquals("123", result.get(Ticket.ID_KEY));
        Assert.assertEquals("url", result.get(Ticket.URL_KEY));
        Assert.assertEquals("eid", result.get(Ticket.EXTERNAL_ID));
        Assert.assertEquals("cat", result.get(Ticket.CREATED_AT));
        Assert.assertEquals("type", result.get(Ticket.TYPE_KEY));
        Assert.assertEquals("sub", result.get(Ticket.SUBJECT_KEY));
        Assert.assertEquals("desc", result.get(Ticket.DESCRIPTION_KEY));
        Assert.assertEquals("pri", result.get(Ticket.PRIORITY_KEY));
        Assert.assertEquals("stat", result.get(Ticket.STATUS_KEY));
        Assert.assertEquals("45", result.get(Ticket.ORGANIZATION_ID));
        Assert.assertEquals("33", result.get(Ticket.SUBMITTER_ID));
        Assert.assertEquals("22", result.get(Ticket.ASSIGNEE_ID));
        Assert.assertEquals("[tag_1]", result.get(Ticket.TAGS_KEY));
        Assert.assertEquals("due", result.get(Ticket.DUE_AT));
        Assert.assertEquals("true", result.get(Ticket.HAS_INCIDENTS));
        Assert.assertEquals("via", result.get(Ticket.VIA_KAY));
    }

    private Map<String, Object> getTicketMap() {
        Map<String, Object> map = new HashMap<>();
        map.put(Ticket.ID_KEY, "123");
        map.put(Ticket.URL_KEY, "url");
        map.put(Ticket.EXTERNAL_ID, "eid");
        map.put(Ticket.CREATED_AT, "cat");
        map.put(Ticket.TYPE_KEY, "type");
        map.put(Ticket.SUBJECT_KEY, "sub");
        map.put(Ticket.DESCRIPTION_KEY, "desc");
        map.put(Ticket.PRIORITY_KEY, "pri");
        map.put(Ticket.STATUS_KEY, "stat");
        map.put(Ticket.ORGANIZATION_ID, 45);
        map.put(Ticket.ASSIGNEE_ID, 22);
        map.put(Ticket.SUBMITTER_ID, 33);
        map.put(Ticket.TAGS_KEY, Arrays.asList("tag_1"));
        map.put(Ticket.HAS_INCIDENTS, true);
        map.put(Ticket.DUE_AT, "due");
        map.put(Ticket.VIA_KAY, "via");
        return map;
    }
}