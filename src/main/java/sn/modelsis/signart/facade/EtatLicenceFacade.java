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
import sn.modelsis.signart.EtatLicence;
import sn.modelsis.signart.exception.SignArtException;

/**
 *
 * @author snfayemp
 */
@Stateless
public class EtatLicenceFacade extends AbstractFacade<EtatLicence> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;
    
    EntityTransaction tx;

    public EtatLicenceFacade() {
        super(EtatLicence.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
     public EtatLicence findById(Integer id) throws SignArtException {
        try {
            final TypedQuery<EtatLicence> query = getEntityManager().createNamedQuery("EtatLicence.findById",
                    EtatLicence.class);
            query.setParameter("id", id);
            //query.setMaxResults(1);
            final List<EtatLicence> etats = query.getResultList();
            if (etats.isEmpty()) {
                return null;
            }
            return etats.get(0);
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }
    }
     
     public EtatLicence findByLibelle(String libelle) throws SignArtException {
        try {
            final TypedQuery<EtatLicence> query = getEntityManager().createNamedQuery("EtatLicence.findByLibelle",
                    EtatLicence.class);
            query.setParameter("libelle", libelle);
            //query.setMaxResults(1);
            final List<EtatLicence> etats = query.getResultList();
            if (etats.isEmpty()) {
                return null;
            }
            return etats.get(0);
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }
    }
    
    @Override
    public List<EtatLicence> findAll(){
        
            final TypedQuery<EtatLicence> query = getEntityManager().createNamedQuery("EtatLicence.findAll",
                    EtatLicence.class);
            return query.getResultList();
        

    }
    
}
