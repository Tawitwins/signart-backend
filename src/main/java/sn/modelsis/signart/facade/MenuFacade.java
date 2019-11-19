package sn.modelsis.signart.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.PathParam;
import sn.modelsis.signart.Menu;

/**
 *
 * @author SNLOM
 */
@Stateless
public class MenuFacade extends AbstractFacade<Menu> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    public MenuFacade() {
        super(Menu.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public List<Menu> findByProfil(@PathParam("id") Integer idProfil) {
        /*CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Oeuvre> cq = cb.createQuery(Oeuvre.class);

        javax.persistence.criteria.Root<Menu> oeuv = cq.from(Menu.class);
        Join<Menu, Profil> marq = oeuv.join(Menu_.profilCollection);
        cq.where(cb.and(cb.equal(marq.get(Artiste_.id), IdArtiste)));
        //cq.orderBy(cb.asc(oeuv.get(Modele_.libelle)));
        TypedQuery<Oeuvre> q = getEntityManager().createQuery(cq);

        List<Oeuvre> list = q.getResultList();

        if (list != null && !list.isEmpty()) {
            return list;
        }*/

        return null;

    }
}
