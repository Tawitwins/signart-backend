package sn.modelsis.signart.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import sn.modelsis.signart.EtatClient;

/**
 *
 * @author SNLOM
 */
@Stateless
public class EtatClientFacade extends AbstractFacade<EtatClient> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    public EtatClientFacade() {
        super(EtatClient.class);
    }

    /**
     * Retourne la ligne correspondant au libellé
     *
     * @param libelle
     * @return
     */
    public EtatClient findByLibelle(String libelle) {

        //try {
        final TypedQuery<EtatClient> query = getEntityManager().createNamedQuery("EtatClient.findByLibelle",
                EtatClient.class);
        query.setParameter("libelle", libelle);
        List<EtatClient> list = query.getResultList();
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
    public EtatClient findByCode(String code) {

        final TypedQuery<EtatClient> query = getEntityManager().createNamedQuery("EtatClient.findByCode",
                EtatClient.class);
        query.setParameter("code", code);
        List<EtatClient> list = query.getResultList();
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
