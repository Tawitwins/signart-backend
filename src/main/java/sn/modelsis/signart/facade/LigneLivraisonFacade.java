package sn.modelsis.signart.facade;

import sn.modelsis.signart.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import java.util.List;


@Stateless
public class LigneLivraisonFacade extends AbstractFacade<LigneLivraison> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    public LigneLivraisonFacade() {
        super(LigneLivraison.class);
    }
    public LigneLivraison findByLigneCommande(Integer idLC) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<LigneLivraison> cq = cb.createQuery(LigneLivraison.class);

        javax.persistence.criteria.Root<LigneLivraison> order = cq.from(LigneLivraison.class);
        Join<LigneLivraison, LigneCommande> lignLivCom = order.join(LigneLivraison_.idLigneCommande);
        cq.where(cb.and(cb.equal(lignLivCom.get(LigneCommande_.id), idLC)));
        TypedQuery<LigneLivraison> q = getEntityManager().createQuery(cq);

        List<LigneLivraison> list = q.getResultList();

        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }

        return null;
/*        final TypedQuery<LigneLivraison> query = getEntityManager().createNamedQuery("LigneLivraison.findByLigneCommande", LigneLivraison.class);
        query.setParameter("idLC", idLC);
        List<LigneLivraison> list = query.getResultList();
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;*/
    }
    public LigneLivraison findById(Integer id) {
        final TypedQuery<LigneLivraison> query = getEntityManager().createNamedQuery("LigneLivraison.findById", LigneLivraison.class);
        query.setParameter("id", id);
        List<LigneLivraison> list = query.getResultList();
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
