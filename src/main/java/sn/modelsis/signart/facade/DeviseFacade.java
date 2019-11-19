package sn.modelsis.signart.facade;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sn.modelsis.signart.Devise;
import javax.persistence.TypedQuery;

/**
 *
 * @author SNLOM
 */
@Stateless
public class DeviseFacade extends AbstractFacade<Devise> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    public DeviseFacade() {
        super(Devise.class);
    }

    public Devise findByCode(String code) {
        final TypedQuery<Devise> query = getEntityManager().createNamedQuery("Devise.findByCode", Devise.class);
        query.setParameter("code", code);
        List<Devise> list = query.getResultList();
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
