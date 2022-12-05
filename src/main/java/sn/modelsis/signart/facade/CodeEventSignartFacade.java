package sn.modelsis.signart.facade;

import sn.modelsis.signart.CodeEventSignart;
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
public class CodeEventSignartFacade extends AbstractFacade<CodeEventSignart> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    public CodeEventSignartFacade() {
        super(CodeEventSignart.class);
    }

    /**
     * Retourne la ligne correspondant à l'artiste
     *
     * @param idEvent
     * @return
     */
    public CodeEventSignart findByEvenement(Integer idEvent) {

        //try {
        final TypedQuery<CodeEventSignart> query = getEntityManager().createNamedQuery("CodeEventSignart.findByEvenement",
                CodeEventSignart.class);
        query.setParameter("idEvent", idEvent);
        List<CodeEventSignart> list = query.getResultList();
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
        /*} catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }*/

    }

    public CodeEventSignart findById(Integer id) {

        //try {
        final TypedQuery<CodeEventSignart> query = getEntityManager().createNamedQuery("CodeEventSignart.findById",
                CodeEventSignart.class);
        query.setParameter("id", id);
        List<CodeEventSignart> list = query.getResultList();
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
    public CodeEventSignart findByCode(String code) {

        //try {
        final TypedQuery<CodeEventSignart> query = getEntityManager().createNamedQuery("CodeEventSignart.findByCode",
                CodeEventSignart.class);
        query.setParameter("code", code);
        List<CodeEventSignart> list = query.getResultList();
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
        /*} catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }*/

    }
    public CodeEventSignart findByStatus(Boolean status) {

        //try {
        final TypedQuery<CodeEventSignart> query = getEntityManager().createNamedQuery("CodeEventSignart.findByStatus",
                CodeEventSignart.class);
        query.setParameter("status", status);
        List<CodeEventSignart> list = query.getResultList();
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
