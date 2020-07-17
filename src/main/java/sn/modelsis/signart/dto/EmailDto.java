package sn.modelsis.signart.dto;

import java.util.Date;

/**
 *
 * @author SNLOM
 */
public class EmailDto {
        private Integer id;
        private Integer nbrEmail;
        private String to;
        private String objet;
        private String content;
        private Date dateEnvoi;
        private byte[] pj;
        
        public EmailDto() {
        }
        public Integer getId(){
            return this.id;
        }
        public void setId(Integer id){
            this.id=id;
        }
        public Integer getNbrEmail(){
            return this.nbrEmail;
        }
        public void setNbrEmail(Integer nbrEmail){
            this.nbrEmail=nbrEmail;
        }
        public String getTo(){
            return this.to;
        }
        public void setTo(String to){
            this.to = to;
        }
        public String getObjet(){
            return this.objet;
        }
        public void setObjet(String objet){
            this.objet = objet;
        }
        public String getContent(){
            return this.content;
        }
        public void setContent(String content){
            this.content = content;
        }
        public byte[] getPj(){
            return this.pj;
        }
        public void setPj ( byte[] pj){
            this.pj = pj;
        }
        public Date getDateEnvoi() {
        return this.dateEnvoi;
        }

        public void setDateEnvoi(Date dateEnvoi) {
            this.dateEnvoi = dateEnvoi;
        }
}
