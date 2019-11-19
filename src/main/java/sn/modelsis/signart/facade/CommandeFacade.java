package sn.modelsis.signart.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import sn.modelsis.signart.Client;
import sn.modelsis.signart.Client_;
import sn.modelsis.signart.Commande;
import sn.modelsis.signart.Commande_;

/**
 *
 * @author SNLOM
 */
@Stateless
public class CommandeFacade extends AbstractFacade<Commande> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    public CommandeFacade() {
        super(Commande.class);
    }

    public Commande findByIdClient(Integer id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Commande> cq = cb.createQuery(Commande.class);
        javax.persistence.criteria.Root<Commande> oeuv = cq.from(Commande.class);
        Join<Commande, Client> client = oeuv.join(Commande_.idClient);
        cq.where(cb.and(cb.equal(client.get(Client_.id), id)));

        TypedQuery<Commande> q = getEntityManager().createQuery(cq);
        List<Commande> list = q.getResultList();

        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }

        return null;

    }

    /**
     * 
     * @param numero
     * @return 
     */
    public Commande findByNumero(String numero) {
        final TypedQuery<Commande> query = getEntityManager().createNamedQuery("Commande.findByNumero", Commande.class);
        query.setParameter("numero", numero);
        List<Commande> list = query.getResultList();
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
