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
        User currentUser = UserDao.getUser(user.getLogin());
        if(DigestUtils.md5Hex(user.getPassword()).equals(currentUser.getPassword())) {
            return true;
        } else {
            return false;
        }
    }

}
