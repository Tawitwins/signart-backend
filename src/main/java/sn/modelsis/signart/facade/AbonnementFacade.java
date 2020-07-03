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
import sn.modelsis.signart.Abonnement;
import sn.modelsis.signart.exception.SignArtException;

/**
 *
 * @author Pendaaa
 */
@Stateless
public class AbonnementFacade extends AbstractFacade<Abonnement> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;
    
    EntityTransaction tx;

    public AbonnementFacade() {
        super(Abonnement.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public Abonnement findById(Integer id) throws SignArtException {
        try {
            final TypedQuery<Abonnement> query = getEntityManager().createNamedQuery("Abonnement.findById",
                    Abonnement.class);
            query.setParameter("id", id);
            query.setMaxResults(1);
            final List<Abonnement> abonnements = query.getResultList();
            if (abonnements.isEmpty()) {
                return null;
            }
            return abonnements.get(0);
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }
    }
    
     public List<Abonnement> findByIdAbonne(Integer idAbonne) throws SignArtException {
        try {
            final TypedQuery<Abonnement> query = getEntityManager().createNamedQuery("Abonnement.findByIdAbonne",
                    Abonnement.class);
            query.setParameter("idAbonne", idAbonne);
            query.setMaxResults(1);
            final List<Abonnement> abonnements = query.getResultList();
            if (abonnements.isEmpty()) {
                return null;
            }
            return abonnements;
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }
    }
    
    /**
     *
     * @return
     * @throws SignArtException
     */
    @Override
    public List<Abonnement> findAll(){
        
            final TypedQuery<Abonnement> query = getEntityManager().createNamedQuery("Abonnement.findAll",
                    Abonnement.class);
            return query.getResultList();
    }

    
}
