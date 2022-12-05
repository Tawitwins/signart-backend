package sn.modelsis.signart.facade;

import sn.modelsis.signart.EtatClient;
import sn.modelsis.signart.EvenementSignart;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 *
 * @author SNMBENGUEO
 */
@Stateless
public class EvenementSignartFacade extends AbstractFacade<EvenementSignart> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    public EvenementSignartFacade() {
        super(EvenementSignart.class);
    }

    /**
     * Retourne la ligne correspondant à l'artiste
     *
     * @param idArtiste
     * @return
     */
    public EvenementSignart findByArtiste(Integer idArtiste) {

        //try {
        final TypedQuery<EvenementSignart> query = getEntityManager().createNamedQuery("EvenementSignart.findByArtiste",
                EvenementSignart.class);
        query.setParameter("idArtiste", idArtiste);
        List<EvenementSignart> list = query.getResultList();
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
        /*} catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }*/

    }
    public EvenementSignart findByAdmin(Integer idAdmin) {

        //try {
        final TypedQuery<EvenementSignart> query = getEntityManager().createNamedQuery("EvenementSignart.findByAdmin",
                EvenementSignart.class);
        query.setParameter("idAdminUser", idAdmin);
        List<EvenementSignart> list = query.getResultList();
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
        /*} catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }*/

    }
    public EvenementSignart findById(Integer id) {

        //try {
        final TypedQuery<EvenementSignart> query = getEntityManager().createNamedQuery("EvenementSignart.findById",
                EvenementSignart.class);
        query.setParameter("id", id);
        List<EvenementSignart> list = query.getResultList();
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
        /*} catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }*/

    }
    /**
     *
     * @param code
     * @return
     */
    public EvenementSignart findByCodeEvenement(String code) {

        //try {
        final TypedQuery<EvenementSignart> query = getEntityManager().createNamedQuery("EvenementSignart.findByCodeEvenement",
                EvenementSignart.class);
        query.setParameter("codeEvenement", code);
        List<EvenementSignart> list = query.getResultList();
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
        /*} catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }*/

    }
    public EvenementSignart findByStatus(String status) {

        //try {
        final TypedQuery<EvenementSignart> query = getEntityManager().createNamedQuery("EvenementSignart.findByStatus",
                EvenementSignart.class);
        query.setParameter("status", status);
        List<EvenementSignart> list = query.getResultList();
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
        /*} catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }*/

    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
