package sn.modelsis.signart.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import sn.modelsis.signart.EtatLigneCommande;

/**
 *
 * @author SNLOM
 */
@Stateless
public class EtatLigneCommandeFacade extends AbstractFacade<EtatLigneCommande> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    public EtatLigneCommandeFacade() {
        super(EtatLigneCommande.class);
    }

    /**
     * Retourne la ligne correspondant au libellé
     *
     * @param libelle
     * @return
     */
    public EtatLigneCommande findByLibelle(String libelle) {
        final TypedQuery<EtatLigneCommande> query = getEntityManager().createNamedQuery("EtatLigneCommande.findByLibelle",
                EtatLigneCommande.class);
        query.setParameter("libelle", libelle);
        List<EtatLigneCommande> list = query.getResultList();
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
    public EtatLigneCommande findByCode(String code) {
        final TypedQuery<EtatLigneCommande> query = getEntityManager().createNamedQuery("EtatLigneCommande.findByCode",
                EtatLigneCommande.class);
        query.setParameter("code", code);
        List<EtatLigneCommande> list = query.getResultList();
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
