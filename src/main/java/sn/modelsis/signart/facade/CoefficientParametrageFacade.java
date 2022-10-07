package sn.modelsis.signart.facade;

import sn.modelsis.signart.CoefficientParametrage;
import sn.modelsis.signart.exception.SignArtException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class CoefficientParametrageFacade extends  AbstractFacade<CoefficientParametrage>{
    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    EntityTransaction tx;

    public CoefficientParametrageFacade() {
        super(CoefficientParametrage.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CoefficientParametrage findById(Integer id) throws SignArtException {
        try {
            final TypedQuery<CoefficientParametrage> query = getEntityManager().createNamedQuery("CoefficientParametrage.findById",
                    CoefficientParametrage.class);
            query.setParameter("id", id);
            query.setMaxResults(1);
            final List<CoefficientParametrage> coefficientParametrageList = query.getResultList();
            if (coefficientParametrageList.isEmpty()) {
                return null;
            }
            return coefficientParametrageList.get(0);
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }
    }

    public CoefficientParametrage findByCodeParametre(String codeParametre) throws SignArtException {
        try {
            final TypedQuery<CoefficientParametrage> query = getEntityManager().createNamedQuery("CoefficientParametrage.findByCodeParametre",
                    CoefficientParametrage.class);
            query.setParameter("codeParametre", codeParametre);
            query.setMaxResults(1);
            final List<CoefficientParametrage> coefficientParametrageList = query.getResultList();
            if (coefficientParametrageList.isEmpty()) {
                return null;
            }
            return coefficientParametrageList.get(0);
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }
    }

    public CoefficientParametrage findByValeurParametre(String valeurParametre) throws SignArtException {
        try {
            final TypedQuery<CoefficientParametrage> query = getEntityManager().createNamedQuery("CoefficientParametrage.findByValeurParametre",
                    CoefficientParametrage.class);
            query.setParameter("valeurParametre", valeurParametre);
            query.setMaxResults(1);
            final List<CoefficientParametrage> coefficientParametrageList = query.getResultList();
            if (coefficientParametrageList.isEmpty()) {
                return null;
            }
            return coefficientParametrageList.get(0);
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }
    }
    public CoefficientParametrage findByStatut(String statut) throws SignArtException {
        try {
            final TypedQuery<CoefficientParametrage> query = getEntityManager().createNamedQuery("CoefficientParametrage.findByStatut",
                    CoefficientParametrage.class);
            query.setParameter("statut", statut);
            query.setMaxResults(1);
            final List<CoefficientParametrage> coefficientParametrageList = query.getResultList();
            if (coefficientParametrageList.isEmpty()) {
                return null;
            }
            return coefficientParametrageList.get(0);
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }
    }

    public CoefficientParametrage findByEnumTypeParam(String enumTypeParam) throws SignArtException {
        try {
            final TypedQuery<CoefficientParametrage> query = getEntityManager().createNamedQuery("CoefficientParametrage.findByEnumTypeParam",
                    CoefficientParametrage.class);
            query.setParameter("enumTypeParam", enumTypeParam);
            query.setMaxResults(1);
            final List<CoefficientParametrage> coefficientParametrageList = query.getResultList();
            if (coefficientParametrageList.isEmpty()) {
                return null;
            }
            return coefficientParametrageList.get(0);
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }
    }
}
