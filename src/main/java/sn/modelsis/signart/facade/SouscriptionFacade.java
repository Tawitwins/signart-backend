package sn.modelsis.signart.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import sn.modelsis.signart.Souscription;
import sn.modelsis.signart.exception.SignArtException;

/**
 *
 * @author SNNGOM
 */
@Stateless
public class SouscriptionFacade extends AbstractFacade<Souscription> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    public SouscriptionFacade() {
        super(Souscription.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }


    
    /**
     * retourne le nombre d'oeuvres d'une souscription
     * @param idSouscription
     * @return
     * @throws SignArtException
     */
    public Long countOeuvreSouscription(Integer idSouscription) throws SignArtException {
        try {
            final TypedQuery<Long> query = getEntityManager().createNamedQuery("OeuvreSouscription.countOeuvreSouscriptionBySouscription",
                    Long.class);
            query.setParameter("idSouscription", idSouscription);
            return query.getSingleResult();
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }
    }
}
