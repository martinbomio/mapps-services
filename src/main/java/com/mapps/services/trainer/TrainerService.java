package com.mapps.services.trainer;

import com.mapps.model.Athlete;
import com.mapps.model.Training;

/**
 * Created with IntelliJ IDEA.
 * User: Usuario1
 * Date: 27/11/13
 * Time: 11:07 AM
 * To change this template use File | Settings | File Templates.
 */
public interface TrainerService {

    /**
     * Starts a training
     * @param training Training to start.
     * @param token that represent the session.
     */
    void startTraining(Training training, String token);

    /**
     * Stops a training
     * @param training Training to stop.
     * @param token that represent the session.
     */
    void stopTraining(Training training, String token);

    /**
     * Adds an athlete to the system
     * @param athlete Athlete to add
     * @param token
     */
    void addAthlete(Athlete athlete,String token);
}
