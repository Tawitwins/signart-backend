package sn.modelsis.signart.security;

import sn.modelsis.signart.Parametrage;
import sn.modelsis.signart.facade.ParametrageFacade;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Stateless
@Path("secure")
public class AESRest {
    @Inject
    ParametrageFacade parametrageFacade;

    String algorithm = null;
    String secretKey = null;
    String ivParam =  null;

    @POST
    @Path("encrypt")
    @Consumes({MediaType.TEXT_PLAIN})
    public String encrypt(String plain){
        intializeParam();
        String cipher = null;
        if(secretKey != null && algorithm != null && ivParam !=null)
            cipher = AESUtil.encrypt(algorithm,plain,secretKey,ivParam);
        return cipher;
    }

    @POST
    @Path("decrypt")
    @Consumes({MediaType.TEXT_PLAIN})
    public String decrypt(String cipherText) {
        intializeParam();
        String plain = null;
        if(secretKey != null && algorithm != null && ivParam !=null)
            plain = AESUtil.decrypt(algorithm,cipherText,secretKey,ivParam);
        return plain;
    }

    public void intializeParam(){

        Parametrage param_key = parametrageFacade.findByParamName("SECRET_KEY");
        Parametrage param_algorigthm = parametrageFacade.findByParamName("ALGORITHME");
        Parametrage param_ivParam = parametrageFacade.findByParamName("IV_PARAMETER_SPEC");

        if(param_key != null)
            secretKey = param_key.getValue();
        if(param_algorigthm != null)
            algorithm = param_algorigthm.getValue();
        if(param_ivParam != null)
            ivParam = param_ivParam.getValue();
    }
}
