package sn.modelsis.signart.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import sn.modelsis.signart.Client;
import sn.modelsis.signart.Client_;
import sn.modelsis.signart.Panier;
import sn.modelsis.signart.Panier_;

/**
 *
 * @author SNLOM
 */
@Stateless
public class PanierFacade extends AbstractFacade<Panier> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    public PanierFacade() {
        super(Panier.class);
    }
    
    public Panier findByIdClient(Integer id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Panier> cq = cb.createQuery(Panier.class);
        javax.persistence.criteria.Root<Panier> oeuv = cq.from(Panier.class);
        Join<Panier, Client> client = oeuv.join(Panier_.idClient);
        cq.where(cb.and(cb.equal(client.get(Client_.id), id)));

        TypedQuery<Panier> q = getEntityManager().createQuery(cq);
        List<Panier> list = q.getResultList();

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
