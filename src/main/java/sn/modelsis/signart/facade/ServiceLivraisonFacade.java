package sn.modelsis.signart.facade;

import sn.modelsis.signart.Agent;
import sn.modelsis.signart.ServiceLivraison;
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
public class ServiceLivraisonFacade extends AbstractFacade<ServiceLivraison> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    EntityTransaction tx;

    public ServiceLivraisonFacade() {
        super(ServiceLivraison.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }


    
     public ServiceLivraison findById(Integer idServiceLivraison) throws SignArtException {
        try {
            final TypedQuery<ServiceLivraison> query = getEntityManager().createNamedQuery("ServiceLivraison.findById",
                    ServiceLivraison.class);
            query.setParameter("id", idServiceLivraison);
            return query.getSingleResult();
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }

    }


    /**
     * Retourne le ServiceLivraison en fonction du nom
     *
     * @param nom
     * @return
     * @throws SignArtException
     */
    public ServiceLivraison findByNom(String nom) throws SignArtException {
        try {
            final TypedQuery<ServiceLivraison> query = getEntityManager().createNamedQuery("ServiceLivraison.findByNom",
                    ServiceLivraison.class);
            query.setParameter("nom", nom);
            //query.setMaxResults(1);
            final List<ServiceLivraison> users = query.getResultList();
            if (users.isEmpty()) {
                return null;
            }
            return users.get(0);
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }
    }


}
