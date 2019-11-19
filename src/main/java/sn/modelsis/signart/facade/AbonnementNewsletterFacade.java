package sn.modelsis.signart.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import sn.modelsis.signart.AbonnementNewsletter;
import sn.modelsis.signart.exception.SignArtException;

/**
 *
 * @author SNLOM
 */
@Stateless
public class AbonnementNewsletterFacade extends AbstractFacade<AbonnementNewsletter> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    public AbonnementNewsletterFacade() {
        super(AbonnementNewsletter.class);
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
    /*public List<AbonnementNewsletter> findByArtiste(Integer idArtiste) throws SignArtException {
        try {
            final TypedQuery<AbonnementNewsletter> query = getEntityManager().createNamedQuery("AbonnementNewsletter.findByArtiste",
                    AbonnementNewsletter.class);
            query.setParameter("idArtiste", idArtiste);
            return query.getResultList();
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }

    }*/
}
