package sn.modelsis.signart.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import sn.modelsis.signart.Commande;
import sn.modelsis.signart.Livraison;

import java.util.List;

/**
 *
 * @author SNLOM
 */
@Stateless
public class LivraisonFacade extends AbstractFacade<Livraison> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    public LivraisonFacade() {
        super(Livraison.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }


    public Livraison findById(Integer id) {

        final TypedQuery<Livraison> query = getEntityManager().createNamedQuery("Livraison.findById",
                Livraison.class);
        query.setParameter("id", id);
        List<Livraison> list = query.getResultList();
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }
}
