package sn.modelsis.signart.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import sn.modelsis.signart.EtatPanier;

/**
 *
 * @author SNLOM
 */
@Stateless
public class EtatPanierFacade extends AbstractFacade<EtatPanier> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    public EtatPanierFacade() {
        super(EtatPanier.class);
    }

    /**
     * Retourne la ligne correspondant au libellé
     *
     * @param libelle
     * @return
     */
    public EtatPanier findByLibelle(String libelle) {
        final TypedQuery<EtatPanier> query = getEntityManager().createNamedQuery("EtatPanier.findByLibelle",
                EtatPanier.class);
        query.setParameter("libelle", libelle);
        List<EtatPanier> list = query.getResultList();
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
    public EtatPanier findByCode(String code) {
        final TypedQuery<EtatPanier> query = getEntityManager().createNamedQuery("EtatPanier.findByCode",
                EtatPanier.class);
        query.setParameter("code", code);
        List<EtatPanier> list = query.getResultList();
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
