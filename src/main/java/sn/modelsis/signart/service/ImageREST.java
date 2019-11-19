package sn.modelsis.signart.service;

import com.sun.jersey.multipart.FormDataParam;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import org.apache.commons.io.IOUtils;
import sn.modelsis.signart.Artiste;
//import org.glassfish.jersey.media.multipart.FormDataParam;
//import org.glassfish.jersey.media.multipart.FormDataParam;
import sn.modelsis.signart.Oeuvre;
import sn.modelsis.signart.facade.ArtisteFacade;
import sn.modelsis.signart.facade.OeuvreFacade;
//import org.glassfish.jersey.media.multipart.MultiPartFeature;
//import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

/**
 *
 * @author SNLOM
 */
@Stateless
@Path("image")
public class ImageREST {

    @Inject
    OeuvreFacade oeuvreFacade;
    @Inject
    ArtisteFacade artisteFacade;

    public ImageREST() {
    }

    @POST
    @Path("/{id}")
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    @Produces({MediaType.APPLICATION_JSON})
    //public Response create(@PathParam("id") Integer id, final MultipartFormDataInput input){//, @Context final SecurityContext securityContext) {
    public Response create(@PathParam("id") Integer id, @FormDataParam("file") InputStream uploadedInputStream) {
        //,@FormDataParam("file") FormDataContentDisposition fileDetail) {
        //String fileName = "";
        Oeuvre oeuvre;
        try {
            //final String idOeuvre = input.getFormDataPart("idOeuvre", String.class, null);
            //final String originalFileName = input.getFormDataPart("originalFileName", String.class, null);
            //final Map<String, List<InputPart>> formParts = input.getFormDataMap();
            //final List<InputPart> inPart = formParts.get("selectedFile");

            //if (Objects.isNull(inPart) || inPart.isEmpty()) {
            //    return Response.status(Response.Status.NOT_FOUND).entity("a file must be attached").build();
            //}
            if (Objects.isNull(uploadedInputStream)) {
                return Response.status(Response.Status.NOT_FOUND).entity("a file must be attached").build();
            }

            /*if (Objects.isNull(originalFileName) || originalFileName.isEmpty()) {
                return Response.status(Response.Status.UNAUTHORIZED).entity("Original filename must be given").build();
            }*/
            if (Objects.isNull(id)) {
                return Response.status(Response.Status.UNAUTHORIZED).entity("idOeuvre must be given").build();
            }
            //fichier.setOriginalFileName(originalFileName);
            oeuvre = oeuvreFacade.find(id);

            //if (inPart.size() > 1) {
            //    return Response.status(Response.Status.UNAUTHORIZED).entity("You should attach one file ").build();
            //}
            byte[] bytes = IOUtils.toByteArray(uploadedInputStream);
            oeuvre.setImage(bytes);
            oeuvreFacade.edit(oeuvre);

//          for (final InputPart inputPart : inPart) {
//                // Recuperation du nom original du fichier a partir du Header
//                //final MultivaluedMap<String, String> headers = inputPart.getHeaders();
//                //fileName = Utils.parseFileName(headers);
//
//                // Creation du fichier a partir du Stream recupere
//                final InputStream istream = inputPart.getBody(InputStream.class, null);
//
//                byte[] bytes = IOUtils.toByteArray(istream);
//                oeuvre.setImage(bytes);
//                oeuvreFacade.edit(oeuvre);
//            }
            //fichier = fichierService.createFichier(fichier);
        } catch (IOException e) {
            //logger.warn("Une erreur est survenue lors de l'ajout de la piece de code ", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
        return Response.status(Response.Status.CREATED).entity("Image sauvegardée").build();

    }

    @GET
    @Produces({MediaType.APPLICATION_OCTET_STREAM})
    @Path("/oeuvre/{id}")
    public Response findOeuvreImage(@PathParam("id") Integer id) {
        try {
            Oeuvre oeuvre = oeuvreFacade.find(id);
            final ResponseBuilder response = Response.ok(oeuvre.getImage());
            response.header("Content-Disposition", "attachment;filename=" + "image.jpg");

            return response.build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_OCTET_STREAM})
    @Path("/oeuvre/miniature/{id}")
    public Response findOeuvreMiniature(@PathParam("id") Integer id) {
        try {
            Oeuvre oeuvre = oeuvreFacade.find(id);
            final ResponseBuilder response = Response.ok(oeuvre.getMiniature());
            response.header("Content-Disposition", "attachment;filename=" + "miniature.jpg");

            return response.build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_OCTET_STREAM})
    @Path("/artiste/{id}")
    public Response findArtisteImage(@PathParam("id") Integer id) {
        try {
            Artiste artiste = artisteFacade.find(id);
            final ResponseBuilder response = Response.ok(artiste.getPhoto());
            response.header("Content-Disposition", "attachment;filename=" + "image.jpg");

            return response.build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }

    }

}
