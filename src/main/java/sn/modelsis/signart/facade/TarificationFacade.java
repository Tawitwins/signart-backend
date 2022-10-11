package sn.modelsis.signart.facade;

import sn.modelsis.signart.ServiceLivraison;
import sn.modelsis.signart.Tarification;
import sn.modelsis.signart.exception.SignArtException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 *
 * @author SNMBENGUEO
 */
@Stateless
public class TarificationFacade extends AbstractFacade<Tarification> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    EntityTransaction tx;

    public TarificationFacade() {
        super(Tarification.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }


    
     public Tarification findById(Integer idTarification) throws SignArtException {
        try {
            final TypedQuery<Tarification> query = getEntityManager().createNamedQuery("Tarification.findById",
                    Tarification.class);
            query.setParameter("id", idTarification);
            return query.getSingleResult();
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }

    }


    /**
     * Retourne la Tarification en fonction de la zone
     *
     * @param zone
     * @return
     * @throws SignArtException
     */
    public Tarification findByZone(String zone) throws SignArtException {
        try {
            final TypedQuery<Tarification> query = getEntityManager().createNamedQuery("Tarification.findByZone",
                    Tarification.class);
            query.setParameter("zone", zone);
            query.setMaxResults(1);
            final List<Tarification> users = query.getResultList();
            if (users.isEmpty()) {
                return null;
            }
            return users.get(0);
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }
    }

    public Tarification findByDistance(Integer distance) throws SignArtException {
        try {
            final TypedQuery<Tarification> query = getEntityManager().createNamedQuery("Tarification.findByDistance",
                    Tarification.class);
            query.setParameter("distance", distance);
            query.setMaxResults(1);
            final List<Tarification> users = query.getResultList();
            if (users.isEmpty()) {
                return null;
            }
            return users.get(0);
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }
    }


}
