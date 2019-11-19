package sn.modelsis.signart.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import sn.modelsis.signart.Adresse;
import sn.modelsis.signart.Adresse_;
import sn.modelsis.signart.Client;
import sn.modelsis.signart.Client_;

/**
 *
 * @author SNLOM
 */
@Stateless
public class AdresseFacade extends AbstractFacade<Adresse> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    public AdresseFacade() {
        super(Adresse.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public List<Adresse> findByClient(Integer idClient) {
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Adresse> cq = cb.createQuery(Adresse.class);
        javax.persistence.criteria.Root<Adresse> rt = cq.from(Adresse.class);
        Join<Adresse, Client> cli = rt.join(Adresse_.idClient);
        cq.where(cb.and(cb.equal(cli.get(Client_.id), idClient)));

        TypedQuery<Adresse> q = getEntityManager().createQuery(cq);
        List<Adresse> list = q.getResultList();

        if (list != null && !list.isEmpty()) {
            return list;
        }

        return null;

    }
}
