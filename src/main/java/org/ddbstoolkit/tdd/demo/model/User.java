package org.ddbstoolkit.tdd.demo.model;

/**
 * User class
 */
public class User {

    /**
     * Create a user with a login password
     * @param login User login
     * @param password User password
     * @return A user object
     */
    public static User createUserWithLoginPassword(String login, String password) {
        return new User(login, password);
    }

    /**
     * User login
     */
    private String login;

    /**
     * User password
     */
    private String password;

    /**
     * User constructor
     * @param login User login
     * @param password User password
     */
    private User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    /**
     * Retrieve login
     * @return Login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Retrieve password
     * @return Password
     */
    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
