/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.modelsis.signart.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sn.modelsis.signart.Annonce;
import sn.modelsis.signart.ImageProfil;

/**
 *
 * @author snfayemp
 */
@Stateless
public class ImageProfilFacade extends AbstractFacade<ImageProfil>{
     @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;
     
     public ImageProfilFacade() {
        super(ImageProfil.class);
    }
     
      @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    
}
