package com.mapps.services.user.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import com.mapps.authentificationhandler.AuthenticationHandler;
import com.mapps.authentificationhandler.exceptions.InvalidTokenException;
import com.mapps.exceptions.UserNotFoundException;
import com.mapps.model.User;
import com.mapps.persistence.UserDAO;
import com.mapps.services.user.UserService;
import com.mapps.services.user.exceptions.AuthenticationException;
import com.mapps.services.user.exceptions.InvalidUserException;

/**
 * An implementation of the UserService.
 */
@Stateless(name = "UserService")
public class UserServiceImpl implements UserService{
    Logger logger = Logger.getLogger(UserServiceImpl.class);

    @EJB(beanName = "AuthenticationHandler")
    AuthenticationHandler authenticationHandler;
    @EJB(beanName = "UserDAO")
    UserDAO userDAO;

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
        } catch (com.mapps.authentificationhandler.exceptions.InvalidUserException e) {
            logger.error("Username or password not valid");
            throw new AuthenticationException();
        }
        return token;
    }

    @Override
    public String logout(String token) {
        return "";
    }

    @Override
    public void updateUser(User user, String token) throws InvalidUserException, AuthenticationException {
        if (user == null){
            logger.error("Not valid user to update");
            throw new InvalidUserException();
        }
        try{
            User actualUser = authenticationHandler.getUserOfToken(token);
            if (actualUser.equals(user) || actualUser.getUserName() != user.getUserName()){
                logger.error("The user that you want to update is the same");
                throw new InvalidUserException();
            }
            userDAO.updateUser(user);
        } catch (InvalidTokenException e) {
            logger.error("Invalid token");
            throw new AuthenticationException();
        }catch (UserNotFoundException e){
            logger.error("User not found");
            throw new InvalidUserException();
        }
    }
}
