package sn.modelsis.signart.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import sn.modelsis.signart.PromotionOeuvre;
import sn.modelsis.signart.PromotionOeuvre_;
import sn.modelsis.signart.Oeuvre_;
import sn.modelsis.signart.Oeuvre;

/**
 *
 * @author SNLOM
 */
@Stateless
public class PromotionOeuvreFacade extends AbstractFacade<PromotionOeuvre> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    public PromotionOeuvreFacade() {
        super(PromotionOeuvre.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public List<PromotionOeuvre> findByOeuvre(Integer idOeuvre) {
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<PromotionOeuvre> cq = cb.createQuery(PromotionOeuvre.class);
        javax.persistence.criteria.Root<PromotionOeuvre> rt = cq.from(PromotionOeuvre.class);
        Join<PromotionOeuvre, Oeuvre> oeuv = rt.join(PromotionOeuvre_.idOeuvre);
        cq.where(cb.and(cb.equal(oeuv.get(Oeuvre_.id), idOeuvre)));

        TypedQuery<PromotionOeuvre> q = getEntityManager().createQuery(cq);
        List<PromotionOeuvre> list = q.getResultList();

        if (list != null && !list.isEmpty()) {
            return list;
        }

        return null;

    }
}
