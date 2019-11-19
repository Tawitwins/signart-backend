package sn.modelsis.signart.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import sn.modelsis.signart.EtatArtiste;

/**
 *
 * @author SNNGOMN
 */
@Stateless
public class EtatArtisteFacade extends AbstractFacade<EtatArtiste>{
    
    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    public EtatArtisteFacade() {
        super(EtatArtiste.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public EtatArtiste findByCode(String code) {
        final TypedQuery<EtatArtiste> query = getEntityManager().createNamedQuery("EtatArtiste.findByCode",
                EtatArtiste.class);
        query.setParameter("code", code);
        List<EtatArtiste> list = query.getResultList();
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }
}
