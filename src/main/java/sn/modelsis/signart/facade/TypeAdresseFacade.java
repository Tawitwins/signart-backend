package sn.modelsis.signart.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import sn.modelsis.signart.TypeAdresse;

/**
 *
 * @author SNLOM
 */
@Stateless
public class TypeAdresseFacade extends AbstractFacade<TypeAdresse> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    public TypeAdresseFacade() {
        super(TypeAdresse.class);
    }


    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     * 
     * @param code
     * @return 
     */
    public TypeAdresse findByCode(String code) {
        final TypedQuery<TypeAdresse> query = getEntityManager().createNamedQuery("TypeAdresse.findByCode",
                TypeAdresse.class);
        query.setParameter("code", code);
        List<TypeAdresse> list = query.getResultList();
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }
}
