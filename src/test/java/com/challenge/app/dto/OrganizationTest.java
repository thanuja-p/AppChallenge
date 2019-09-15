package com.challenge.app.dto;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * This class tests the Organization class
 */
public class OrganizationTest {

    Organization organization;

    @Before
    public void setUp() throws Exception {
        organization = new Organization(getOrganizationMap());
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void Should_Return_PrintMap() {
        Map<String, String> result = organization.getPrintMap();
        Assert.assertEquals("123", result.get(Organization.ID_KEY));
        Assert.assertEquals("url", result.get(Organization.URL_KEY));
        Assert.assertEquals("eid", result.get(Organization.EXTERNAL_ID));
        Assert.assertEquals("name", result.get(Organization.NAME_KEY));
        Assert.assertEquals("[dn_1]", result.get(Organization.DOMAIN_NAMES));
        Assert.assertEquals("cat", result.get(Organization.CREATED_AT));
        Assert.assertEquals("details", result.get(Organization.DETAILS_KEY));
        Assert.assertEquals("true", result.get(Organization.SHARED_TICKETS));
        Assert.assertEquals("[tag_1]", result.get(Organization.TAGS_KEY));
    }

    private Map<String, Object> getOrganizationMap(){
        Map<String, Object> map = new HashMap<>();
        map.put(Organization.ID_KEY, 123);
        map.put(Organization.URL_KEY, "url");
        map.put(Organization.EXTERNAL_ID, "eid");
        map.put(Organization.NAME_KEY, "name");
        map.put(Organization.DOMAIN_NAMES, Arrays.asList("dn_1"));
        map.put(Organization.CREATED_AT, "cat");
        map.put(Organization.DETAILS_KEY, "details");
        map.put(Organization.SHARED_TICKETS, true);
        map.put(Organization.TAGS_KEY, Arrays.asList("tag_1"));
        return map;
    }
}