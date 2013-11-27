package com.mapps.services.user;

/**
 * Defines the services for handling user interactions with the system.
 */
public interface UserService {
    /**
     * Authentificates a user of the system to interact with the system
     * @param username name of the user in the system.
     * @param password password of the user in the system.
     * @return token that represent the session.
     */
    String login(String username, String password);
}
