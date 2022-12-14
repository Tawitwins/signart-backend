package sn.modelsis.signart.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import sn.modelsis.signart.Utilisateur;
import sn.modelsis.signart.exception.SignArtException;
import sn.modelsis.signart.Artiste;
import sn.modelsis.signart.Biographie;
import sn.modelsis.signart.Souscription;

/**
 *
 * @author SNLOM
 */
@Stateless
public class SouscriptionFacade extends AbstractFacade<Souscription> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    public SouscriptionFacade() {
        super(Souscription.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     * Retourne la liste des artistes marqués(typeMarquage donné) par un client
     * donné
     *
     * @param
     * @param
     * @return
     * @throws sn.modelsis.signart.exception.SignArtException
     */
    
     public Souscription findById(Integer idSouscription) throws SignArtException {
        try {
            final TypedQuery<Souscription> query = getEntityManager().createNamedQuery("Souscription.findById",
                    Souscription.class);
            query.setParameter("id", idSouscription);
            return query.getSingleResult();
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }

    }

 

    /**
     *
     * @param idArtiste
     * @return
     * @throws SignArtException
     */
    public Long countOeuvre(Integer idArtiste) throws SignArtException {
        try {
            final TypedQuery<Long> query = getEntityManager().createNamedQuery("Oeuvre.countOeuvreByArtiste",
                    Long.class);
            query.setParameter("idArtiste", idArtiste);
            return query.getSingleResult();
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }
    }

    
     public Biographie findBiographieByArtiste(Integer id) throws SignArtException {
        try {
            final TypedQuery<Artiste> query = getEntityManager().createNamedQuery("Artiste.findById",
                    Artiste.class);
            query.setParameter("id", id);
            //query.setMaxResults(1);
            final List<Artiste> users = query.getResultList();
            if (users.isEmpty()) {
                return null;
            }
            return users.get(0).getIdBiographie();
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }
    }

/**    public Souscription findByEmail(String email) throws SignArtException {
        try {
            final TypedQuery<Souscription> query = getEntityManager().createNamedQuery("Souscription.findByEmail",
                    Souscription.class);
            query.setParameter("email", email);
            return query.getSingleResult();
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }
    }**/

    public Souscription findByEmail(String mail) {
        final TypedQuery<Souscription> query = (TypedQuery<Souscription>) em.createNamedQuery("Souscription.findByEmail", Souscription.class);
        query.setParameter("email", mail);
        final List<Souscription> souscriptions = query.getResultList();
        if (souscriptions.isEmpty()) {
            return null;
        }
        return souscriptions.get(0);
    }
}
