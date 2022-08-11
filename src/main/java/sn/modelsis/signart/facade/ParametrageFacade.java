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
 * @author SNLOM
 */
@Stateless
public class ParametrageFacade extends AbstractFacade<Parametrage> {

    @Inject
    EtatPanierFacade etatpanierFacade;
    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    public ParametrageFacade() {
        super(Parametrage.class);
    }
    
    public Parametrage findByParamName(String paramName) {
        final TypedQuery<Parametrage> query = getEntityManager().createNamedQuery("Parametrage.findByParamName",
                Parametrage.class);
        query.setParameter("paramName", paramName);
        List<Parametrage> list = query.getResultList();
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
