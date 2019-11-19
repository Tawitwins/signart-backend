package sn.modelsis.signart.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sn.modelsis.signart.Livraison;

/**
 *
 * @author SNLOM
 */
@Stateless
public class LivraisonFacade extends AbstractFacade<Livraison> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    public LivraisonFacade() {
        super(Livraison.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
