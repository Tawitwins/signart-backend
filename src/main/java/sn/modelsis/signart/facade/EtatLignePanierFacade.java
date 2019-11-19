package sn.modelsis.signart.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import sn.modelsis.signart.EtatLignePanier;

/**
 *
 * @author SNLOM
 */
@Stateless
public class EtatLignePanierFacade extends AbstractFacade<EtatLignePanier> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    public EtatLignePanierFacade() {
        super(EtatLignePanier.class);
    }

    /**
     * Retourne la ligne correspondant au libellé
     *
     * @param libelle
     * @return
     */
    public EtatLignePanier findByLibelle(String libelle) {
        final TypedQuery<EtatLignePanier> query = getEntityManager().createNamedQuery("EtatLignePanier.findByLibelle",
                EtatLignePanier.class);
        query.setParameter("libelle", libelle);
        List<EtatLignePanier> list = query.getResultList();
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
    public EtatLignePanier findByCode(String code) {
        final TypedQuery<EtatLignePanier> query = getEntityManager().createNamedQuery("EtatLignePanier.findByCode",
                EtatLignePanier.class);
        query.setParameter("code", code);
        List<EtatLignePanier> list = query.getResultList();
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
