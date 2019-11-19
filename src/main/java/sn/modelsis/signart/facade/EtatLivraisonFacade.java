package sn.modelsis.signart.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import sn.modelsis.signart.EtatLivraison;

/**
 *
 * @author SNLOM
 */
@Stateless
public class EtatLivraisonFacade extends AbstractFacade<EtatLivraison> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    public EtatLivraisonFacade() {
        super(EtatLivraison.class);
    }

    /**
     * Retourne la ligne correspondant au libellé
     *
     * @param libelle
     * @return
     */
    public EtatLivraison findByLibelle(String libelle) {
        final TypedQuery<EtatLivraison> query = getEntityManager().createNamedQuery("EtatLivraison.findByLibelle",
                EtatLivraison.class);
        query.setParameter("libelle", libelle);
        List<EtatLivraison> list = query.getResultList();
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
    public EtatLivraison findByCode(String code) {
        final TypedQuery<EtatLivraison> query = getEntityManager().createNamedQuery("EtatLivraison.findByCode",
                EtatLivraison.class);
        query.setParameter("code", code);
        List<EtatLivraison> list = query.getResultList();
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
