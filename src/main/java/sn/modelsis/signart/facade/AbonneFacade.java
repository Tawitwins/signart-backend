/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.modelsis.signart.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import sn.modelsis.signart.Abonne;
import sn.modelsis.signart.Artiste;
import sn.modelsis.signart.Formation;
import sn.modelsis.signart.exception.SignArtException;

/**
 *
 * @author Pendaaa
 */
@Stateless
public class AbonneFacade extends AbstractFacade<Abonne> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;
    
    EntityTransaction tx;

    public AbonneFacade() {
        super(Abonne.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public Abonne findByUtilisateur(Integer idUtilisateur) throws SignArtException {
        try {
            final TypedQuery<Abonne> query = getEntityManager().createNamedQuery("Abonne.findByIdUtilisateur",
                    Abonne.class);
            query.setParameter("idUtilisateur", idUtilisateur);
            return query.getSingleResult();
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }

    }
    
     public List<Abonne> findAllByIdUtilisateur(Integer idUtilisateur) throws SignArtException {
        try {
            final TypedQuery<Abonne> query = getEntityManager().createNamedQuery("Abonne.findAllByIdUtilisateur",
                    Abonne.class);
            query.setParameter("idUtilisateur", idUtilisateur);
            return query.getResultList();
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }

    }
    
     public Abonne findByIdListeSelection(Integer idListeSelection) throws SignArtException {
        try {
            final TypedQuery<Abonne> query = getEntityManager().createNamedQuery("Abonne.findByIdListeSelection",
                    Abonne.class);
            query.setParameter("idListeSelection", idListeSelection);
            return query.getSingleResult();
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }

    }
    
    public Abonne findById(Integer id) throws SignArtException {
        try {
            final TypedQuery<Abonne> query = getEntityManager().createNamedQuery("Abonne.findById",
                    Abonne.class);
            query.setParameter("id", id);
            //query.setMaxResults(1);
            final List<Abonne> abonnes = query.getResultList();
            if (abonnes.isEmpty()) {
                return null;
            }
            return abonnes.get(0);
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }
    }

    
}
