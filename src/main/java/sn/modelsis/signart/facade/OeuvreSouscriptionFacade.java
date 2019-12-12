package sn.modelsis.signart.facade;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;

import sn.modelsis.signart.OeuvreSouscription;
import sn.modelsis.signart.OeuvreSouscription_;

import sn.modelsis.signart.Souscription;
import sn.modelsis.signart.Souscription_;

/**
 *
 * @author SNNGOM
 */
@Stateless
public class OeuvreSouscriptionFacade extends AbstractFacade<OeuvreSouscription> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    public OeuvreSouscriptionFacade() {
        super(OeuvreSouscription.class);
    }


    /**
     * Retourne la liste des oeuvres pour une souscription donné
     *
     * @param idSouscription
     * @return
     */
    public List<OeuvreSouscription> findBySouscription(Integer idSouscription) {


        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<OeuvreSouscription> cq = cb.createQuery(OeuvreSouscription.class);

        javax.persistence.criteria.Root<OeuvreSouscription> oeuv = cq.from(OeuvreSouscription.class);
        Join<OeuvreSouscription, Souscription> marq = oeuv.join(OeuvreSouscription_.idSouscription);
        cq.where(cb.and(cb.equal(marq.get(Souscription_.id), idSouscription)));
        //cq.orderBy(cb.asc(oeuv.get(Modele_.libelle)));
        TypedQuery<OeuvreSouscription> q = getEntityManager().createQuery(cq);

        List<OeuvreSouscription> list = q.getResultList();

        if (list != null && !list.isEmpty()) {
            return list;
        }

        return new ArrayList();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
