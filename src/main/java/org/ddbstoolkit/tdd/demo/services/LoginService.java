package org.ddbstoolkit.tdd.demo.services;

import org.apache.commons.codec.digest.DigestUtils;
import org.ddbstoolkit.tdd.demo.dao.UserDao;
import org.ddbstoolkit.tdd.demo.exception.EmptyFieldException;
import org.ddbstoolkit.tdd.demo.model.User;

/**
 * Login service
 */
public class LoginService {

    /**
     * Authenticate user
     * @param user User to authenticate
     * @return True if user is correctly authenticated or false if not authenticated
     * @throws EmptyFieldException Exception raised when there is a login or password sent empty
     */
    public static boolean authenticate(User user) throws EmptyFieldException {

        /**
         * If there is a JWT Web token
         */
        if(user.getJwtToken() != null) {
            User userDecode = UserDao.getUserWithToken(user.getJwtToken());
            //If we succeeded to extract a user
            if(userDecode != null) {
                //We check if the user exist in the database
                return UserDao.getUser(userDecode.getLogin()) != null;
            } else {
                return false;
            }
        }

        if(user.getLogin() == null || user.getPassword() == null || user.getLogin().isEmpty() || user.getPassword().isEmpty()) {
            throw new EmptyFieldException("User has an empty field");
        }

        User currentUser = UserDao.getUser(user.getLogin());
        //If user has been found and password is correct
        if(currentUser != null && DigestUtils.md5Hex(user.getPassword()).equals(currentUser.getPassword())) {
            return true;
        } else {
            return false;
        }
    }

}
