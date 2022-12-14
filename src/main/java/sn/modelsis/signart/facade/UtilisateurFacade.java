package sn.modelsis.signart.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;

import sn.modelsis.signart.AdminsTable;
import sn.modelsis.signart.AdminsTable_;
import sn.modelsis.signart.Utilisateur;
import sn.modelsis.signart.Utilisateur_;
import sn.modelsis.signart.exception.SignArtException;

/**
 *
 * @author SNLOM
 */
@Stateless
public class UtilisateurFacade extends AbstractFacade<Utilisateur> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    public UtilisateurFacade() {
        super(Utilisateur.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     * Find user by mail
     *
     * @param mail
     * @return found user
     */
    public Utilisateur findByMail(final String mail) {
        final TypedQuery<Utilisateur> query = (TypedQuery<Utilisateur>) em.createNamedQuery(Utilisateur.FIND_BY_MAIL, Utilisateur.class);
        query.setParameter("mail", mail);
        //query.setMaxResults(1);
        final List<Utilisateur> users = query.getResultList();
        if (users.isEmpty()) {
            return null;
        }
        return users.get(0);
    }
  /**  public Utilisateur findByMailAdvanced(final String mail) {
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Utilisateur> cq = cb.createQuery(Utilisateur.class);

            javax.persistence.criteria.Root<AdminsTable> order = cq.from(AdminsTable.class);
            Join<Utilisateur, Utilisateur> clientUser = order.join(Utilisateur_.mail);
            cq.where(cb.and(cb.equal(clientUser.get(Utilisateur_.id), mail)));
            TypedQuery<Utilisateur> q = getEntityManager().createQuery(cq);

            List<Utilisateur> list = q.getResultList();

            if (list != null && !list.isEmpty()) {
                return list.get(0);
            }

            return null;
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }
    }
    
    /**
     * Find user by id
     *
     * @param id
     * @return found user
     */
    public Utilisateur findById(final int id) {
        final TypedQuery<Utilisateur> query = (TypedQuery<Utilisateur>) em.createNamedQuery(Utilisateur.FIND_BY_ID, Utilisateur.class);
        query.setParameter("id", id);
        //query.setMaxResults(1);
        final List<Utilisateur> users = query.getResultList();
        if (users.isEmpty()) {
            return null;
        }
        System.out.println(users.get(0));
        return users.get(0);
    }

}
