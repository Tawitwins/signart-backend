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
import javax.persistence.TypedQuery;
import sn.modelsis.signart.CodeSignart;
import sn.modelsis.signart.exception.SignArtException;

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
    
    public CodeSignart findByCode(String code) throws SignArtException {
        try {
            final TypedQuery<CodeSignart> query = getEntityManager().createNamedQuery("CodeSignart.findBycode",
                    CodeSignart.class);
            query.setParameter("code", code);
            return query.getSingleResult();
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }

    }
    
    
}
