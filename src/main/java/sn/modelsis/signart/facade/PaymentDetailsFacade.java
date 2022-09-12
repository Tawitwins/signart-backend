package sn.modelsis.signart.facade;

import sn.modelsis.signart.PaymentDetails;
import sn.modelsis.signart.Pays;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class PaymentDetailsFacade extends AbstractFacade<PaymentDetails> {
    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    public PaymentDetailsFacade() {
        super(PaymentDetails.class);
    }

    public PaymentDetails findByReference(String reference) {

        final TypedQuery<PaymentDetails> query = getEntityManager().createNamedQuery("PaymentDetails.findByReference",
                PaymentDetails.class);
        query.setParameter("reference", reference);
        List<PaymentDetails> list = query.getResultList();
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    public PaymentDetails findByDestinataire(String destinataire) {

        final TypedQuery<PaymentDetails> query = getEntityManager().createNamedQuery("PaymentDetails.findByDestinataire",
                PaymentDetails.class);
        query.setParameter("destinataire", destinataire);
        List<PaymentDetails> list = query.getResultList();
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    public PaymentDetails findByPreuve(String preuve) {

        final TypedQuery<PaymentDetails> query = getEntityManager().createNamedQuery("PaymentDetails.findByPreuve",
                PaymentDetails.class);
        query.setParameter("preuve", preuve);
        List<PaymentDetails> list = query.getResultList();
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
