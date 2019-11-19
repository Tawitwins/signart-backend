package sn.modelsis.signart.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sn.modelsis.signart.Newsletter;

/**
 *
 * @author SNLOM
 */
@Stateless
public class NewsletterFacade extends AbstractFacade<Newsletter> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    public NewsletterFacade() {
        super(Newsletter.class);
    }


    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
