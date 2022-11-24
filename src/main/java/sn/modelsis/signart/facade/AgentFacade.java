package sn.modelsis.signart.facade;

import sn.modelsis.signart.Agent;
import sn.modelsis.signart.Agent_;
import sn.modelsis.signart.Utilisateur;
import sn.modelsis.signart.Utilisateur_;
import sn.modelsis.signart.exception.SignArtException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import java.util.List;

/**
 *
 * @author SNMBENGUEO
 */
@Stateless
public class AgentFacade extends AbstractFacade<Agent> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    EntityTransaction tx;

    public AgentFacade() {
        super(Agent.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }


    
     public Agent findById(Integer idAgent) throws SignArtException {
        try {
            final TypedQuery<Agent> query = getEntityManager().createNamedQuery("Agent.findById",
                    Agent.class);
            query.setParameter("id", idAgent);
            return query.getSingleResult();
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }

    }


    /**
     * Retourne l'Agent en fonction de l'id user
     *
     * @param idUser
     * @return
     * @throws SignArtException
     */
    public Agent findByUser(Integer idUser) throws SignArtException {
        try {
            final TypedQuery<Agent> query = getEntityManager().createNamedQuery("Agent.findByIdUser",
                    Agent.class);
            query.setParameter("idUser", idUser);
            //query.setMaxResults(1);
            final List<Agent> users = query.getResultList();
            if (users.isEmpty()) {
                return null;
            }
            return users.get(0);
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }
    } public Agent findByUserAdvanced(Integer idUser) throws SignArtException {
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Agent> cq = cb.createQuery(Agent.class);

            javax.persistence.criteria.Root<Agent> order = cq.from(Agent.class);
            Join<Agent, Utilisateur> clientUser = order.join(Agent_.idUser);
            cq.where(cb.and(cb.equal(clientUser.get(Utilisateur_.id), idUser)));
            TypedQuery<Agent> q = getEntityManager().createQuery(cq);

            List<Agent> list = q.getResultList();

            if (list != null && !list.isEmpty()) {
                return list.get(0);
            }

            return null;
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }
    }
    public List<Agent> findByRole(String role) throws SignArtException {
        try {
            final TypedQuery<Agent> query = getEntityManager().createNamedQuery("Agent.findByRole",
                    Agent.class);
            query.setParameter("role", role);
            //query.setMaxResults(1);
            final List<Agent> users = query.getResultList();
            if (users.isEmpty()) {
                return null;
            }
            return users;
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }
    }


}
