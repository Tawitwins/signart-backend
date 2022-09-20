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
import sn.modelsis.signart.Licence;
import sn.modelsis.signart.exception.SignArtException;

/**
 *
 * @author Pendaaa
 */
@Stateless
public class LicenceFacade extends AbstractFacade<Licence> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;
    
    EntityTransaction tx;

    public LicenceFacade() {
        super(Licence.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public Licence findByIdAbonnement(Integer idAbonnement) throws SignArtException {
        try {
            final TypedQuery<Licence> query = getEntityManager().createNamedQuery("Licence.findByIdAbonnement",
                    Licence.class);
            query.setParameter("idAbonnement", idAbonnement);
            return query.getSingleResult();
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }

    }
    
    public Licence findById(Integer id) throws SignArtException {
        try {
            final TypedQuery<Licence> query = getEntityManager().createNamedQuery("Licence.findById",
                    Licence.class);
            query.setParameter("id", id);
            query.setMaxResults(1);
            final List<Licence> licences = query.getResultList();
            if (licences.isEmpty()) {
                return null;
            }
            return licences.get(0);
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }
    }
    
}
