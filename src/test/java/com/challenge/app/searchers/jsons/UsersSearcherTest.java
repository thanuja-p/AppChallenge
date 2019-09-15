package com.challenge.app.searchers.jsons;

import com.challenge.app.dto.Searchable;
import com.challenge.app.dto.User;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * This class is used to test the UserSearcher class.
 */
public class UsersSearcherTest {


    private UserSearcher usersSearcher;

    @Before
    public void setUp() throws Exception {
        DocumentContext jsonContext = JsonPath.parse(getJsonContext());
        usersSearcher = new UserSearcher(jsonContext);
    }

    @After
    public void tearDown() throws Exception {
    }

//    @Test
//    public void search() throws IOException {
////        List<Map> books = JsonPath.from(jsonString).get("store.book.findAll { book -> book.price >= 5 && book.price <= 15 }");
////        List<Map> books = JsonPath.from(jsonString).get("findAll { user -> user.organization_id == 119}");
////        List<Map> books = JsonPath.from(jsonString).get("findAll { user -> user.tags == ['Hartsville/Hartley']}");
////        List<Map> books = JsonPath.parse(jsonString).read("$[?('Hartsville/Hartley' in @['tags'])]");
//        File jsonFile = new File("/Users/thanujap/Desktop/AppChallenge/src/test/java/com/challenge/app/searchers/users.json");
//        List<Map> books = JsonPath.parse(jsonFile).read("$[?(@.organization_id == 119)]");
////        List<Map> books = JsonPath.parse(jsonString).read("$[?(@.organization_id == 119)]");
////        List<Map> books = JsonPath.parse(jsonString).read("$[?(@.active == true)]");
//
//        Printer printer = new Printer();
//        List<Printable> userSearchResultHolderList = new ArrayList<>();
//        books.forEach(c -> {
//            User user = new User(c);
//            UserSearchResultHolder userSearchResultHolder = new UserSearchResultHolder(user, new ArrayList<>(),
//                    new ArrayList<>(), new ArrayList<>());
//            userSearchResultHolderList.add(userSearchResultHolder);
////            System.out.println(user.getName());
////            user.getTags().forEach(t-> System.out.println(t));
////            System.out.println("-------");
//        });
//
//        printer.printSearchResults(userSearchResultHolderList);
//
//
////        JSONParser parser = new JSONParser();
////        try {
////            JSONObject json = (JSONObject) parser.parse(jsonString);
////
////        } catch (ParseException e) {
////            e.printStackTrace();
////        }
//    }

//    @Test
//    public void search_Tickets() throws IOException {
//        File jsonFile = new File("/Users/thanujap/Desktop/AppChallenge/src/test/java/com/challenge/app/searchers/tickets.json");
//
//        DocumentContext documentContext = JsonPath.parse(jsonFile);
//        List<Map> tickets = documentContext.read("$[?(@.organization_id == 119)]");
//
//        tickets.forEach(t -> {
//            Ticket ticket = new Ticket(t);
//            System.out.println(ticket.getSubject());
//        });
//    }

    @Test
    public void Should_Return_SearchResults_When_organizationIdIsGiven() {
        List<Searchable> result = usersSearcher.search("organization_id", "119");
        Assert.assertEquals(2, result.size());
        Assert.assertEquals(119, ((User) result.get(0)).getOrganizationId());
        Assert.assertEquals(119, ((User) result.get(1)).getOrganizationId());
    }

    @Test
    public void Should_Return_SearchResults_When_RoleIsGiven() {
        List<Searchable> result = usersSearcher.search("role", "admin");
        Assert.assertEquals(2, result.size());
        Assert.assertEquals("admin", ((User) result.get(0)).getRole());
    }

    @Test
    public void Should_Return_SearchResults_When_NameIsGiven() {
        List<Searchable> result = usersSearcher.search("name", "Francisca Rasmussen");
        Assert.assertEquals(1, result.size());
        Assert.assertEquals("Francisca Rasmussen", ((User) result.get(0)).getName());
    }

    @Test
    public void Should_Return_NoSearchResults_When_InvalidKeyIsGiven() {
        List<Searchable> result = usersSearcher.search("name1", "Francisca Rasmussen");
        Assert.assertEquals(0, result.size());
    }

    @Test
    public void Should_Return_NoSearchResults_When_InvalidValueIsGiven() {
        List<Searchable> result = usersSearcher.search("name", "Francisca Rasmussen1");
        Assert.assertEquals(0, result.size());
    }

    @Test
    public void Should_Return_SearchResults_When_TagIsGiven() {
        List<Searchable> result = usersSearcher.search("tags", "Hartsville/Hartley");
        Assert.assertEquals(1, result.size());
        Assert.assertEquals(4, ((User) result.get(0)).getTags().size());
    }

    @Test
    public void Should_Return_Name_When_UserIdIsGiven() {
        String result = usersSearcher.searchNameById(1);
        Assert.assertEquals("Francisca Rasmussen", result);
    }

