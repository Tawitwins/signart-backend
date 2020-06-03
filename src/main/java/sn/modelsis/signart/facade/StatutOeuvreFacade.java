package sn.modelsis.signart.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sn.modelsis.signart.StatutOeuvre;

/**
 *
 * @author SNLOM
 */
@Stateless
public class StatutOeuvreFacade extends AbstractFacade<StatutOeuvre> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    public StatutOeuvreFacade() {
        super(StatutOeuvre.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

   
   /* public Pays findByCode(String code) {

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
*/
}
