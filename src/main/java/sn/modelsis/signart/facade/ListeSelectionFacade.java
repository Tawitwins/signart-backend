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
import sn.modelsis.signart.Delai;
import sn.modelsis.signart.ListeSelection;
import sn.modelsis.signart.exception.SignArtException;

/**
 *
 * @author Pendaaa
 */
@Stateless
public class ListeSelectionFacade extends AbstractFacade<ListeSelection> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;
    
    EntityTransaction tx;

    public ListeSelectionFacade() {
        super(ListeSelection.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public ListeSelection findById(Integer id) throws SignArtException {
        try {
            final TypedQuery<ListeSelection> query = getEntityManager().createNamedQuery("ListeSelection.findById",
                    ListeSelection.class);
            query.setParameter("id", id);
            //query.setMaxResults(1);
            final List<ListeSelection> listeSelections = query.getResultList();
            if (listeSelections.isEmpty()) {
                return null;
            }
            return listeSelections.get(0);
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }
    }
    
    public ListeSelection findByName(String nomListe) throws SignArtException {
        try {
            final TypedQuery<ListeSelection> query = getEntityManager().createNamedQuery("ListeSelection.findName",
                    ListeSelection.class);
            query.setParameter("nomListe", nomListe);
            //query.setMaxResults(1);
            final List<ListeSelection> listeSelections = query.getResultList();
            if (listeSelections.isEmpty()) {
                return null;
            }
            return listeSelections.get(0);
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }
    }
    
    
    
    public List<ListeSelection> findByIdUtilisateur(Integer idUtilisateur) throws SignArtException {
        try {
            final TypedQuery<ListeSelection> query = getEntityManager().createNamedQuery("ListeSelection.findByIdUtilisateur",
                    ListeSelection.class);
            query.setParameter("idUtilisateur", idUtilisateur);
            return query.getResultList();
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }

    }
    
   
}
    
