package com.challenge.app.dto;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;

/**
 * This class tests the UserSearchResultHolder class.
 */
public class UserSearchResultHolderTest {

    private UserSearchResultHolder userSearchResultHolder;

    @Before
    public void setUp() throws Exception {
        userSearchResultHolder = new UserSearchResultHolder(
                getUser(),
                new ArrayList<>(),
                new ArrayList<>(),
                "Org Test"
        );
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void Should_Return_PrintMap() {
        Map<String, String> result = userSearchResultHolder.getPrintMap();
        Assert.assertEquals("123", result.get("_id"));
        Assert.assertEquals("Org Test", result.get("organization_name"));
        Assert.assertEquals("[]", result.get("assignee_ticket_subjects"));
        Assert.assertEquals("[]", result.get("submitted_ticket_subjects"));
    }

    private User getUser(){
        User user = new User();
        user.setId(123);
        return user;
    }
}