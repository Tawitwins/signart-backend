package sn.modelsis.signart.dto;

/**
 *
 * @author SNLOM
 */
public class EmailDto {
        private String to;
        private String objet;
        private String content;
        private byte[] pj;
        
        public EmailDto() {
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
}
