package sn.modelsis.signart.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sn.modelsis.signart.Paiement;

/**
 *
 * @author SNLOM
 */
@Stateless
public class PaiementFacade extends AbstractFacade<Paiement> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    public PaiementFacade() {
        super(Paiement.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
