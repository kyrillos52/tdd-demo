package org.ddbstoolkit.tdd.demo.dao;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTVerifyException;
import org.apache.commons.codec.digest.DigestUtils;
import org.ddbstoolkit.tdd.demo.model.User;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * JWT Secret
     */
    private static String secret = "secret";

    /**
     * Set the JWT secret
     * @param secret JWT secret
     */
    public static void setSecret(String secret) {
        UserDao.secret = secret;
    }


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

    /**
     * Create a JWT web token with user login
     * @param user Current user
     * @return JWT web token
     */
    public static String createUserToken(User user) {
        final JWTSigner signer = new JWTSigner(secret);
        final Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("user", user.getLogin());
        return signer.sign(claims);
    }

    /**
     * Get User from JWT Token
     * @param jwtToken JWT web token
     * @return User or null if not found
     */
    public static User getUserWithToken(String jwtToken) {
        final JWTVerifier verifier = new JWTVerifier(secret);
        try {
            Map<String, Object> claims =  verifier.verify(jwtToken);
            if(claims.size() > 0) {
                return User.createUserWithLogin((String)claims.get("user"));
            } else {
                return null;
            }
        } catch (NoSuchAlgorithmException | InvalidKeyException | IOException | SignatureException | JWTVerifyException e) {
            return null;
        }
    }
}
