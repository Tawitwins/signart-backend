package sn.modelsis.signart.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import sn.modelsis.signart.Promotion;
import sn.modelsis.signart.exception.SignArtException;

/**
 *
 * @author SNLOM
 */
@Stateless
public class PromotionFacade extends AbstractFacade<Promotion> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    public PromotionFacade() {
        super(Promotion.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     * 
     * @param idOeuvre
     * @return
     * @throws SignArtException 
     */
    public List<Promotion> findByOeuvre(Integer idOeuvre) throws SignArtException {
        try {
            final TypedQuery<Promotion> query = getEntityManager().createNamedQuery("Promotion.findByOeuvre",
                    Promotion.class);
            query.setParameter("idOeuvre", idOeuvre);
            return query.getResultList();
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }
    }
}
