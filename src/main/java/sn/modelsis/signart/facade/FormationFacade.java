package sn.modelsis.signart.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import sn.modelsis.signart.Formation;
import sn.modelsis.signart.exception.SignArtException;

/**
 *
 * @author SNLOM
 */
@Stateless
public class FormationFacade extends AbstractFacade<Formation> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    public FormationFacade() {
        super(Formation.class);
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
    public List<Formation> findByArtiste(Integer idArtiste) throws SignArtException {
        try {
            final TypedQuery<Formation> query = getEntityManager().createNamedQuery("Formation.findByArtiste",
                    Formation.class);
            query.setParameter("idArtiste", idArtiste);
            return query.getResultList();
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }

    }
    
    public Formation findById(Integer id) throws SignArtException {
        try {
            final TypedQuery<Formation> query = getEntityManager().createNamedQuery("Formation.findById",
                    Formation.class);
            query.setParameter("id", id);
            query.setMaxResults(1);
            final List<Formation> formations = query.getResultList();
            if (formations.isEmpty()) {
                return null;
            }
            return formations.get(0);
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }
    }
}
