package com.challenge.app.dto;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Map;

/**
 * This class tests the OrganizationSearchResultHolder class.
 */
public class OrganizationSearchResultHolderTest {

    private OrganizationSearchResultHolder organizationSearchResultHolder;

    @Before
    public void setUp() throws Exception {
        organizationSearchResultHolder = new OrganizationSearchResultHolder(
                getOrganization(),
                Arrays.asList("sub_1"),
                Arrays.asList("un_1"));
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void Should_Return_PrintMap() {
        Map<String, String> result = organizationSearchResultHolder.getPrintMap();
        Assert.assertEquals("123", result.get("_id"));
        Assert.assertEquals("[sub_1]", result.get("ticket_subjects"));
        Assert.assertEquals("[un_1]", result.get("user_names"));
    }

    private Organization getOrganization() {
        Organization organization = new Organization();
        organization.setId(123);
        return organization;
    }
}