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

import sn.modelsis.signart.*;
import sn.modelsis.signart.exception.SignArtException;
//import sn.modelsis.signart.SousTechnique_;


/**
 *
 * @author SNLOM
 */
@Stateless
public class OeuvreFacade extends AbstractFacade<Oeuvre> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    public OeuvreFacade() {
        super(Oeuvre.class);
    }

    /**
     * Retourne la liste des oeuvres pour une sous technique donnée
     *
     * @param //idSousTechnique
     * @return
     */
   /* public List<Oeuvre> findBySousTechnique(Integer idSousTechnique) {
        //EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Oeuvre> cq = cb.createQuery(Oeuvre.class);
        javax.persistence.criteria.Root<Oeuvre> oeuv = cq.from(Oeuvre.class);
        Join<Oeuvre, SousTechnique> marq = oeuv.join(Oeuvre_.idSousTechnique);
        cq.where(cb.and(cb.equal(marq.get(SousTechnique_.id), idSousTechnique)));
        //cq.orderBy(cb.asc(oeuv.get(Modele_.libelle)));

        TypedQuery<Oeuvre> q = getEntityManager().createQuery(cq);
        List<Oeuvre> list = q.getResultList();

        if (list != null && !list.isEmpty()) {
            return list;
        }

        return null;

    }

    /**
     * Retourne la liste des oeuvres pour une technique donnée
     *
     * @param idTechnique
     * @return
     */
    public List<Oeuvre> findByTechnique(Integer idTechnique) {
        //EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Oeuvre> cq = cb.createQuery(Oeuvre.class);
        javax.persistence.criteria.Root<Oeuvre> oeuv = cq.from(Oeuvre.class);
        Join<Oeuvre, Technique> tech = oeuv.join(Oeuvre_.idTechnique);
        //Join<SousTechnique, Technique> tech = sousTech.join(SousTechnique_.idTechnique);
        cq.where(cb.and(cb.equal(tech.get(Technique_.id), idTechnique)));
        //cq.orderBy(cb.asc(oeuv.get(Modele_.libelle)));

        TypedQuery<Oeuvre> q = getEntityManager().createQuery(cq);
        List<Oeuvre> list = q.getResultList();

        if (list != null && !list.isEmpty()) {
            return list;
        }

        return null;

    }
    public List<Oeuvre> findByTechniqueAndTheme(Integer idTechnique, Integer idTheme) {
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Oeuvre> cq = cb.createQuery(Oeuvre.class);

        javax.persistence.criteria.Root<Oeuvre> oeuv = cq.from(Oeuvre.class);
        Join<Oeuvre, Technique> tech = oeuv.join(Oeuvre_.idTechnique);
        //Join<SousTechnique, Technique> tech = sousTech.join(SousTechnique_.idTechnique);
        Join<Oeuvre, Theme> them = oeuv.join(Oeuvre_.themeSet);
        cq.where(cb.and(
            cb.equal(them.get(Theme_.id), idTheme),
            cb.equal(tech.get(Technique_.id), idTechnique)
            ));
        
        TypedQuery<Oeuvre> q = getEntityManager().createQuery(cq);
        List<Oeuvre> list = q.getResultList();

        if (list != null && !list.isEmpty()) {
            return list;
        }

        return null;

    }

    /**
     * Retourne la liste des oeuvres pour un artiste donné
     *
     * @param idArtiste
     * @return
     */
    public List<Oeuvre> findByArtiste(Integer idArtiste) {

        //EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Oeuvre> cq = cb.createQuery(Oeuvre.class);

        javax.persistence.criteria.Root<Oeuvre> oeuv = cq.from(Oeuvre.class);
        Join<Oeuvre, Artiste> marq = oeuv.join(Oeuvre_.idArtiste);
        cq.where(cb.and(cb.equal(marq.get(Artiste_.id), idArtiste)));
        //cq.orderBy(cb.asc(oeuv.get(Modele_.libelle)));
        TypedQuery<Oeuvre> q = getEntityManager().createQuery(cq);

        List<Oeuvre> list = q.getResultList();

        if (list != null && !list.isEmpty()) {
            return list;
        }

        return new ArrayList();
    }

    /**
     * Retourne la liste des oeuvres marqués(typeMarquage donné) par un client
     * donné
     * @param codeTypeMarquage
     * @param idClient
     * @return
     * @throws sn.modelsis.signart.exception.SignArtException
     */
    public List<Oeuvre> findMarqueByClient(String codeTypeMarquage, Integer idClient) throws SignArtException {
        try {
            final TypedQuery<Oeuvre> query = getEntityManager().createNamedQuery("MarquageOeuvre.findMarqueByClient",
                    Oeuvre.class);
            query.setParameter("idClient", idClient);
            query.setParameter("codeTypeMarquage", codeTypeMarquage);
            return query.getResultList();
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }

    }
    
    /**
     * 
     * @return 
     */
    public List<Oeuvre> findNouveau() {
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Oeuvre> cq = cb.createQuery(Oeuvre.class);
        javax.persistence.criteria.Root<Oeuvre> oeuv = cq.from(Oeuvre.class);
        cq.where(cb.and(cb.equal(oeuv.get(Oeuvre_.nouveau), Boolean.TRUE)));

        TypedQuery<Oeuvre> q = getEntityManager().createQuery(cq);
        List<Oeuvre> list = q.getResultList();

        if (list != null && !list.isEmpty()) {
            return list;
        }

        return null;

    }

    public Oeuvre findByReference(String reference) {
        final TypedQuery<Oeuvre> query = getEntityManager().createNamedQuery("Oeuvre.findByReference", Oeuvre.class);
        query.setParameter("reference", reference);
        List<Oeuvre> list = query.getResultList();
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return  null;
    }

    public Oeuvre findByUsure(String niveauUsure) {
        final TypedQuery<Oeuvre> query = getEntityManager().createNamedQuery("Oeuvre.findByUsure", Oeuvre.class);
        query.setParameter("usure", niveauUsure);
        List<Oeuvre> list = query.getResultList();
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return  null;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
