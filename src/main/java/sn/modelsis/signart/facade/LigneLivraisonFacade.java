package sn.modelsis.signart.facade;

import sn.modelsis.signart.LigneLivraison;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class LigneLivraisonFacade extends AbstractFacade<LigneLivraison> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    public LigneLivraisonFacade() {
        super(LigneLivraison.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
