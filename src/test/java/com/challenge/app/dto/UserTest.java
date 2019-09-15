package com.challenge.app.dto;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * This class tests the User class
 */
public class UserTest {

    User user;

    @Before
    public void setUp() throws Exception {
        user = new User(getUserMap());
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void Should_Return_PrintMap() {
        Map<String, String> result = user.getPrintMap();
        Assert.assertEquals("123", result.get(User.ID_KEY));
        Assert.assertEquals("url", result.get(User.URL_KEY));
        Assert.assertEquals("eid", result.get(User.EXTERNAL_ID));
        Assert.assertEquals("name", result.get(User.NAME_KEY));
        Assert.assertEquals("alias", result.get(User.ALIAS_KEY));
        Assert.assertEquals("cat", result.get(User.CREATED_AT));
        Assert.assertEquals("true", result.get(User.ACTIVE_KEY));
        Assert.assertEquals("false", result.get(User.VERIFIED_KEY));
        Assert.assertEquals("true", result.get(User.SHARED_KEY));
        Assert.assertEquals("local", result.get(User.LOCALE_KEY));
        Assert.assertEquals("tz", result.get(User.TIMEZONE_KEY));
        Assert.assertEquals("llat", result.get(User.LAST_LOGIN_AT));
        Assert.assertEquals("email", result.get(User.EMAIL_KEY));
        Assert.assertEquals("phone", result.get(User.PHONE_KEY));
        Assert.assertEquals("sig", result.get(User.SIGNATURE_KEY));
        Assert.assertEquals("45", result.get(User.ORGANIZATION_ID));
        Assert.assertEquals("[tag_1]", result.get(User.TAGS_KEY));
        Assert.assertEquals("false", result.get(User.SUSPENDED_KEY));
        Assert.assertEquals("role", result.get(User.ROLE_KEY));
    }

    private Map<String, Object> getUserMap(){
        Map<String, Object> map = new HashMap<>();
        map.put(User.ID_KEY, 123);
        map.put(User.URL_KEY, "url");
        map.put(User.EXTERNAL_ID, "eid");
        map.put(User.NAME_KEY, "name");
        map.put(User.ALIAS_KEY, "alias");
        map.put(User.CREATED_AT, "cat");
        map.put(User.ACTIVE_KEY, true);
        map.put(User.VERIFIED_KEY, false);
        map.put(User.SHARED_KEY, true);
        map.put(User.LOCALE_KEY, "local");
        map.put(User.TIMEZONE_KEY, "tz");
        map.put(User.LAST_LOGIN_AT, "llat");
        map.put(User.EMAIL_KEY, "email");
        map.put(User.PHONE_KEY, "phone");
        map.put(User.SIGNATURE_KEY, "sig");
        map.put(User.ORGANIZATION_ID, 45);
        map.put(User.TAGS_KEY, Arrays.asList("tag_1"));
        map.put(User.SUSPENDED_KEY, false);
        map.put(User.ROLE_KEY, "role");
        return map;
    }
}