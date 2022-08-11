package sn.modelsis.signart.facade;

import sn.modelsis.signart.LigneLivraison;
import sn.modelsis.signart.LignePaiement;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class LignePaiementFacade extends AbstractFacade<LignePaiement> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    public LignePaiementFacade() {
        super(LignePaiement.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
