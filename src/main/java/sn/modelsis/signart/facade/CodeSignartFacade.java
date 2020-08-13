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
import sn.modelsis.signart.CodeSignart;

/**
 *
 * @author snfayemp
 */
@Stateless
public class CodeSignartFacade extends AbstractFacade<CodeSignart> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;
    
    EntityTransaction tx;

    public CodeSignartFacade() {
        super(CodeSignart.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    
}
