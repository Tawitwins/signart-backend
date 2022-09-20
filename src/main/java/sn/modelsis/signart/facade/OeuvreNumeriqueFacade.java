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
import sn.modelsis.signart.OeuvreNumerique;
import sn.modelsis.signart.exception.SignArtException;

/**
 *
 * @author Pendaaa
 */
@Stateless
public class OeuvreNumeriqueFacade extends AbstractFacade<OeuvreNumerique>{
    
    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;
    
     public OeuvreNumeriqueFacade() {
        super(OeuvreNumerique.class);
    }

    
     @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public OeuvreNumerique findById(Integer id) throws SignArtException {
        try {
            final TypedQuery<OeuvreNumerique> query = getEntityManager().createNamedQuery("OeuvreNumerique.findById",
                    OeuvreNumerique.class);
            query.setParameter("id", id);
            query.setMaxResults(1);
            final List<OeuvreNumerique> imageBruts = query.getResultList();
            if (imageBruts.isEmpty()) {
                return null;
            }
            return imageBruts.get(0);
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }
    }
    
    public OeuvreNumerique findByName(String name) throws SignArtException {
        try {
            final TypedQuery<OeuvreNumerique> query = getEntityManager().createNamedQuery("OeuvreNumerique.findByName",
                    OeuvreNumerique.class);
            query.setParameter("nom", name);
            query.setMaxResults(1);
            final List<OeuvreNumerique> imageBruts = query.getResultList();
            if (imageBruts.isEmpty()) {
                return null;
            }
            return imageBruts.get(0);
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }
    }
    
    public List<OeuvreNumerique> findAll(){
       
            final TypedQuery<OeuvreNumerique> query = getEntityManager().createNamedQuery("OeuvreNumerique.findAll",
                    OeuvreNumerique.class);
            final List<OeuvreNumerique> imageBruts = query.getResultList();
            if (imageBruts.isEmpty()) {
                return null;
            }
            return imageBruts;
        
    }
    
}
