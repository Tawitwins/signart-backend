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
import sn.modelsis.signart.LignePanier;
import sn.modelsis.signart.LignePanier_;
import sn.modelsis.signart.Oeuvre;
import sn.modelsis.signart.Panier;
import sn.modelsis.signart.Panier_;
import sn.modelsis.signart.exception.SignArtException;

/**
 *
 * @author SNLOM
 */
@Stateless
public class LignePanierFacade extends AbstractFacade<LignePanier> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    public LignePanierFacade() {
        super(LignePanier.class);
    }

    ///**
    // * Retourne la liste des oeuvres pour une sous technique donnée
    // * @param IdSousTechnique
    // * @return 
    // */
    /*public List<LignePanier> findBySousTechnique(Integer IdSousTechnique) {
        //EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<LignePanier> cq = cb.createQuery(LignePanier.class);
        javax.persistence.criteria.Root<LignePanier> oeuv = cq.from(LignePanier.class);
        Join<LignePanier, SousTechnique> marq = oeuv.join(LignePanier_.idSousTechnique);
        cq.where(cb.and(cb.equal(marq.get(SousTechnique_.id), IdSousTechnique)));
        //cq.orderBy(cb.asc(oeuv.get(Modele_.libelle)));

        TypedQuery<LignePanier> q = getEntityManager().createQuery(cq);
        List<LignePanier> list = q.getResultList();

        if (list != null && !list.isEmpty()) {
            return list;
        }

        return null;

    }*/
    
    /**
     * Retourne la liste des oeuvres pour une technique donnée
     //* @param IdTechnique
     * @return 
     */
    /*public List<LignePanier> findByTechnique(Integer IdTechnique) {
        //EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<LignePanier> cq = cb.createQuery(LignePanier.class);
        javax.persistence.criteria.Root<LignePanier> oeuv = cq.from(LignePanier.class);
        Join<LignePanier, SousTechnique> sousTech = oeuv.join(LignePanier_.idSousTechnique);
        Join<SousTechnique, Technique> tech = sousTech.join(SousTechnique_.idTechnique);
        cq.where(cb.and(cb.equal(tech.get(Technique_.id), IdTechnique)));
        //cq.orderBy(cb.asc(oeuv.get(Modele_.libelle)));

        TypedQuery<LignePanier> q = getEntityManager().createQuery(cq);
        List<LignePanier> list = q.getResultList();

        if (list != null && !list.isEmpty()) {
            return list;
        }

        return null;

    }*/

    /**
     * Retourne la liste des lignes de panier pour un client donné
     //* @param IdArtiste
     * @param idClient
     * @return 
     */
    public List<LignePanier> findByClient(Integer idClient) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<LignePanier> cq = cb.createQuery(LignePanier.class);

        javax.persistence.criteria.Root<LignePanier> oeuv = cq.from(LignePanier.class);
        Join<LignePanier, Panier> panier = oeuv.join(LignePanier_.idPanier);
        Join<Panier, Client> client = panier.join(Panier_.idClient);
        cq.where(cb.and(cb.equal(client.get(Client_.id), idClient)));
        TypedQuery<LignePanier> q = getEntityManager().createQuery(cq);

        List<LignePanier> list = q.getResultList();

        if (list != null && !list.isEmpty()) {
            return list;
        }

        return null;

    }
    
    /**
     * 
     * @param idClient
     * @return
     * @throws SignArtException 
     */
    public List<LignePanier> findLignePanierByClient(Integer idClient) throws SignArtException {
        try {
            final TypedQuery<LignePanier> query = getEntityManager().createNamedQuery("LignePanier.findByIdClient",
                    LignePanier.class);
            query.setParameter("idClient", idClient);
            return query.getResultList();
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }

    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
