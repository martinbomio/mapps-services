package com.mapps.services.institution;

import com.mapps.model.Institution;
import com.mapps.services.institution.exceptions.AuthenticationException;
import com.mapps.services.institution.exceptions.InvalidInstitutionException;

/**
 *
 */
public interface InstitutionService {

    /**
     * Creates and persist an Institution into the system
     * @param institution Institution to add in the system.
     * @param token that represent the session.
     */
  void createInstitution(Institution institution, String token)throws AuthenticationException,InvalidInstitutionException;

    /**
     * Deletes an Institution from the system
     * @param institution Institution to delete in the system.
     * @param token that represent the session.
     */
  void deleteInstitution(Institution institution, String token)throws AuthenticationException,InvalidInstitutionException;

    /**
     * Updates an Institution
     * @param institution Institution to update.
     * @param token that represent the session.
     */
  void updateInstitution(Institution institution, String token)throws AuthenticationException,InvalidInstitutionException;


}
