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
import sn.modelsis.signart.Delai;
import sn.modelsis.signart.Terminal;
import sn.modelsis.signart.exception.SignArtException;

/**
 *
 * @author Pendaaa
 */
@Stateless
public class DelaiFacade extends AbstractFacade<Delai> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;
    
    EntityTransaction tx;

    public DelaiFacade() {
        super(Delai.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public Delai findById(Integer id) throws SignArtException {
        try {
            final TypedQuery<Delai> query = getEntityManager().createNamedQuery("Delai.findById",
                    Delai.class);
            query.setParameter("id", id);
            //query.setMaxResults(1);
            final List<Delai> delais = query.getResultList();
            if (delais.isEmpty()) {
                return null;
            }
            return delais.get(0);
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }
    }
    
    @Override
    public List<Delai> findAll(){
        
            final TypedQuery<Delai> query = getEntityManager().createNamedQuery("Delai.findAll",
                    Delai.class);
            return query.getResultList();
        

    }
    
}
