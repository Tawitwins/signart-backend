package sn.modelsis.signart.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import sn.modelsis.signart.ConfigState;

/**
 *
 * @author SNLOM
 */
@Stateless
public class ConfigStateFacade extends AbstractFacade<ConfigState> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    public ConfigStateFacade() {
        super(ConfigState.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     * 
     * @param etat
     * @return 
     */
    public ConfigState findByEtat(String etat) {

        final TypedQuery<ConfigState> query = getEntityManager().createNamedQuery("ConfigState.findByEtat",
                ConfigState.class);
        query.setParameter("etat", etat);
        List<ConfigState> list = query.getResultList();
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

}
