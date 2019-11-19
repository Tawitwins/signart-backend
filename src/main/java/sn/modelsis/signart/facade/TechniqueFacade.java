package sn.modelsis.signart.facade;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import sn.modelsis.signart.Technique;

/**
 *
 * @author SNLOM
 */
@Stateless
public class TechniqueFacade extends AbstractFacade<Technique> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    public TechniqueFacade() {
        super(Technique.class);
    }

    public Technique findByMenu(Integer idMenu){
        final TypedQuery<Technique> query = getEntityManager().createNamedQuery("Technique.findByMenu",
                Technique.class);
        query.setParameter("idMenu", idMenu);
        List<Technique> list = query.getResultList();
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
