package sn.modelsis.signart.dto;

import java.util.Date;


/**
 *
 * @author SNLOM
 */
public class MessagesTchatsDto {

    private Integer idMsg;
    private Integer idSender;
    private Integer idReceiver;
    private String username;
    private String contenu;
    private String profilSender;
    private String filename;
    private String urlFile;
    private byte[] msgFile;
    private String msgStateSender;
    private String msgStateReceiver;
    private Date dateEnvoi;
  

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
    
    public String getProfilSender() {
        return this.profilSender;
    }

    public void setProfilSender(String profilSender) {
        this.profilSender = profilSender;
    }
    
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
}
