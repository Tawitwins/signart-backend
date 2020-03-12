package sn.modelsis.signart.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import sn.modelsis.signart.Couleur;
import sn.modelsis.signart.exception.SignArtException;

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
    
    public Couleur findById(Integer idCouleur) throws SignArtException {
        try {
            final TypedQuery<Couleur> query = getEntityManager().createNamedQuery("Couleur.findById",
                    Couleur.class);
             query.setParameter("id", idCouleur);
            return query.getSingleResult();
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }

    }
    
}
