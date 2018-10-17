package org.ddbstoolkit.tdd.demo.services;

import org.ddbstoolkit.tdd.demo.dao.UserDao;
import org.ddbstoolkit.tdd.demo.exception.EmptyFieldException;
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
    public void testLoginUsername() throws EmptyFieldException {
        Assert.assertTrue(LoginService.authenticate(User.createUserWithLoginPassword("test", "test")));
        Assert.assertFalse(LoginService.authenticate(User.createUserWithLoginPassword("test", "test2")));

        //Add an unknown user
        Assert.assertTrue(LoginService.authenticate(User.createUserWithLoginPassword("test2", "test")));
    }

    /**
     * Test a with a null login
     * @throws EmptyFieldException
     */
    @Test(expected = EmptyFieldException.class)
    public void testNullLogin() throws EmptyFieldException {
        LoginService.authenticate(User.createUserWithLoginPassword(null, ""));
    }

    /**
     * Test a with a null password
     * @throws EmptyFieldException
     */
    @Test(expected = EmptyFieldException.class)
    public void testNullPassword() throws EmptyFieldException {
        LoginService.authenticate(User.createUserWithLoginPassword("test", null));
    }

    /**
     * Test a with a empty login
     * @throws EmptyFieldException
     */
    @Test(expected = EmptyFieldException.class)
    public void testEmptyLogin() throws EmptyFieldException {
        LoginService.authenticate(User.createUserWithLoginPassword("", "test"));
    }

    /**
     * Test a with an empty password
     * @throws EmptyFieldException
     */
    @Test(expected = EmptyFieldException.class)
    public void testEmptyPassword() throws EmptyFieldException {
        LoginService.authenticate(User.createUserWithLoginPassword("test", ""));
    }
}
