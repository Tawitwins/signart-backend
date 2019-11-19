package sn.modelsis.signart.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sn.modelsis.signart.Biographie;

/**
 *
 * @author SNLOM
 */
@Stateless
public class BiographieFacade extends AbstractFacade<Biographie> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    public BiographieFacade() {
        super(Biographie.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     *
     * @param idArtiste
     * @return
     * @throws SignArtException
     */
//    public Biographie findBiogByArtiste(Integer idArtiste) throws SignArtException {
//        try {
//            Biographie bigraphie = new Biographie();
//            bigraphie = 
//            final TypedQuery<Filmographie> query = getEntityManager().createNamedQuery("Filmographie.findByArtiste",
//                    Filmographie.class);
//            query.setParameter("idArtiste", idArtiste);
//            return query.getResultList();
//        } catch (Exception e) {
//            throw new SignArtException(e.getMessage(), e);
//        }
//
//    }
}
