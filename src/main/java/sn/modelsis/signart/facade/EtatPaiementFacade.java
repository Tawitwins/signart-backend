package sn.modelsis.signart.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import sn.modelsis.signart.EtatPaiement;

/**
 *
 * @author SNLOM
 */
@Stateless
public class EtatPaiementFacade extends AbstractFacade<EtatPaiement> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    public EtatPaiementFacade() {
        super(EtatPaiement.class);
    }

    /**
     * Retourne la ligne correspondant au libellé
     *
     * @param libelle
     * @return
     */
    public EtatPaiement findByLibelle(String libelle) {
        final TypedQuery<EtatPaiement> query = getEntityManager().createNamedQuery("EtatPaiement.findByLibelle",
                EtatPaiement.class);
        query.setParameter("libelle", libelle);
        List<EtatPaiement> list = query.getResultList();
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
    public EtatPaiement findByCode(String code) {
        final TypedQuery<EtatPaiement> query = getEntityManager().createNamedQuery("EtatPaiement.findByCode",
                EtatPaiement.class);
        query.setParameter("code", code);
        List<EtatPaiement> list = query.getResultList();
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
    public EtatPaiement findById(Integer id) {
        final TypedQuery<EtatPaiement> query = getEntityManager().createNamedQuery("EtatPaiement.findById",
                EtatPaiement.class);
        query.setParameter("id", id);
        List<EtatPaiement> list = query.getResultList();
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
