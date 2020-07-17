package sn.modelsis.signart.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import sn.modelsis.signart.Email;
import sn.modelsis.signart.exception.SignArtException;

/**
 *
 * @author SNLOM
 */
@Stateless
public class EmailFacade extends AbstractFacade<Email>  {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    public EmailFacade() {
        super(Email.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<Email> findById(Integer id) throws SignArtException {
        try {
            final TypedQuery<Email> query = getEntityManager().createNamedQuery("Email.findById",
                    Email.class);
            query.setParameter("id", id);
            return query.getResultList();
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }
    }
    /**
     * Retourne la liste des clients d'un artiste. Se base sur les commandes
     * faites par un client
     *
     * @param to
     * @return
     * @throws SignArtException
     */
    public List<Email> findByTo(String to) throws SignArtException {
        try {
            final TypedQuery<Email> query = getEntityManager().createNamedQuery("Email.findByTo",
                    Email.class);
            query.setParameter("to", to);
            return query.getResultList();
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }
    }

    /**
     *
     * @param to
     * @return
     * @throws SignArtException
     */
    public Email findByToLast(String to) throws SignArtException {
        try {
            final TypedQuery<Email> query = getEntityManager().createNamedQuery("Email.findByTo",
                    Email.class);
            query.setParameter("to", to);
            return query.getResultList().get(query.getResultList().size()-1);
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
    /*public Client findByUser(Integer idUser) throws SignArtException {
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
    }*/

}
