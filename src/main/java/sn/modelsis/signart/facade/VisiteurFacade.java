package sn.modelsis.signart.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import sn.modelsis.signart.Visiteur;
import sn.modelsis.signart.exception.SignArtException;

/**
 *
 * @author SNLOM
 */
@Stateless
public class VisiteurFacade extends AbstractFacade<Visiteur>  {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    public VisiteurFacade() {
        super(Visiteur.class);
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
    public List<Visiteur> findByArtiste() throws SignArtException {
        try {
            final TypedQuery<Visiteur> query = getEntityManager().createNamedQuery("Visiteur.findAll",
                    Visiteur.class);
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
