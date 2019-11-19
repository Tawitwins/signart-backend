package sn.modelsis.signart.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import sn.modelsis.signart.Fonction;
import sn.modelsis.signart.exception.SignArtException;

/**
 *
 * @author SNLOM
 */
@Stateless
public class FonctionFacade extends AbstractFacade<Fonction> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    public FonctionFacade() {
        super(Fonction.class);
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
    public List<Fonction> findByArtiste(Integer idArtiste) throws SignArtException {
        try {
            final TypedQuery<Fonction> query = getEntityManager().createNamedQuery("Fonction.findByArtiste",
                    Fonction.class);
            query.setParameter("idArtiste", idArtiste);
            return query.getResultList();
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }

    }
    
    public Fonction findById(Integer id) throws SignArtException {
        try {
            final TypedQuery<Fonction> query = getEntityManager().createNamedQuery("Fonction.findById",
                    Fonction.class);
            query.setParameter("id", id);
            query.setMaxResults(1);
            final List<Fonction> fonctions = query.getResultList();
            if (fonctions.isEmpty()) {
                return null;
            }
            return fonctions.get(0);
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }
    }
}
