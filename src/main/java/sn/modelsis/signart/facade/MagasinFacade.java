package sn.modelsis.signart.facade;

import sn.modelsis.signart.Magasin;
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
public class MagasinFacade extends AbstractFacade<Magasin> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    EntityTransaction tx;

    public MagasinFacade() {
        super(Magasin.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }


    
     public Magasin findById(Integer idMagasin) throws SignArtException {
        try {
            final TypedQuery<Magasin> query = getEntityManager().createNamedQuery("Magasin.findById",
                    Magasin.class);
            query.setParameter("id", idMagasin);
            return query.getSingleResult();
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }

    }


    /**
     * Retourne l'Agent en fonction de l'id user
     *
     * @param nom
     * @return
     * @throws SignArtException
     */
    public Magasin findByNom(String nom) throws SignArtException {
        try {
            final TypedQuery<Magasin> query = getEntityManager().createNamedQuery("Magasin.findByNom",
                    Magasin.class);
            query.setParameter("nom", nom);
            //query.setMaxResults(1);
            final List<Magasin> users = query.getResultList();
            if (users.isEmpty()) {
                return null;
            }
            return users.get(0);
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }
    }


}
