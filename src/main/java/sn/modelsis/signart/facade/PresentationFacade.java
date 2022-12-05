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
import sn.modelsis.signart.Formation;
import sn.modelsis.signart.Presentation;
import sn.modelsis.signart.exception.SignArtException;

/**
 *
 * @author Pendaaa
 */
@Stateless
public class PresentationFacade extends AbstractFacade<Presentation>{
    
    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    public PresentationFacade() {
        super(Presentation.class);
    }
    
   @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    
    public Presentation findByArtiste(Integer idArtiste) throws SignArtException {
        try {
            final TypedQuery<Presentation> query = getEntityManager().createNamedQuery("Presentation.findByArtiste",
                    Presentation.class);
            query.setParameter("idArtiste", idArtiste);
            return query.getSingleResult();
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }

    }
    
      public List<Presentation> findAllByArtiste(Integer idArtiste) throws SignArtException {
        try {
            final TypedQuery<Presentation> query = getEntityManager().createNamedQuery("Presentation.findByArtiste",
                    Presentation.class);
            query.setParameter("idArtiste", idArtiste);
            return query.getResultList();
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }

    }
      
      public List<Presentation> findAllPresentation() throws SignArtException {
        try {
            final TypedQuery<Presentation> query = getEntityManager().createNamedQuery("Presentation.findAll",
                    Presentation.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }

    }
    
    public Presentation findById(Integer id) throws SignArtException {
        try {
            final TypedQuery<Presentation> query = getEntityManager().createNamedQuery("Presentation.findById",
                    Presentation.class);
            query.setParameter("id", id);
            //query.setMaxResults(1);
            final List<Presentation> presentation = query.getResultList();
            if (presentation.isEmpty()) {
                return null;
            }
            return presentation.get(0);
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }
    }
}
