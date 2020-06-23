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
import sn.modelsis.signart.EtatAbonnement;
import sn.modelsis.signart.exception.SignArtException;


/**
 *
 * @author Pendaaa
 */
@Stateless
public class EtatAbonnementFacade extends AbstractFacade<EtatAbonnement> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;
    
    EntityTransaction tx;

    public EtatAbonnementFacade() {
        super(EtatAbonnement.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
     public EtatAbonnement findById(Integer id) throws SignArtException {
        try {
            final TypedQuery<EtatAbonnement> query = getEntityManager().createNamedQuery("EtatAbonnement.findById",
                    EtatAbonnement.class);
            query.setParameter("id", id);
            query.setMaxResults(1);
            final List<EtatAbonnement> etats = query.getResultList();
            if (etats.isEmpty()) {
                return null;
            }
            return etats.get(0);
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }
    }
     
     public EtatAbonnement findByLibelle(String libelle) throws SignArtException {
        try {
            final TypedQuery<EtatAbonnement> query = getEntityManager().createNamedQuery("EtatAbonnement.findByLibelle",
                    EtatAbonnement.class);
            query.setParameter("libelle", libelle);
            query.setMaxResults(1);
            final List<EtatAbonnement> etats = query.getResultList();
            if (etats.isEmpty()) {
                return null;
            }
            return etats.get(0);
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }
    }
    
    @Override
    public List<EtatAbonnement> findAll(){
        
            final TypedQuery<EtatAbonnement> query = getEntityManager().createNamedQuery("EtatAbonnement.findAll",
                    EtatAbonnement.class);
            return query.getResultList();
        

    }
    
}
