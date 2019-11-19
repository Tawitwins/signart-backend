package sn.modelsis.signart.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import sn.modelsis.signart.Filmographie;
import sn.modelsis.signart.exception.SignArtException;

/**
 *
 * @author SNLOM
 */
@Stateless
public class FilmographieFacade extends AbstractFacade<Filmographie> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    public FilmographieFacade() {
        super(Filmographie.class);
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
    public List<Filmographie> findByArtiste(Integer idArtiste) throws SignArtException {
        try {
            final TypedQuery<Filmographie> query = getEntityManager().createNamedQuery("Filmographie.findByArtiste",
                    Filmographie.class);
            query.setParameter("idArtiste", idArtiste);
            return query.getResultList();
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }

    }
}
