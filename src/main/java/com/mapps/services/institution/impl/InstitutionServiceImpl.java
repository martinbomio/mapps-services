package com.mapps.services.institution.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.mapps.model.Institution;
import com.mapps.persistence.InstitutionDAO;
import com.mapps.services.institution.InstitutionService;
import com.mapps.services.institution.exceptions.AuthenticationException;
import com.mapps.services.institution.exceptions.InvalidInstitutionException;
import org.apache.log4j.Logger;



/**
 * Created with IntelliJ IDEA.
 * User: Usuario1
 * Date: 29/11/13
 * Time: 05:14 PM
 * To change this template use File | Settings | File Templates.
 */

@Stateless(name="InstitutionService")
public class InstitutionServiceImpl implements InstitutionService {
    Logger logger = Logger.getLogger(InstitutionServiceImpl.class);

    @EJB(name = "InstitutionDAO")
    private InstitutionDAO institutionDAO;

    @Override
    public void createInstitution(Institution institution, String token) throws AuthenticationException, InvalidInstitutionException {

    }

    @Override
    public void deleteInstitution(Institution institution, String token) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void updateInstitution(Institution institution, String token) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
