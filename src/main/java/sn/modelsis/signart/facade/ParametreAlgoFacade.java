package sn.modelsis.signart.facade;

import sn.modelsis.signart.Parametrage;
import sn.modelsis.signart.ParametreAlgo;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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

    }


    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
