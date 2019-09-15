package com.challenge.app.searchers.jsons;

import com.challenge.app.dto.Organization;
import com.challenge.app.dto.Searchable;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * This class is used to test the OrganizationSearcher class.
 */
public class OrganizationSearcherTest {

    private OrganizationSearcher organizationSearcher;

    @Before
    public void setUp() throws Exception {
        DocumentContext jsonContext = JsonPath.parse(getJsonContext());
        organizationSearcher = new OrganizationSearcher(jsonContext);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void Should_Return_SearchResults_When_DetailsIsGiven() {
        List<Searchable> result = organizationSearcher.search("details", "MegaCorp");
        Assert.assertEquals(1, result.size());
        Assert.assertEquals("MegaCorp", ((Organization) result.get(0)).getDetails());
    }

    @Test
    public void Should_Return_SearchResults_When_IdIsGiven() {
        List<Searchable> result = organizationSearcher.search("_id", "101");
        Assert.assertEquals(1, result.size());
        Assert.assertEquals(101, ((Organization) result.get(0)).getId());
    }

    @Test
    public void Should_Return_NoSearchResults_When_InvalidKeyIsGiven() {
        List<Searchable> result = organizationSearcher.search("name1", "Francisca Rasmussen");
        Assert.assertEquals(0, result.size());
    }

    @Test
    public void Should_Return_NoSearchResults_When_InvalidValueIsGiven() {
        List<Searchable> result = organizationSearcher.search("name", "Francisca Rasmussen1");
        Assert.assertEquals(0, result.size());
    }

    @Test
    public void Should_Return_SearchResults_When_TagIsGiven() {
        List<Searchable> result = organizationSearcher.search("tags", "Fulton");
        Assert.assertEquals(1, result.size());
        Assert.assertEquals(4, ((Organization) result.get(0)).getTags().size());
    }

    @Test
    public void Should_Return_SearchResults_When_DomainNameIsGiven() {
        List<Searchable> result = organizationSearcher.search("domain_names", "endipin.com");
        Assert.assertEquals(1, result.size());
        Assert.assertEquals(4, ((Organization) result.get(0)).getDomainNames().size());
    }

    @Test
    public void Should_Return_SearchResults_When_SharedTicketsIsGiven() {
        List<Searchable> result = organizationSearcher.search("shared_tickets", "false");
        Assert.assertEquals(2, result.size());
        Assert.assertFalse(((Organization) result.get(0)).isSharedTickets());
    }

    @Test
    public void Should_Return_Name_When_OrganizationIdIsGiven() {
        String result = organizationSearcher.searchNameById(101);
        Assert.assertEquals("Enthaze", result);
    }

    private String getJsonContext() {
        return "[\n" +
                "  {\n" +
                "    \"_id\": 101,\n" +
                "    \"url\": \"http://initech.tokoin.io.com/api/v2/organizations/101.json\",\n" +
                "    \"external_id\": \"9270ed79-35eb-4a38-a46f-35725197ea8d\",\n" +
                "    \"name\": \"Enthaze\",\n" +
                "    \"domain_names\": [\n" +
                "      \"kage.com\",\n" +
                "      \"ecratic.com\",\n" +
                "      \"endipin.com\",\n" +
                "      \"zentix.com\"\n" +
                "    ],\n" +
                "    \"created_at\": \"2016-05-21T11:10:28 -10:00\",\n" +
                "    \"details\": \"MegaCorp\",\n" +
                "    \"shared_tickets\": false,\n" +
                "    \"tags\": [\n" +
                "      \"Fulton\",\n" +
                "      \"West\",\n" +
                "      \"Rodriguez\",\n" +
                "      \"Farley\"\n" +
                "    ]\n" +
                "  },\n" +
                "  {\n" +
                "    \"_id\": 102,\n" +
                "    \"url\": \"http://initech.tokoin.io.com/api/v2/organizations/102.json\",\n" +
                "    \"external_id\": \"7cd6b8d4-2999-4ff2-8cfd-44d05b449226\",\n" +
                "    \"name\": \"Nutralab\",\n" +
                "    \"domain_names\": [\n" +
                "      \"trollery.com\",\n" +
                "      \"datagen.com\",\n" +
                "      \"bluegrain.com\",\n" +
                "      \"dadabase.com\"\n" +
                "    ],\n" +
                "    \"created_at\": \"2016-04-07T08:21:44 -10:00\",\n" +
                "    \"details\": \"Non profit\",\n" +
                "    \"shared_tickets\": false,\n" +
                "    \"tags\": [\n" +
                "      \"Cherry\",\n" +
                "      \"Collier\",\n" +
                "      \"Fuentes\",\n" +
                "      \"Trevino\"\n" +
                "    ]\n" +
                "  }" +
                "]";
    }
}