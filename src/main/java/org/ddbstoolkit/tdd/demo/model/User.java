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
        return new User(login, password, null);
    }

    /**
     * Create a user with a JWT token
     * @param jwtToken JWT Token
     * @return User with a JWT Token
     */
    public static User createUserWithJwtToken(String jwtToken) {
        return new User(null, null, jwtToken);
    }

    /**
     * Create a user with only the login information
     * @param login User login
     * @return User with login information
     */
    public static User createUserWithLogin(String login) {
        return new User(login, null, null);
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
     * User JWT token
     */
    private String jwtToken;

    /**
     * User constructor
     * @param login User login
     * @param password User password
     * @param jwtToken User JWT token
     */
    private User(String login, String password, String jwtToken) {
        this.login = login;
        this.password = password;
        this.jwtToken = jwtToken;
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

    /**
     * Retrieve JWT token
     * @return JWT token
     */
    public String getJwtToken() {
        return jwtToken;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", jwtToken='" + jwtToken + '\'' +
                '}';
    }
}
