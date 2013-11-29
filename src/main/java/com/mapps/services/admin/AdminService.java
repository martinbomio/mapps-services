package com.mapps.services.admin;

import java.util.List;
import javax.ejb.Local;

import com.mapps.model.Device;
import com.mapps.model.Permission;
import com.mapps.model.Role;
import com.mapps.model.Training;
import com.mapps.model.User;
import com.mapps.services.admin.exceptions.AuthenticationException;
import com.mapps.services.admin.exceptions.InvalidDeviceException;
import com.mapps.services.admin.exceptions.InvalidUserException;

/**
 * Defines the operation of the Administrator with the system.
 */
@Local
public interface AdminService{
    void createUser(User newUser, String token) throws AuthenticationException, InvalidUserException;
    void deleteUser(User user, String token) throws AuthenticationException;
    void changePermissions(Training training, User user, Permission permission, String token) throws AuthenticationException;
    void changeRole(User user, Role role, String token) throws AuthenticationException;
    void addDevice(Device device, String token)throws InvalidDeviceException, AuthenticationException;
    void disableDevice(Device device, String token) throws AuthenticationException;
    List<Device> getAllDevices(String token) throws AuthenticationException;
}
