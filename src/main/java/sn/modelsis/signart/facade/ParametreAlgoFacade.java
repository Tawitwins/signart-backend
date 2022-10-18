package sn.modelsis.signart.facade;

import sn.modelsis.signart.*;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import java.util.List;

/**
 *
 * @author SNMBENGUO
 */
@Stateless
public class ParametreAlgoFacade extends AbstractFacade<ParametreAlgo> {

    @Inject
    EtatPanierFacade etatpanierFacade;
    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    public ParametreAlgoFacade() {
        super(ParametreAlgo.class);
    }
    
    public ParametreAlgo findByNiveau(String niveau) {
        final TypedQuery<ParametreAlgo> query = getEntityManager().createNamedQuery("ParametreAlgo.findByNiveau",
                ParametreAlgo.class);
        query.setParameter("niveau", niveau);
        List<ParametreAlgo> list = query.getResultList();
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;

    }public List<ParametreAlgo> findByCode(String codeParametre) {
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<ParametreAlgo> cq = cb.createQuery(ParametreAlgo.class);
        javax.persistence.criteria.Root<ParametreAlgo> pa = cq.from(ParametreAlgo.class);
        Join<ParametreAlgo, CoefficientParametrage> coef = pa.join(ParametreAlgo_.coefficientParam);
        cq.where(cb.and(cb.equal(coef.get(CoefficientParametrage_.codeParametre), codeParametre)));

        TypedQuery<ParametreAlgo> q = getEntityManager().createQuery(cq);
        List<ParametreAlgo> list = q.getResultList();

        if (list != null && !list.isEmpty()) {
            return list;
        }

        return null;

    }


    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
