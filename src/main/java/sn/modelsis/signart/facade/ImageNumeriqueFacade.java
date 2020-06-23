/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.modelsis.signart.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import sn.modelsis.signart.ImageNumerique;
import sn.modelsis.signart.exception.SignArtException;

/**
 *
 * @author Pendaaa
 */
@Stateless
public class ImageNumeriqueFacade extends AbstractFacade<ImageNumerique>{
    
    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    public ImageNumeriqueFacade() {
        super(ImageNumerique.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public ImageNumerique findById(Integer id) throws SignArtException {
        try {
            final TypedQuery<ImageNumerique> query = getEntityManager().createNamedQuery("ImageNumerique.findById",
                    ImageNumerique.class);
            query.setParameter("id", id);
            query.setMaxResults(1);
            final List<ImageNumerique> images = query.getResultList();
            if (images.isEmpty()) {
                return null;
            }
            return images.get(0);
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }
    }
    
    public ImageNumerique findByValue(String value) throws SignArtException {
        try {
            final TypedQuery<ImageNumerique> query = getEntityManager().createNamedQuery("ImageNumerique.findByValue",
                    ImageNumerique.class);
            query.setParameter("value", value);
            query.setMaxResults(1);
            final List<ImageNumerique> images = query.getResultList();
            if (images.isEmpty()) {
                return null;
            }
            return images.get(0);
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }
    }
    
    /**
     *
     * @return
     * @throws SignArtException
     */
    @Override
    public List<ImageNumerique> findAll(){
        
            final TypedQuery<ImageNumerique> query = getEntityManager().createNamedQuery("ImageNumerique.findAll",
                    ImageNumerique.class);
            final List<ImageNumerique> images = query.getResultList();
            if (images.isEmpty()) {
                return null;
            }
            return images;
        
    }
    
}
