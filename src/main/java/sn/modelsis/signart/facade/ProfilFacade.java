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
import sn.modelsis.signart.Profil;
import sn.modelsis.signart.Profil;

/**
 *
 * @author SNNGOMN
 */
@Stateless
public class ProfilFacade extends AbstractFacade<Profil> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    public ProfilFacade() {
        super(Profil.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Profil findByCode(String code) {

        final TypedQuery<Profil> query = getEntityManager().createNamedQuery("Profil.findByCode",
                Profil.class);
        query.setParameter("code", code);
        List<Profil> list = query.getResultList();
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }
}
