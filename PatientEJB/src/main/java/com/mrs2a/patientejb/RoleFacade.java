/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mrs2a.patientejb;

import com.mrs2a.entities.Role;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Snoopy
 */
@Stateless
public class RoleFacade extends AbstractFacade<Role> implements RoleFacadeLocal {
    @PersistenceContext(unitName = "patient_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RoleFacade() {
        super(Role.class);
    }
    
}
