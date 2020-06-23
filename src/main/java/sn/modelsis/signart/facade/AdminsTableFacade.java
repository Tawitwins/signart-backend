package sn.modelsis.signart.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import sn.modelsis.signart.AdminsTable;
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

}
