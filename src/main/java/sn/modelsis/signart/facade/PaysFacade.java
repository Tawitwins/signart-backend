package sn.modelsis.signart.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import sn.modelsis.signart.Pays;
import sn.modelsis.signart.Pays;

/**
 *
 * @author SNLOM
 */
@Stateless
public class PaysFacade extends AbstractFacade<Pays> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    public PaysFacade() {
        super(Pays.class);
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
    public Pays findByCode(String code) {

        final TypedQuery<Pays> query = getEntityManager().createNamedQuery("Pays.findByCode",
                Pays.class);
        query.setParameter("code", code);
        List<Pays> list = query.getResultList();
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }
    
    public Pays findByLibelle(String libelle) {

        final TypedQuery<Pays> query = getEntityManager().createNamedQuery("Pays.findByLibelle",
                Pays.class);
        query.setParameter("libelle", libelle);
        List<Pays> list = query.getResultList();
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

}

