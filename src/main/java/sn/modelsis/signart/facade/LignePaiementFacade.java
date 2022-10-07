package sn.modelsis.signart.facade;

import sn.modelsis.signart.*;
import sn.modelsis.signart.dto.LignePaiementDto;
import sn.modelsis.signart.exception.SignArtException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import java.util.List;


@Stateless
public class LignePaiementFacade extends AbstractFacade<LignePaiement> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    public LignePaiementFacade() {
        super(LignePaiement.class);
    }
    public LignePaiement findByLigneCommande(Integer idLC) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<LignePaiement> cq = cb.createQuery(LignePaiement.class);

        javax.persistence.criteria.Root<LignePaiement> order = cq.from(LignePaiement.class);
        Join<LignePaiement, LigneCommande> lignPaieCom = order.join(LignePaiement_.idLigneCommande);
        cq.where(cb.and(cb.equal(lignPaieCom.get(LigneCommande_.id), idLC)));
        TypedQuery<LignePaiement> q = getEntityManager().createQuery(cq);

        List<LignePaiement> list = q.getResultList();

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
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LignePaiement findByToken(String tokenPaiement) {
        final TypedQuery<LignePaiement> query = getEntityManager().createNamedQuery("LignePaiement.findByTokenPaiement", LignePaiement.class);
        query.setParameter("tokenPaiement", tokenPaiement);
        List<LignePaiement> list = query.getResultList();
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return  null;
    }

    public LignePaiement findById(Integer id) {
        final TypedQuery<LignePaiement> query = getEntityManager().createNamedQuery("LignePaiement.findById", LignePaiement.class);
        query.setParameter("id", id);
        List<LignePaiement> list = query.getResultList();
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return  null;
    }


}