    @Test
    public void Should_Return_ListOfSingleUserId_When_OrganizationIdIsGiven() {
        List<Integer> result = usersSearcher.searchUserIdsByOrganizationId(106);
        Assert.assertEquals(1, result.size());
        Assert.assertEquals((Integer) 2, result.get(0));
    }

    @Test
    public void Should_Return_ListOfUserIds_When_OrganizationIdIsGiven() {
        List<Integer> result = usersSearcher.searchUserIdsByOrganizationId(119);
        Assert.assertEquals(2, result.size());
        Assert.assertEquals((Integer) 1, result.get(0));
        Assert.assertEquals((Integer) 48, result.get(1));
    }

    private String getJsonContext() {
        return "[\n" +
                "  {\n" +
                "    \"_id\": 1,\n" +
                "    \"url\": \"http://initech.tokoin.io.com/api/v2/users/1.json\",\n" +
                "    \"external_id\": \"74341f74-9c79-49d5-9611-87ef9b6eb75f\",\n" +
                "    \"name\": \"Francisca Rasmussen\",\n" +
                "    \"alias\": \"Miss Coffey\",\n" +
                "    \"created_at\": \"2016-04-15T05:19:46 -10:00\",\n" +
                "    \"active\": true,\n" +
                "    \"verified\": true,\n" +
                "    \"shared\": false,\n" +
                "    \"locale\": \"en-AU\",\n" +
                "    \"timezone\": \"Sri Lanka\",\n" +
                "    \"last_login_at\": \"2013-08-04T01:03:27 -10:00\",\n" +
                "    \"email\": \"coffeyrasmussen@flotonic.com\",\n" +
                "    \"phone\": \"8335-422-718\",\n" +
                "    \"signature\": \"Don't Worry Be Happy!\",\n" +
                "    \"organization_id\": 119,\n" +
                "    \"tags\": [\n" +
                "      \"Springville\",\n" +
                "      \"Sutton\",\n" +
                "      \"Hartsville/Hartley\",\n" +
                "      \"Diaperville\"\n" +
                "    ],\n" +
                "    \"suspended\": true,\n" +
                "    \"role\": \"admin\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"_id\": 2,\n" +
                "    \"url\": \"http://initech.tokoin.io.com/api/v2/users/2.json\",\n" +
                "    \"external_id\": \"c9995ea4-ff72-46e0-ab77-dfe0ae1ef6c2\",\n" +
                "    \"name\": \"Cross Barlow\",\n" +
                "    \"alias\": \"Miss Joni\",\n" +
                "    \"created_at\": \"2016-06-23T10:31:39 -10:00\",\n" +
                "    \"active\": true,\n" +
                "    \"verified\": true,\n" +
                "    \"shared\": false,\n" +
                "    \"locale\": \"zh-CN\",\n" +
                "    \"timezone\": \"Armenia\",\n" +
                "    \"last_login_at\": \"2012-04-12T04:03:28 -10:00\",\n" +
                "    \"email\": \"jonibarlow@flotonic.com\",\n" +
                "    \"phone\": \"9575-552-585\",\n" +
                "    \"signature\": \"Don't Worry Be Happy!\",\n" +
                "    \"organization_id\": 106,\n" +
                "    \"tags\": [\n" +
                "      \"Foxworth\",\n" +
                "      \"Woodlands\",\n" +
                "      \"Herlong\",\n" +
                "      \"Henrietta\"\n" +
                "    ],\n" +
                "    \"suspended\": false,\n" +
                "    \"role\": \"admin\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"_id\": 48,\n" +
                "    \"url\": \"http://initech.tokoin.io.com/api/v2/users/48.json\",\n" +
                "    \"external_id\": \"80e6a7b7-9a2a-4b93-93da-68bd5e95cbb8\",\n" +
                "    \"name\": \"Pitts Park\",\n" +
                "    \"alias\": \"Miss Betsy\",\n" +
                "    \"created_at\": \"2016-02-02T03:34:44 -11:00\",\n" +
                "    \"active\": false,\n" +
                "    \"verified\": true,\n" +
                "    \"shared\": false,\n" +
                "    \"locale\": \"en-AU\",\n" +
                "    \"timezone\": \"Lesotho\",\n" +
                "    \"last_login_at\": \"2012-06-01T08:51:56 -10:00\",\n" +
                "    \"email\": \"betsypark@flotonic.com\",\n" +
                "    \"phone\": \"9974-742-963\",\n" +
                "    \"signature\": \"Don't Worry Be Happy!\",\n" +
                "    \"organization_id\": 119,\n" +
                "    \"tags\": [\n" +
                "      \"Chicopee\",\n" +
                "      \"Maplewood\",\n" +
                "      \"Oley\",\n" +
                "      \"Whitmer\"\n" +
                "    ],\n" +
                "    \"suspended\": false,\n" +
                "    \"role\": \"agent\"\n" +
                "  }" +
                "]";
    }
}