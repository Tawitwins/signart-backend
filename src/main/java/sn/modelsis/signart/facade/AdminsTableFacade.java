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
public class AdminsTableFacade extends AbstractFacade<AdminsTable> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    public AdminsTableFacade() {
        super(AdminsTable.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

//    /**
//     * Retourne la liste des clients d'un artiste. Se base sur les commandes
//     * faites par un client
//     * @param idUser
//     * @return
//     * @throws SignArtException
//     */
//    public List<Admin> findByIdUser(Integer idUser) throws SignArtException {
//        try {
//            final TypedQuery<Admin> query = getEntityManager().createNamedQuery("Client.findByIdUser",
//                    Admin.class);
//            query.setParameter("idUser", idUser);
//            return query.getResultList();
//        } catch (Exception e) {
//            throw new SignArtException(e.getMessage(), e);
//        }
//    }

    /**
     * Retourne le client en fonction de l'id user
     *
     * @param idUser
     * @return
     * @throws SignArtException
     */
    public AdminsTable findByIdUser(Integer idUser) throws SignArtException {
        try {
            final TypedQuery<AdminsTable> query = getEntityManager().createNamedQuery("AdminsTable.findByIdUser",
                    AdminsTable.class);
            query.setParameter("idUser", idUser);
            //query.setMaxResults(1);
            final List<AdminsTable> admins = query.getResultList();
            if (admins.isEmpty()) {
                return null;
            }
            return admins.get(0);
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }
    }

    public AdminsTable findByUserAdvanced(Integer idUser) throws SignArtException {
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<AdminsTable> cq = cb.createQuery(AdminsTable.class);

            javax.persistence.criteria.Root<AdminsTable> order = cq.from(AdminsTable.class);
            Join<AdminsTable, Utilisateur> clientUser = order.join(AdminsTable_.idUser);
            cq.where(cb.and(cb.equal(clientUser.get(Utilisateur_.id), idUser)));
            TypedQuery<AdminsTable> q = getEntityManager().createQuery(cq);

            List<AdminsTable> list = q.getResultList();

            if (list != null && !list.isEmpty()) {
                return list.get(0);
            }

            return null;
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }
    }
}
