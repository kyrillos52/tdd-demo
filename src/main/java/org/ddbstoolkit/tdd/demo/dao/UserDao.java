package org.ddbstoolkit.tdd.demo.dao;

import org.apache.commons.codec.digest.DigestUtils;
import org.ddbstoolkit.tdd.demo.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * User DAO (Data Access Object)
 * https://en.wikipedia.org/wiki/Data_access_object
 */
public class UserDao {

    /**
     * Simulates a database of users
     */
    private static List<User> users = new ArrayList<>();


    /**
     * Get user with login
     * @param login User login
     * @return Retrieve a user if user found or null if not found
     */
    public static User getUser(String login) {
        for(User user : users) {
            if(user.getLogin().equals(login)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Add a user inside the database
     * @param user The user to add
     */
    public static void add(User user) {
        /**
         * For security reasons, the password is hashed using MD5 hashing system
         */
        users.add(User.createUserWithLoginPassword(user.getLogin(), DigestUtils.md5Hex(user.getPassword())));
    }
}
