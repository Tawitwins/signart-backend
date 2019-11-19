package sn.modelsis.signart.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import sn.modelsis.signart.MarquageOeuvre;
import sn.modelsis.signart.exception.SignArtException;

/**
 *
 * @author SNLOM
 */
@Stateless
public class MarquageOeuvreFacade extends AbstractFacade<MarquageOeuvre> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    public MarquageOeuvreFacade() {
        super(MarquageOeuvre.class);
    }


    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     * 
     * @param codeTypeMarquage
     * @param idClient
     * @param idOeuvre
     * @return
     * @throws SignArtException 
     */
    public MarquageOeuvre findMarqueByClientAndOeuvre(String codeTypeMarquage, Integer idClient, Integer idOeuvre) throws SignArtException {
        try {
            final TypedQuery<MarquageOeuvre> query = getEntityManager().createNamedQuery("MarquageOeuvre.findMarqueByClientAndOeuvre",
                    MarquageOeuvre.class);
            query.setParameter("idClient", idClient);
            query.setParameter("idOeuvre", idOeuvre);
            query.setParameter("codeTypeMarquage", codeTypeMarquage);
            query.setMaxResults(1);
            final List<MarquageOeuvre> list = query.getResultList();
            if (list.isEmpty()) {
                return null;
            }
            return list.get(0);
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }

    }
}
