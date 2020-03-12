package sn.modelsis.signart.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import sn.modelsis.signart.Utilisateur;

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
        query.setMaxResults(1);
        final List<Utilisateur> users = query.getResultList();
        if (users.isEmpty()) {
            return null;
        }
        return users.get(0);
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
        query.setMaxResults(1);
        final List<Utilisateur> users = query.getResultList();
        if (users.isEmpty()) {
            return null;
        }
        return users.get(0);
    }

}
