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
import sn.modelsis.signart.ImageMiniature;
import sn.modelsis.signart.exception.SignArtException;


/**
 *
 * @author snfayemp
 */
@Stateless
public class ImageMiniatureFacade extends AbstractFacade<ImageMiniature> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    public ImageMiniatureFacade() {
        super(ImageMiniature.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
     public List<ImageMiniature> findAllImage(String codeTypeMarquage, Integer idClient) throws SignArtException {
        try {
            final TypedQuery<ImageMiniature> query = getEntityManager().createNamedQuery("ImageMiniature.findAll",
                    ImageMiniature.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }

    }
     
     public ImageMiniature findByName(String nomImage) throws SignArtException {
        try {
            final TypedQuery<ImageMiniature> query = getEntityManager().createNamedQuery("ImageMiniature.findByName",
                    ImageMiniature.class);
            query.setParameter("nomImage", nomImage);
            return query.getSingleResult();
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }
    } 
}
