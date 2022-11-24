package sn.modelsis.signart.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;

import sn.modelsis.signart.*;
import sn.modelsis.signart.exception.SignArtException;

/**
 *
 * @author SNLOM
 */
@Stateless
public class ArtisteFacade extends AbstractFacade<Artiste> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;
    
    EntityTransaction tx;

    public ArtisteFacade() {
        super(Artiste.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     * Retourne la liste des artistes marqués(typeMarquage donné) par un client
     * donné
     *
     * @param codeTypeMarquage
     * @param idClient
     * @return
     * @throws sn.modelsis.signart.exception.SignArtException
     */
    public List<Artiste> findMarqueByClient(String codeTypeMarquage, Integer idClient) throws SignArtException {
        try {
            final TypedQuery<Artiste> query = getEntityManager().createNamedQuery("MarquageArtiste.findMarqueByClient",
                    Artiste.class);
            query.setParameter("idClient", idClient);
            query.setParameter("codeTypeMarquage", codeTypeMarquage);
            return query.getResultList();
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }

    }
    
     public Artiste findById(Integer idArtiste) throws SignArtException {
        try {
            final TypedQuery<Artiste> query = getEntityManager().createNamedQuery("Artiste.findById",
                    Artiste.class);
            query.setParameter("id", idArtiste);
            return query.getSingleResult();
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }

    }

    /**
     *
     * @param codeTypeMarquage
     * @return
     * @throws SignArtException
     */
    public Long countMarqueArtiste(String codeTypeMarquage) throws SignArtException {
        try {
            final TypedQuery<Long> query = getEntityManager().createNamedQuery("MarquageArtiste.countMarqueByClient",
                    Long.class);
            query.setParameter("codeTypeMarquage", codeTypeMarquage);
            return query.getSingleResult();
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }
    }
    
    
    public Long countMarqueArtisteFan(String codeTypeMarquage,Integer idArtiste) throws SignArtException {
        try {
            final TypedQuery<MarquageArtiste> query = getEntityManager().createNamedQuery("MarquageArtiste.countMarqueByArtiste",
                    MarquageArtiste.class);
            query.setParameter("codeTypeMarquage", codeTypeMarquage);
            query.setParameter("idArtiste", idArtiste);
             final List<MarquageArtiste> ma = query.getResultList();
            return (long) ma.size();
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }
    }
    
    public Artiste findByIdentite(String identite) throws SignArtException {
        try {
            final TypedQuery<Artiste> query = getEntityManager().createNamedQuery("Artiste.findByIdentite",
                    Artiste.class);
            query.setParameter("identite", identite);
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

    /**
     * Retourne l'artiste en fonction de l'id user
     *
     * @param idUser
     * @return
     * @throws SignArtException
     */
    public Artiste findByUser(Integer idUser) throws SignArtException {
        try {
            final TypedQuery<Artiste> query = getEntityManager().createNamedQuery("Artiste.findByIdUser",
                    Artiste.class);
            query.setParameter("idUser", idUser);
            final Artiste art = query.getSingleResult();

            return art;
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }
    }
    public Artiste findByUserAdvanced(Integer idUser) throws SignArtException {
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Artiste> cq = cb.createQuery(Artiste.class);

            javax.persistence.criteria.Root<Artiste> order = cq.from(Artiste.class);
            Join<Artiste, Utilisateur> artisteUser = order.join(Artiste_.idUser);
            cq.where(cb.and(cb.equal(artisteUser.get(Utilisateur_.id), idUser)));
            TypedQuery<Artiste> q = getEntityManager().createQuery(cq);

            List<Artiste> list = q.getResultList();

            if (list != null && !list.isEmpty()) {
                return list.get(0);
            }

            return null;
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }
    }
    
   /* public Artiste updateProfil(Artiste artiste) throws SignArtException {
        try {
            final TypedQuery<Artiste> query = getEntityManager().createNamedQuery("Artiste.updateProfil",
                    Artiste.class);
            query.setParameter("nom", artiste.getNom());
            query.setParameter("prenom", artiste.getPrenom());
            query.setParameter("surnom", artiste.getSurnom());
            query.setParameter("telephone", artiste.getTelephone());
            query.setParameter("email", artiste.getEmail());
            query.setParameter("adresse", artiste.getAdresse());
            query.setParameter("ville", artiste.getVille());
            query.setParameter("pays", artiste.getIdPays());
            query.setParameter("id", artiste.getId());
            //query.setMaxResults(1);
            final List<Artiste> users = query.getResultList();
            if (users.isEmpty()) {
                return null;
            }
            return users.get(0);
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }
    }*/
    
    public void updateNom(Artiste artiste) throws SignArtException {
       
            final TypedQuery<Artiste> query = getEntityManager().createNamedQuery("Artiste.findById",
                    Artiste.class);
            query.setParameter("id", artiste.getId());
            Artiste art = query.getSingleResult();
            art.setNom(artiste.getNom());
            tx = em.getTransaction();
            tx.begin();
            em.merge(art);
            tx.commit();
             
        
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
}
