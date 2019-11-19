package sn.modelsis.signart.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import sn.modelsis.signart.ArtisteFonction;
import sn.modelsis.signart.ArtisteFormation;
import sn.modelsis.signart.exception.SignArtException;

/**
 *
 * @author SNLOM
 */
@Stateless
public class ArtisteFonctionFacade extends AbstractFacade<ArtisteFonction> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    public ArtisteFonctionFacade() {
        super(ArtisteFonction.class);
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
    public List<ArtisteFonction> findByArtiste(Integer idArtiste) throws SignArtException {
        try {
            final TypedQuery<ArtisteFonction> query = getEntityManager().createNamedQuery("ArtisteFonction.findByIdArtiste",
                    ArtisteFonction.class);
            query.setParameter("idArtiste", idArtiste);
            return query.getResultList();
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }

    }
}
