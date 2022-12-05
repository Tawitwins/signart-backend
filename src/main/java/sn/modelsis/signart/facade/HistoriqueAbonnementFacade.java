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
import sn.modelsis.signart.HistoriqueAbonnement;
import sn.modelsis.signart.exception.SignArtException;

/**
 *
 * @author snfayemp
 */
@Stateless
public class HistoriqueAbonnementFacade extends AbstractFacade<HistoriqueAbonnement> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;
    
    EntityTransaction tx;

    public HistoriqueAbonnementFacade() {
        super(HistoriqueAbonnement.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
     public HistoriqueAbonnement findByIdAbonnement(Integer idAbonnement) throws SignArtException {
        try {
            final TypedQuery<HistoriqueAbonnement> query = getEntityManager().createNamedQuery("HistoriqueAbonnement.findByIdAbonnement",
                    HistoriqueAbonnement.class);
            query.setParameter("idAbonnement", idAbonnement);
            return query.getSingleResult();
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }

    }
     
      public List<HistoriqueAbonnement> findAllByIdUtilisateur(Integer idUtilisateur) throws SignArtException {
        try {
            final TypedQuery<HistoriqueAbonnement> query = getEntityManager().createNamedQuery("HistoriqueAbonnement.findAllByIdUtilisateur",
                    HistoriqueAbonnement.class);
            query.setParameter("idUtilisateur", idUtilisateur);
            return query.getResultList();
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }

    }
    
    public HistoriqueAbonnement findById(Integer id) throws SignArtException {
        try {
            final TypedQuery<HistoriqueAbonnement> query = getEntityManager().createNamedQuery("HistoriqueAbonnement.findById",
                    HistoriqueAbonnement.class);
            query.setParameter("id", id);
            //query.setMaxResults(1);
            final List<HistoriqueAbonnement> historiqueAbonnements = query.getResultList();
            if (historiqueAbonnements.isEmpty()) {
                return null;
            }
            return historiqueAbonnements.get(0);
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }
    }
    
}
