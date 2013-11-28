package com.mapps.services.user.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import com.mapps.authentificationhandler.AuthenticationHandler;
import com.mapps.authentificationhandler.exceptions.InvalidUserException;
import com.mapps.model.User;
import com.mapps.services.user.UserService;
import com.mapps.services.user.exceptions.AuthenticationException;
import com.mapps.services.user.exceptions.CouldNotUpdateUserException;

/**
 * An implementation of the UserService.
 */
@Stateless(name = "UserService")
public class UserServiceImpl implements UserService{
    Logger logger = Logger.getLogger(UserServiceImpl.class);

    @EJB(name = "AuthenticationHandler")
    AuthenticationHandler authenticationHandler;

    @Override
    public String login(String username, String password) throws AuthenticationException {
        if (username == null || password == null){
            logger.error("Username or password null");
            throw new AuthenticationException();
        }
        User user = new User(null, null, null, null, username, username, password, null, null);
        String token;
        try {
            token = authenticationHandler.authenticate(user);
        } catch (InvalidUserException e) {
            logger.error("Username or password not valid");
            throw new AuthenticationException();
        }
        return token;
    }

    @Override
    public String logout(String token) {
        return null;
    }

    @Override
    public void updateUser(User user, String token) throws CouldNotUpdateUserException {

    }
}
