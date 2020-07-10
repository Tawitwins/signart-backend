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
import sn.modelsis.signart.Delai;
import sn.modelsis.signart.Theme;

/**
 *
 * @author snfayemp
 */
@Stateless
public class ThemeFacade extends AbstractFacade<Theme> {
      
    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    public ThemeFacade() {
        super(Theme.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @Override
    public List<Theme> findAll(){
        
            final TypedQuery<Theme> query = getEntityManager().createNamedQuery("Theme.findAll",
                    Theme.class);
            return query.getResultList();
        

    }

}
