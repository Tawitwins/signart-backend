package sn.modelsis.signart.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import sn.modelsis.signart.ModePaiement;

/**
 *
 * @author SNLOM
 */
@Stateless
public class ModePaiementFacade extends AbstractFacade<ModePaiement> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    public ModePaiementFacade() {
        super(ModePaiement.class);
    }

    /**
     * Retourne la ligne correspondant au libellé
     *
     * @param libelle
     * @return
     */
    public ModePaiement findByLibelle(String libelle) {
        final TypedQuery<ModePaiement> query = getEntityManager().createNamedQuery("ModePaiement.findByLibelle",
                ModePaiement.class);
        query.setParameter("libelle", libelle);
        List<ModePaiement> list = query.getResultList();
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    /**
     *
     * @param code
     * @return
     */
    public ModePaiement findByCode(String code) {
        final TypedQuery<ModePaiement> query = getEntityManager().createNamedQuery("ModePaiement.findByCode",
                ModePaiement.class);
        query.setParameter("code", code);
        List<ModePaiement> list = query.getResultList();
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    /**
     *
     * @param id
     * @return
     */
    public ModePaiement findById(Integer id) {
        final TypedQuery<ModePaiement> query = getEntityManager().createNamedQuery("ModePaiement.findById",
                ModePaiement.class);
        query.setParameter("id", id);
        List<ModePaiement> list = query.getResultList();
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
