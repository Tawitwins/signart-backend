package sn.modelsis.signart.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sn.modelsis.signart.TypeNewsletter;

/**
 *
 * @author SNLOM
 */
@Stateless
public class TypeNewsletterFacade extends AbstractFacade<TypeNewsletter> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    public TypeNewsletterFacade() {
        super(TypeNewsletter.class);
    }


    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
