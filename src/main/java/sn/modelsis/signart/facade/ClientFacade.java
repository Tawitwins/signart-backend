package sn.modelsis.signart.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
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
public class ClientFacade extends AbstractFacade<Client> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    public ClientFacade() {
        super(Client.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     * Retourne la liste des clients d'un artiste. Se base sur les commandes
     * faites par un client
     *
     * @param idArtiste
     * @return
     * @throws SignArtException
     */
    public List<Client> findByArtiste(Integer idArtiste) throws SignArtException {
        try {
            final TypedQuery<Client> query = getEntityManager().createNamedQuery("Client.findClientArtiste",
                    Client.class);
            query.setParameter("idArtiste", idArtiste);
            return query.getResultList();
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }
    }

    /**
     * Retourne le client en fonction de l'id user
     *
     * @param idUser
     * @return
     * @throws SignArtException
     */
    public Client findByUser(Integer idUser) throws SignArtException {
        try {
            final TypedQuery<Client> query = getEntityManager().createNamedQuery("Client.findByIdUser",
                    Client.class);
            query.setParameter("idUser", idUser);
            query.setMaxResults(1);
            final List<Client> users = query.getResultList();
            if (users.isEmpty()) {
                return null;
            }
            return users.get(0);
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }
    }
    public Client findByUserAdvanced(Integer idUser) throws SignArtException {
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Client> cq = cb.createQuery(Client.class);

            javax.persistence.criteria.Root<Client> order = cq.from(Client.class);
            Join<Client, Utilisateur> clientUser = order.join(Client_.idUser);
            cq.where(cb.and(cb.equal(clientUser.get(Utilisateur_.id), idUser)));
            TypedQuery<Client> q = getEntityManager().createQuery(cq);

            List<Client> list = q.getResultList();

            if (list != null && !list.isEmpty()) {
                return list.get(0);
            }

            return null;
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }
    }

    public Client findById(Integer id) throws SignArtException{
        try {
            final TypedQuery<Client> query = getEntityManager().createNamedQuery("Client.findById",
                    Client.class);
            query.setParameter("id", id);
            query.setMaxResults(1);
            final List<Client> users = query.getResultList();
            if (users.isEmpty()) {
                return null;
            }
            return users.get(0);
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }
    }
}
