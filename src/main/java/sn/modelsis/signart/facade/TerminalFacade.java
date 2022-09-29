/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.modelsis.signart.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import sn.modelsis.signart.Abonne;
import sn.modelsis.signart.Terminal;
import sn.modelsis.signart.exception.SignArtException;

/**
 *
 * @author Pendaaa
 */
@Stateless
public class TerminalFacade extends AbstractFacade<Terminal> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;
    
    EntityTransaction tx;

    public TerminalFacade() {
        super(Terminal.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public Terminal findById(Integer id) throws SignArtException {
        try {
            final TypedQuery<Terminal> query = getEntityManager().createNamedQuery("Terminal.findById",
                    Terminal.class);
            query.setParameter("id", id);
            query.setMaxResults(1);
            final List<Terminal> terminals = query.getResultList();
            if (terminals.isEmpty()) {
                return null;
            }
            return terminals.get(0);
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }
    }

    public Terminal findByCode(String code) throws SignArtException {
        try {
            final TypedQuery<Terminal> query = getEntityManager().createNamedQuery("Terminal.findByCode",
                    Terminal.class);
            query.setParameter("code", code);
            query.setMaxResults(1);
            final List<Terminal> terminals = query.getResultList();
            if (terminals.isEmpty()) {
                return null;
            }
            return terminals.get(0);
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }
    }
    
    public List<Terminal> findAll(){
        
            final TypedQuery<Terminal> query = getEntityManager().createNamedQuery("Terminal.findAll",
                    Terminal.class);
            return query.getResultList();
        

    }

    
    
}
