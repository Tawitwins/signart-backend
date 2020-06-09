package sn.modelsis.signart.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import sn.modelsis.signart.Annonce;
import sn.modelsis.signart.Biographie;
import sn.modelsis.signart.OeuvreSouscription;
import sn.modelsis.signart.exception.SignArtException;

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
    
    
    public Biographie findByArtiste(Integer idArtiste) throws SignArtException {
        try {
            final TypedQuery<Biographie> query = getEntityManager().createNamedQuery("Biographie.findByIdArtiste",
                    Biographie.class);
            query.setParameter("idArtiste", idArtiste);
            return query.getSingleResult();
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }

    }
    
    public List<Biographie> findAllByArtiste(Integer idArtiste) throws SignArtException {
        try {
            final TypedQuery<Biographie> query = getEntityManager().createNamedQuery("Biographie.findByIdArtiste",
                    Biographie.class);
            query.setParameter("idArtiste", idArtiste);
            return query.getResultList();
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }

    }
    
     public Biographie findById(Integer id) {

        final TypedQuery<Biographie> query = getEntityManager().createNamedQuery("Biographie.findById",
                Biographie.class);
        query.setParameter("id", id);
        List<Biographie> list = query.getResultList();
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
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
