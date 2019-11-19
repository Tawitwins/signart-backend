package sn.modelsis.signart.rest.application.config;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import sn.modelsis.signart.exception.SignArtException;

/**
 *
 * @author SNLOM
 */
@Provider
public class SignArtExceptionMapper implements javax.ws.rs.ext.ExceptionMapper<SignArtException> {

    /*@Override
    public Response toResponse(Exception exception) {
        exception.printStackTrace();
        return Response.status(500).build();
    }*/
    
    @Override
    public Response toResponse(SignArtException exception) {
        exception.printStackTrace();
        return Response.status(500).build();
    }

    /*@Override
    public Response toResponse(final SignArtException exception) {
        return Response.status(Status.INTERNAL_SERVER_ERROR).entity(new Message(exception.getMessage()))
                .type(MediaType.APPLICATION_JSON).build();
    }*/
}
