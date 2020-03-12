package sn.modelsis.signart.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import sn.modelsis.signart.MarquageArtiste;
import sn.modelsis.signart.Visiteur;
import sn.modelsis.signart.exception.SignArtException;

/**
 *
 * @author SNLOM
 */
@Stateless
public class MarquageArtisteFacade extends AbstractFacade<MarquageArtiste> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    public MarquageArtisteFacade() {
        super(MarquageArtiste.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     * 
     * @param codeTypeMarquage
     * @param idClient
     * @param idArtiste
     * @return
     * @throws SignArtException 
     */
    public List<MarquageArtiste> findMarqueByClient(String codeTypeMarquage, Integer idClient, Integer idArtiste) throws SignArtException {
        try {
            final TypedQuery<MarquageArtiste> query = getEntityManager().createNamedQuery("MarquageArtiste.findMarqueByClient",
                    MarquageArtiste.class);
            query.setParameter("idClient", idClient);
            query.setParameter("idArtiste", idArtiste);
            query.setParameter("codeTypeMarquage", codeTypeMarquage);
            return query.getResultList();
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }
    }
    
    /**
     * 
     * @param codeTypeMarquage
     * @param idClient
     * @param idArtiste
     * @return
     * @throws SignArtException 
     */
    public MarquageArtiste findMarqueByClientAndArtiste(String codeTypeMarquage, Integer idClient, Integer idArtiste) throws SignArtException {
        try {
            final TypedQuery<MarquageArtiste> query = getEntityManager().createNamedQuery("MarquageArtiste.findMarqueByClientAndArtiste",
                    MarquageArtiste.class);
            query.setParameter("idClient", idClient);
            query.setParameter("idArtiste", idArtiste);
            query.setParameter("codeTypeMarquage", codeTypeMarquage);
            query.setMaxResults(1);
            final List<MarquageArtiste> list = query.getResultList();
            if (list.isEmpty()) {
                return null;
            }
            return list.get(0);
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }

    }
    
    public MarquageArtiste findMarqueByVisiteurAndArtiste(String codeTypeMarquage, Integer idVisiteur, Integer idArtiste) throws SignArtException {
        try {
            final TypedQuery<MarquageArtiste> query = getEntityManager().createNamedQuery("MarquageArtiste.findMarqueByVisiteurAndArtiste",
                    MarquageArtiste.class);
            query.setParameter("idVisiteur", idVisiteur);
            query.setParameter("idArtiste", idArtiste);
            query.setParameter("codeTypeMarquage", codeTypeMarquage);
            query.setMaxResults(1);
            final List<MarquageArtiste> list = query.getResultList();
            if (list.isEmpty()) {
                return null;
            }
            return list.get(0);
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }

    }
     public MarquageArtiste findMarqueById(Integer idMarquageArtiste) throws SignArtException {
        try {
            final TypedQuery<MarquageArtiste> query = getEntityManager().createNamedQuery("MarquageArtiste.findById",
                    MarquageArtiste.class);
            query.setParameter("id", idMarquageArtiste);
            query.setMaxResults(1);
            final List<MarquageArtiste> list = query.getResultList();
            if (list.isEmpty()) {
                return null;
            }
            return list.get(0);
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }

    }
    
     /*public MarquageArtiste findMarqueByClientAndArtiste(String codeTypeMarquage, Integer idClient, Integer idArtiste) throws SignArtException {
        try {
            final TypedQuery<MarquageArtiste> query = getEntityManager().createNamedQuery("MarquageArtiste.findMarqueByClientAndArtiste",
                    MarquageArtiste.class);
            query.setParameter("idClient", idClient);
            query.setParameter("idArtiste", idArtiste);
            query.setParameter("codeTypeMarquage", codeTypeMarquage);
            query.setMaxResults(1);
            final List<MarquageArtiste> list = query.getResultList();
            Visiteur visiteur = VisiteurFacade.find(idArtiste);
            if (list.isEmpty() && visiteur != null) {
                return null;
            }
            return list.get(0)==null??;
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }

    }*/

}
