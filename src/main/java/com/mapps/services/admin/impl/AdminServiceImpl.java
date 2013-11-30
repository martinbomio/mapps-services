package com.mapps.services.admin.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import com.mapps.authentificationhandler.AuthenticationHandler;
import com.mapps.authentificationhandler.exceptions.InvalidTokenException;
import com.mapps.exceptions.DeviceNotFoundException;
import com.mapps.exceptions.UserNotFoundException;
import com.mapps.model.Device;
import com.mapps.model.Permission;
import com.mapps.model.Role;
import com.mapps.model.Training;
import com.mapps.model.User;
import com.mapps.persistence.DeviceDAO;
import com.mapps.persistence.TrainingDAO;
import com.mapps.persistence.UserDAO;
import com.mapps.services.admin.AdminService;
import com.mapps.services.admin.exceptions.AuthenticationException;
import com.mapps.services.admin.exceptions.InvalidDeviceException;
import com.mapps.services.admin.exceptions.InvalidDeviceRuntimeException;
import com.mapps.services.admin.exceptions.InvalidUserException;
import com.mapps.services.admin.exceptions.InvalidUserRuntimeException;

/**
 * Implementation of AdminService
 */
@Stateless(name = "AdminService")
public class AdminServiceImpl implements AdminService{

    Logger logger = Logger.getLogger(AdminServiceImpl.class);

    @EJB(beanName = "UserDAO")
    protected UserDAO userDAO;
    @EJB(beanName = "DeviceDAO")
    protected DeviceDAO deviceDAO;
    @EJB(beanName = "TrainingDAO")
    protected TrainingDAO trainingDAO;
    @EJB(beanName = "AuthenticationHandler")
    protected AuthenticationHandler authenticationHandler;

    @Override
    public void createUser(User newUser, String token) throws AuthenticationException, InvalidUserException {
        if (newUser == null || newUser.getUserName() == null || newUser.getPassword() == null){
            logger.error("Invalid User");
            throw new InvalidUserException();
        }
        try{
            if (!authenticationHandler.isUserInRole(token,Role.ADMINISTRATOR)){
                logger.error("User not an administrator");
                throw new AuthenticationException();
            }
            newUser.setRole(Role.USER);
            userDAO.addUser(newUser);
        } catch (InvalidTokenException e) {
            logger.error("Invalid Token");
            throw new AuthenticationException();
        }
    }

    @Override
    public void deleteUser(User user, String token) throws AuthenticationException{
        if (user == null || user.getUserName() == null){
            logger.error("Invalid User");
            throw new InvalidUserRuntimeException();
        }
        try{
            if (!authenticationHandler.isUserInRole(token,Role.ADMINISTRATOR)){
                logger.error("User not an administrator");
                throw new AuthenticationException();
            }
            User dbUser = userDAO.getUserByUsername(user.getUserName());
            dbUser.setEnabled(false);
            userDAO.updateUser(dbUser);
        } catch (InvalidTokenException e) {
            logger.error("Invalid Token");
            throw new AuthenticationException();
        } catch (UserNotFoundException e) {
            logger.error("Invalid User");
            throw new InvalidUserRuntimeException();
        }
    }

    @Override
    public void changePermissions(Training training, User user, Permission permission, String token) throws AuthenticationException {
    //TODO: implement changePermissions.
    }

    @Override
    public void changeRole(User user, Role role, String token) throws AuthenticationException {
        if (user == null || user.getUserName() == null){
            logger.error("Invalid User");
            throw new InvalidUserRuntimeException();
        }
        try{
            if (!authenticationHandler.isUserInRole(token,Role.ADMINISTRATOR)){
                logger.error("User not an administrator");
                throw new AuthenticationException();
            }
            User dbUser = userDAO.getUserByUsername(user.getUserName());
            dbUser.setRole(role);
            userDAO.updateUser(dbUser);
        } catch (UserNotFoundException e) {
            logger.error("Invalid User");
            throw new InvalidUserRuntimeException();
        } catch (InvalidTokenException e) {
            logger.error("Invalid token");
            throw  new AuthenticationException();
        }
    }

    @Override
    public void addDevice(Device device, String token) throws InvalidDeviceException, AuthenticationException{
        if( device == null ){
            logger.error("Invalid device");
            throw new InvalidDeviceException();
        }
        try{
            if (!authenticationHandler.isUserInRole(token,Role.ADMINISTRATOR)){
                logger.error("User not an administrator");
                throw new AuthenticationException();
            }
            deviceDAO.addDevice(device);
        } catch (InvalidTokenException e) {
            logger.error("Invalid token");
            throw new AuthenticationException();
        }
    }

    @Override
    public void disableDevice(Device device, String token) throws AuthenticationException{
        if ( device == null){
            logger.info("Invalid device");
            throw new InvalidDeviceRuntimeException();
        }
        try{
            if (!authenticationHandler.isUserInRole(token,Role.ADMINISTRATOR)){
                logger.error("User not an administrator");
                throw new AuthenticationException();
            }
            Device dbDevice = deviceDAO.getDeviceByDir(device.getDirLow());
            dbDevice.setAvailable(false);
            deviceDAO.updateDevice(dbDevice);
        } catch (InvalidTokenException e) {
            logger.error("Invalid token");
            throw new AuthenticationException();
        } catch (DeviceNotFoundException e) {
            logger.error("Invalid device");
            throw new InvalidDeviceRuntimeException();
        }
    }
}
