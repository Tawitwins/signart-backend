package sn.modelsis.signart.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import sn.modelsis.signart.ArtisteFormation;
import sn.modelsis.signart.exception.SignArtException;

/**
 *
 * @author SNLOM
 */
@Stateless
public class ArtisteFormationFacade extends AbstractFacade<ArtisteFormation> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    public ArtisteFormationFacade() {
        super(ArtisteFormation.class);
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
    public List<ArtisteFormation> findByArtiste(Integer idArtiste) throws SignArtException {
        try {
            final TypedQuery<ArtisteFormation> query = getEntityManager().createNamedQuery("ArtisteFormation.findByIdArtiste",
                    ArtisteFormation.class);
            query.setParameter("idArtiste", idArtiste);
            return query.getResultList();
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }

    }
}
