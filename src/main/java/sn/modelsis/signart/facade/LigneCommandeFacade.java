package sn.modelsis.signart.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;

import sn.modelsis.signart.*;

/**
 *
 * @author SNLOM
 */
@Stateless
public class LigneCommandeFacade extends AbstractFacade<LigneCommande> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    public LigneCommandeFacade() {
        super(LigneCommande.class);
    }

    /**
     * Retourne la liste des lignes de panier pour un client donné
     //* @param IdArtiste
     * @param idClient
     * @return 
     */
    public List<LigneCommande> findByClient(Integer idClient) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<LigneCommande> cq = cb.createQuery(LigneCommande.class);

        javax.persistence.criteria.Root<LigneCommande> oeuv = cq.from(LigneCommande.class);
        Join<LigneCommande, Commande> panier = oeuv.join(LigneCommande_.idCommande);
        Join<Commande, Client> client = panier.join(Commande_.idClient);
        cq.where(cb.and(cb.equal(client.get(Client_.id), idClient)));
        TypedQuery<LigneCommande> q = getEntityManager().createQuery(cq);

        List<LigneCommande> list = q.getResultList();

        if (list != null && !list.isEmpty()) {
            return list;
        }

        return null;

    }
    public LigneCommande findByid(Integer id) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<LigneCommande> cq = cb.createQuery(LigneCommande.class);

        javax.persistence.criteria.Root<LigneCommande> oeuv = cq.from(LigneCommande.class);
        cq.where(cb.and(cb.equal(oeuv.get(LigneCommande_.id), id)));
        TypedQuery<LigneCommande> q = getEntityManager().createQuery(cq);

        List<LigneCommande> list = q.getResultList();

        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }

        return null;

    }
    public LigneCommande findById(Integer id) {

        final TypedQuery<LigneCommande> query = getEntityManager().createNamedQuery("LigneCommande.findById",
                LigneCommande.class);
        query.setParameter("id", id);
        List<LigneCommande> list = query.getResultList();
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }
   /* public List<LigneCommande> findByMagasin(Integer idMagasin) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<LigneCommande> cq = cb.createQuery(LigneCommande.class);

        javax.persistence.criteria.Root<LigneCommande> oeuv = cq.from(LigneCommande.class);
        Join<LigneCommande, Oeuvre> ligneComplet = oeuv.join(LigneCommande_.idOeuvre);
        //Join<Commande, Client> client = panier.join(Commande_.idClient);
        cq.where(cb.and(cb.equal(ligneComplet.get(Oeuvre_.idMagasin), idMagasin)));
        TypedQuery<LigneCommande> q = getEntityManager().createQuery(cq);

        List<LigneCommande> list = q.getResultList();

        if (list != null && !list.isEmpty()) {
            return list;
        }

        return null;

    }*/

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
