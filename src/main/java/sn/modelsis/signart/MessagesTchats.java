package sn.modelsis.signart;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author SNLOM
 */
@Entity
@Table(name = "MessagesTchats", catalog = "signart", schema = "dbo")
@NamedQueries({
    //@NamedQuery(name = "Visiteur.findClientArtiste", query = "SELECT cl FROM visisteur cl Join cl.commandeSet cm Join cm.ligneCommandeSet lc WHERE lc.idOeuvre.idArtiste.id = :idArtiste")
      @NamedQuery(name = "MessagesTchats.findAll", query = "SELECT mt FROM MessagesTchats mt")
    , @NamedQuery(name = "MessagesTchats.findById", query = "SELECT mt FROM MessagesTchats mt WHERE mt.idMsg = :idMsg")
    , @NamedQuery(name = "MessagesTchats.findByIdSender", query = "SELECT mt FROM MessagesTchats mt WHERE mt.idSender = :idSender")
    , @NamedQuery(name = "MessagesTchats.findByPrenom", query = "SELECT mt FROM MessagesTchats mt WHERE mt.idReceiver = :idReceiver")
    , @NamedQuery(name = "MessagesTchats.findAllMine", query = "SELECT mt FROM MessagesTchats mt WHERE mt.idSender = :idUser or mt.idReceiver = :idUser ")
    , @NamedQuery(name = "MessagesTchats.findNewMsg", query = "SELECT mt FROM MessagesTchats mt WHERE mt.idReceiver = 0 ")
    , @NamedQuery(name = "MessagesTchats.findAllForAdmin", query = "SELECT mt FROM MessagesTchats mt  WHERE mt.idReceiver = 0 or mt.idReceiver = :idUser")
    //, @NamedQuery(name = "MessagesTchats.findByRaisonSociale", query = "SELECT c FROM MessagesTchats c WHERE c.raisonsociale = :raisonsociale") GROUP BY mt.idSender,mt.idMsg,mt.contenu,mt.dateEnvoi
    , /*@NamedQuery(name = "Visiteur.findByPays", query = "SELECT c FROM Visiteur c WHERE c.pays = :pays")*/})

/**
 *
 * @author SNMBENGUEO
 */
public class MessagesTchats implements Serializable {
     public static final String USER_TYPE_CLIENT = "CLIENT";
    public static final String USER_TYPE_ARTISTE = "ARTISTE";

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idMsg", nullable = false)
    private Integer idMsg;
    @Column(name = "idSender", nullable = true)
    private Integer idSender;
    @Column(name = "idReceiver", nullable = true)
    private Integer idReceiver;
    
    @Column(name = "username", length = 100)
    private String username;
    @Column(name = "contenu", length = 5000)
    private String contenu;
//    @Column(name = "profilSender", length = 50)
//    private String profilSender;
//    @Column(name = "profilReceiver", length = 50)
//    private String profilReceiver;
    @Column(name = "filename", length = 200)
    private String filename;
    @Column(name = "urlFile", length = 200)
    private String urlFile;
    @Column(name = "msgFile", nullable = true)
    private byte[] msgFile;
    @Column(name = "msgStateSender", length = 50)
    private String msgStateSender;
    @Column(name = "msgStateReceiver", length = 50)
    private String msgStateReceiver;
    @Column(name = "dateEnvoi", nullable = true)
    private Date dateEnvoi;
    @Column(name = "showDate", nullable = true)
    private boolean showDate;

    public MessagesTchats() {
    }

    public MessagesTchats(Integer idMsg) {
        this.idMsg = idMsg;
    }

    public Integer getIdMsg() {
        return this.idMsg;
    }

    public void setIdMsg(Integer idMsg) {
        this.idMsg = idMsg;
    }
    public Integer getIdSender() {
        return this.idSender;
    }

    public void setIdSender(Integer idSender) {
        this.idSender = idSender;
    }
    public Integer getIdReceiver() {
        return this.idReceiver;
    }

    public void setIdReceiver(Integer idReceiver) {
        this.idReceiver = idReceiver;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContenu() {
        return this.contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }
    
//    public String getProfilSender() {
//        return this.profilSender;
//    }
//
//    public void setProfilSender(String profilSender) {
//        this.profilSender = profilSender;
//    }
//     public String getProfilReceiver() {
//        return this.profilReceiver;
//    }
//
//    public void setProfilReceiver(String profilReceiver) {
//        this.profilReceiver = profilReceiver;
//    }
    public String getFilename() {
        return this.filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getUrlfile() {
        return urlFile;
    }

    public void setUrlFile(String urlFile) {
        this.urlFile = urlFile;
    }
     public byte[] getMsgFile() {
        return this.msgFile;
    }

    public void setMsgFile(byte[] msgFile) {
        this.msgFile = msgFile;
    }
     public String getMsgStateSender() {
        return this.msgStateSender;
    }

    public void setMsgStateSender(String msgStateSender) {
        this.msgStateSender = msgStateSender;
    }
    public String getMsgStateReceiver() {
        return this.msgStateReceiver;
    }

    public void setMsgStateReceiver(String msgStateReceiver) {
        this.msgStateReceiver = msgStateReceiver;
    }
    public Date getDateEnvoi() {
        return this.dateEnvoi;
    }

    public void setDateEnvoi(Date dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }
    public boolean getShowDate() {
        return this.showDate;
    }

    public void setShowDate(boolean showDate) {
        this.showDate = showDate;
    }
    /*public Integer getIdPays() {
        return this.Pays.getId();
    }

    public void setIdPays() {
        this.idPays = this.Pays.getId();
    }*/



    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMsg != null ? idMsg.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MessagesTchats)) {
            return false;
        }
        MessagesTchats other = (MessagesTchats) object;
        return !((this.idMsg == null && other.idMsg != null) || (this.idMsg != null && !this.idMsg.equals(other.idMsg)));
    }

    @Override
    public String toString() {
        return "sn.modelsis.signart.MessagesTchats[ id=" + idMsg + " ]";
    }
}
