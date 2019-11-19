package sn.modelsis.signart.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sn.modelsis.signart.Couleur;

/**
 *
 * @author SNLOM
 */
@Stateless
public class CouleurFacade extends AbstractFacade<Couleur> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    public CouleurFacade() {
        super(Couleur.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
