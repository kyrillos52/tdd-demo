package org.ddbstoolkit.tdd.demo.services;

import org.ddbstoolkit.tdd.demo.dao.UserDao;
import org.ddbstoolkit.tdd.demo.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit class to test Login Service class
 */
public class LoginServiceTest {

    /**
     * Prepare data inside the database
     */
    @Before
    public void prepareData() {
        //We add a user with login test and password test
        UserDao.add(User.createUserWithLoginPassword("test", "test"));
    }

    /**
     * Test with a valid password and invalid password
     */
    @Test
    public void testLoginUsername() {
        Assert.assertTrue(LoginService.authenticate(User.createUserWithLoginPassword("test", "test")));
        Assert.assertFalse(LoginService.authenticate(User.createUserWithLoginPassword("test", "test2")));
    }
}
