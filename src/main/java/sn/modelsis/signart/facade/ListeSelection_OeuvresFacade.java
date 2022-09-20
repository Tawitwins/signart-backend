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
import sn.modelsis.signart.ListeSelection_Oeuvres;
import sn.modelsis.signart.exception.SignArtException;

/**
 *
 * @author Pendaaa
 */
@Stateless
public class ListeSelection_OeuvresFacade extends AbstractFacade<ListeSelection_Oeuvres> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;
    
    EntityTransaction tx;

    public ListeSelection_OeuvresFacade() {
        super(ListeSelection_Oeuvres.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public List<ListeSelection_Oeuvres> findByIdListe(Integer idListe) throws SignArtException {
        try {
            final TypedQuery<ListeSelection_Oeuvres> query = getEntityManager().createNamedQuery("ListeSelection_Oeuvres.findByIdListe",
                    ListeSelection_Oeuvres.class);
            query.setParameter("idListe", idListe);
            return query.getResultList();
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }

    }
    
}
