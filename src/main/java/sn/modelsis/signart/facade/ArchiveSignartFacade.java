/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.modelsis.signart.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import sn.modelsis.signart.ArchiveSignart;

/**
 *
 * @author snfayemp
 */
@Stateless
public class ArchiveSignartFacade extends AbstractFacade<ArchiveSignart> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;
    
    EntityTransaction tx;

    public ArchiveSignartFacade() {
        super(ArchiveSignart.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    
}
