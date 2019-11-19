package sn.modelsis.signart.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import sn.modelsis.signart.ModeLivraison;

/**
 *
 * @author SNLOM
 */
@Stateless
public class ModeLivraisonFacade extends AbstractFacade<ModeLivraison> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    public ModeLivraisonFacade() {
        super(ModeLivraison.class);
    }

    /**
     * Retourne la ligne correspondant au libellé
     *
     * @param libelle
     * @return
     */
    public ModeLivraison findByLibelle(String libelle) {
        final TypedQuery<ModeLivraison> query = getEntityManager().createNamedQuery("ModeLivraison.findByLibelle",
                ModeLivraison.class);
        query.setParameter("libelle", libelle);
        List<ModeLivraison> list = query.getResultList();
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
    public ModeLivraison findByCode(String code) {
        final TypedQuery<ModeLivraison> query = getEntityManager().createNamedQuery("ModeLivraison.findByCode",
                ModeLivraison.class);
        query.setParameter("code", code);
        List<ModeLivraison> list = query.getResultList();
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
