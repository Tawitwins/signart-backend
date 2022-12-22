package sn.modelsis.signart.security;

import sn.modelsis.signart.Parametrage;
import sn.modelsis.signart.facade.ParametrageFacade;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Stateless
@Path("security")
public class RsaRest {
    @Inject
    ParametrageFacade parametrageFacade;
    RSA rsa = new RSA();
    @POST
    @Path("encrypt")
    @Consumes({MediaType.TEXT_PLAIN})
    public String encrypt(String data) throws Exception{
        String cipher = null;
        Parametrage param = parametrageFacade.findByParamName("RSA_PUBLIC_KEY");
        if(param != null) {
            byte[] dd = rsa.encrypt(data,param.getValue());
            cipher = rsa.encode(dd);
        }
       return cipher;
    }

    @POST
    @Path("decrypt")
    @Consumes({MediaType.TEXT_PLAIN})
    public String decrypt(String cipherText) throws Exception {
        String plainText = null;
        Parametrage param = parametrageFacade.findByParamName("RSA_PRIVATE_KEY");
        if(param != null) {
            plainText = rsa.decrypt(cipherText,param.getValue());
        }
        return plainText;
    }
}
