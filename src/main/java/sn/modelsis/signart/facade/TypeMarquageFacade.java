package sn.modelsis.signart.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import sn.modelsis.signart.TypeMarquage;

/**
 *
 * @author SNLOM
 */
@Stateless
public class TypeMarquageFacade extends AbstractFacade<TypeMarquage> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    public TypeMarquageFacade() {
        super(TypeMarquage.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TypeMarquage findByCode(String code) {

        final TypedQuery<TypeMarquage> query = getEntityManager().createNamedQuery("TypeMarquage.findByCode",
                TypeMarquage.class);
        query.setParameter("code", code);
        List<TypeMarquage> list = query.getResultList();
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }
}
