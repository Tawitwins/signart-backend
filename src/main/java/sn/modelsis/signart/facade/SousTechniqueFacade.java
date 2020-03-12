package sn.modelsis.signart.facade;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

//import sn.modelsis.signart.SousTechnique;

/**
 *
 * @author SNLOM
 
@Stateless
public class SousTechniqueFacade extends AbstractFacade<SousTechnique> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    public SousTechniqueFacade() {
        super(SousTechnique.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public List<SousTechnique> findByTechnique(Integer idTechnique){
        final TypedQuery<SousTechnique> query = getEntityManager().createNamedQuery("SousTechnique.findByTechnique",
        SousTechnique.class);
        query.setParameter("idTechnique", idTechnique);
        List<SousTechnique> list = query.getResultList();
        return list;
    }
}*/
