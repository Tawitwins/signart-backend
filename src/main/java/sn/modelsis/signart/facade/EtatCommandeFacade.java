package sn.modelsis.signart.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import sn.modelsis.signart.EtatCommande;

/**
 *
 * @author SNLOM
 */
@Stateless
public class EtatCommandeFacade extends AbstractFacade<EtatCommande> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    public EtatCommandeFacade() {
        super(EtatCommande.class);
    }

    /**
     * Retourne la ligne correspondant au libellé
     *
     * @param libelle
     * @return
     */
    public EtatCommande findByLibelle(String libelle) {

        //try {
        final TypedQuery<EtatCommande> query = getEntityManager().createNamedQuery("EtatCommande.findByLibelle",
                EtatCommande.class);
        query.setParameter("libelle", libelle);
        List<EtatCommande> list = query.getResultList();
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
    public EtatCommande findByCode(String code) {

        final TypedQuery<EtatCommande> query = getEntityManager().createNamedQuery("EtatCommande.findByCode",
                EtatCommande.class);
        query.setParameter("code", code);
        List<EtatCommande> list = query.getResultList();
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
