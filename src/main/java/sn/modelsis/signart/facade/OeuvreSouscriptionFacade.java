/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.modelsis.signart.facade;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.GenerationType;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import sn.modelsis.signart.Artiste;
import sn.modelsis.signart.Artiste_;
import sn.modelsis.signart.Oeuvre;
import sn.modelsis.signart.OeuvreSouscription;
import sn.modelsis.signart.OeuvreSouscription_;
import sn.modelsis.signart.Oeuvre_;

/**
 *
 * @author snfayemp
 */
@Stateless
public class OeuvreSouscriptionFacade extends AbstractFacade<OeuvreSouscription> {
    
     @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    public OeuvreSouscriptionFacade() {
        super(OeuvreSouscription.class);
    }
    
    public List<OeuvreSouscription> findByArtiste(Integer idArtiste) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<OeuvreSouscription> cq = cb.createQuery(OeuvreSouscription.class);

        javax.persistence.criteria.Root<OeuvreSouscription> oeuv = cq.from(OeuvreSouscription.class);
        Join<OeuvreSouscription, Artiste> marq = oeuv.join(OeuvreSouscription_.idArtiste);
        cq.where(cb.and(cb.equal(marq.get(Artiste_.id), idArtiste)));
        TypedQuery<OeuvreSouscription> q = getEntityManager().createQuery(cq);
        List<OeuvreSouscription> list = q.getResultList();
        if (list != null && !list.isEmpty()) {
            return list;
        }
        return new ArrayList();
    }
    public OeuvreSouscription findById(Integer id) {

        final TypedQuery<OeuvreSouscription> query = getEntityManager().createNamedQuery("OeuvreSouscription.findById",
                OeuvreSouscription.class);
        query.setParameter("id", id);
        List<OeuvreSouscription> list = query.getResultList();
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
