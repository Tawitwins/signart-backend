package sn.modelsis.signart.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import sn.modelsis.signart.Exposition;
import sn.modelsis.signart.exception.SignArtException;

/**
 *
 * @author SNLOM
 */
@Stateless
public class ExpositionFacade extends AbstractFacade<Exposition> {

    @PersistenceContext(unitName = "SignArtPU") 
    private EntityManager em;

    public ExpositionFacade() {
        super(Exposition.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     *
     * @param idArtiste
     * @return
     * @throws SignArtException
     */
    public List<Exposition> findByArtiste(Integer idArtiste) throws SignArtException {
        try {
            final TypedQuery<Exposition> query = getEntityManager().createNamedQuery("Exposition.findByArtiste",
                    Exposition.class);
            query.setParameter("idArtiste", idArtiste);
            return query.getResultList();
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }

    }
}
