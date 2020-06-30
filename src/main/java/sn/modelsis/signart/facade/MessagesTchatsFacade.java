package sn.modelsis.signart.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import sn.modelsis.signart.Exposition;
import sn.modelsis.signart.MessagesTchats;
import sn.modelsis.signart.exception.SignArtException;

/**
 *
 * @author SNLOM
 */
@Stateless
public class MessagesTchatsFacade extends AbstractFacade<MessagesTchats> {

    @PersistenceContext(unitName = "SignArtPU") 
    private EntityManager em;

    public MessagesTchatsFacade() {
        super(MessagesTchats.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

//    public List<MessagesTchats> findAll() throws SignArtException {
//        try {
//            final TypedQuery<MessagesTchats> query = getEntityManager().createNamedQuery("MessagesTchats.findByAll",
//                    MessagesTchats.class);
//            //query.setParameter("idMsg", idMsg);
//            return query.getResultList();
//        } catch (Exception e) {
//            throw new SignArtException(e.getMessage(), e);
//        }
//
//    }
    /**
     *
     * @param idMsg
     * @return
     * @throws SignArtException
     */
    public List<MessagesTchats> findById(Integer idMsg) throws SignArtException {
        try {
            final TypedQuery<MessagesTchats> query = getEntityManager().createNamedQuery("MessagesTchats.findById",
                    MessagesTchats.class);
            query.setParameter("idMsg", idMsg);
            return query.getResultList();
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }

    }
    public List<MessagesTchats> findByIdSender(Integer idSender) throws SignArtException {
        try {
            final TypedQuery<MessagesTchats> query = getEntityManager().createNamedQuery("MessagesTchats.findByIdSender",
                    MessagesTchats.class);
            query.setParameter("idSender", idSender);
            return query.getResultList();
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }

    }
    public List<MessagesTchats> findByIdReceiver(Integer idReceiver) throws SignArtException {
        try {
            final TypedQuery<MessagesTchats> query = getEntityManager().createNamedQuery("MessagesTchats.findByIdReceiver",
                    MessagesTchats.class);
            query.setParameter("idReceiver", idReceiver);
            return query.getResultList();
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }

    }
    public List<MessagesTchats> findAllMine(Integer idUser) throws SignArtException {
        try {
            final TypedQuery<MessagesTchats> query = getEntityManager().createNamedQuery("MessagesTchats.findAllMine",
                    MessagesTchats.class);
             query.setParameter("idUser", idUser);
            return query.getResultList();
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }

    }
    public List<MessagesTchats> findNewMsg() throws SignArtException {
        try {
            final TypedQuery<MessagesTchats> query = getEntityManager().createNamedQuery("MessagesTchats.findNewMsg",
                    MessagesTchats.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }

    }
            public List<MessagesTchats> findAllForAdmin(Integer idUser) throws SignArtException {
        try {
            final TypedQuery<MessagesTchats> query = getEntityManager().createNamedQuery("MessagesTchats.findAllForAdmin",
                    MessagesTchats.class);
             query.setParameter("idUser", idUser);
            return query.getResultList();
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }

    }
}
