package sn.modelsis.signart.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import sn.modelsis.signart.Promotion;
import sn.modelsis.signart.PromotionOeuvre;
import sn.modelsis.signart.dto.PromotionDto;
import sn.modelsis.signart.exception.SignArtException;
import sn.modelsis.signart.facade.OeuvreFacade;
import sn.modelsis.signart.facade.PromotionFacade;
import sn.modelsis.signart.facade.PromotionOeuvreFacade;
import sn.modelsis.signart.facade.SousTechniqueFacade;

/**
 *
 * @author SNLOM
 */
@Stateless
@Path("promotion")
public class PromotionREST {

    @Inject
    PromotionFacade promotionFacade;
    @Inject
    PromotionOeuvreFacade promotionOeuvreFacade;
    @Inject
    OeuvreFacade oeuvreFacade;
    @Inject
    SousTechniqueFacade sousTechniqueFacade;

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(PromotionDto dto) {
        try {
            Promotion entity = dtoToEntity(dto);
            promotionFacade.create(entity);
            return Response.status(Response.Status.CREATED).entity(entityToDto(entity)).build();
        } catch (Exception e) {
            Logger.getLogger(PromotionREST.class.getName()).log(Level.SEVERE, "create/Exception", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") Integer id, PromotionDto dto) {
        try {
            Promotion entity = dtoToEntity(dto);
            promotionFacade.edit(entity);
            return Response.status(Response.Status.OK).entity(entityToDto(entity)).build();
        } catch (Exception e) {
            Logger.getLogger(PromotionREST.class.getName()).log(Level.SEVERE, "edit/Exception", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Integer id) {
        try {
            promotionFacade.remove(promotionFacade.find(id));
            return Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            Logger.getLogger(PromotionREST.class.getName()).log(Level.SEVERE, "remove/Exception", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public PromotionDto find(@PathParam("id") Integer id) {
        try {
            Promotion promotion = promotionFacade.find(id);
            return entityToDto(promotion);
        } catch (Exception e) {
            Logger.getLogger(PromotionREST.class.getName()).log(Level.SEVERE, "find/Exception", e);
            //return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            return null;
        }
    }

    @GET
    @Path("oeuvre/{idOeuvre}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<PromotionDto> findByOeuvre(@PathParam("idOeuvre") Integer idOeuvre) {
        List<PromotionDto> listDto = new ArrayList<>();
        try {
            //Les promotions qui concernent une sous technique
            List<Promotion> listP = promotionFacade.findByOeuvre(idOeuvre);
            if (listP != null) {
                listP.stream().map(entity
                        -> entityToDto(entity)
                ).forEachOrdered(dto
                        -> listDto.add(dto)
                );
            }
            //Les promotions qui concernent une oeuvre donnée
            List<PromotionOeuvre> listPE = promotionOeuvreFacade.findByOeuvre(idOeuvre);
            if (listPE != null) {
                listPE.stream().map(entity
                        -> entityToDto(entity)
                ).forEachOrdered(dto
                        -> listDto.add(dto)
                );
            }
            return listDto;
        } catch (final SignArtException e) {
            Logger.getLogger(PromotionREST.class.getName()).log(Level.SEVERE, "findByOeuvre/Exception", e);
            return listDto;
        }
    }
    
    /*@POST
    @Path("soustechnique")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(PromotionDto dto) {
        try {
            Promotion entity = dtoToEntity(dto);
            promotionFacade.create(entity);
            return Response.status(Response.Status.CREATED).entity(entityToDto(entity)).build();
        } catch (Exception e) {
            Logger.getLogger(PromotionREST.class.getName()).log(Level.SEVERE, "create/Exception", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") Integer id, PromotionDto dto) {
        try {
            Promotion entity = dtoToEntity(dto);
            promotionFacade.edit(entity);
            return Response.status(Response.Status.OK).entity(entityToDto(entity)).build();
        } catch (Exception e) {
            Logger.getLogger(PromotionREST.class.getName()).log(Level.SEVERE, "edit/Exception", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Integer id) {
        try {
            promotionFacade.remove(promotionFacade.find(id));
            return Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            Logger.getLogger(PromotionREST.class.getName()).log(Level.SEVERE, "remove/Exception", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public PromotionDto find(@PathParam("id") Integer id) {
        try {
            Promotion promotion = promotionFacade.find(id);
            return entityToDto(promotion);
        } catch (Exception e) {
            Logger.getLogger(PromotionREST.class.getName()).log(Level.SEVERE, "find/Exception", e);
            //return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            return null;
        }
    }*/

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(promotionFacade.count());
    }

    /**
     * Converts entity to Dto
     *
     * @param entity
     * @return
     */
    private PromotionDto entityToDto(Promotion entity) {
        PromotionDto dto = new PromotionDto();
        dto.setDateDebut(entity.getDateDebut());
        dto.setId(entity.getId());
        dto.setDateFin(entity.getDateFin());
        dto.setMontantReduction(entity.getMontantReduction());
        dto.setTauxReduction(entity.getTauxReduction());
        //dto.setIdSousTechnique(entity.getIdSousTechnique().getId());
        return dto;
    }

    /**
     *
     * @param entity
     * @return
     */
    private PromotionDto entityToDto(PromotionOeuvre entity) {
        PromotionDto dto = new PromotionDto();
        dto.setDateDebut(entity.getDateDebut());
        dto.setId(entity.getId());
        dto.setDateFin(entity.getDateFin());
        dto.setMontantReduction(entity.getMontantReduction());
        dto.setTauxReduction(entity.getTauxReduction());
        //dto.setIdSousTechnique(entity.getIdSousTechnique().getId());
        return dto;
    }

    /**
     *
     * @param dto
     * @return
     */
    private Promotion dtoToEntity(PromotionDto dto) {
        Promotion entity = new Promotion();
        entity.setId(dto.getId());
        entity.setMontantReduction(dto.getMontantReduction());
        entity.setTauxReduction(dto.getTauxReduction());
        entity.setDateDebut(dto.getDateDebut());
        entity.setDateFin(dto.getDateFin());
        entity.setIdSousTechnique(sousTechniqueFacade.find(dto.getIdSousTechnique()));
        return entity;
    }

    /**
     *
     * @param dto
     * @return
     */
    private PromotionOeuvre dtoToPromotionOeuvre(PromotionDto dto) {
        PromotionOeuvre entity = new PromotionOeuvre();
        entity.setId(dto.getId());
        entity.setMontantReduction(dto.getMontantReduction());
        entity.setTauxReduction(dto.getTauxReduction());
        entity.setDateDebut(dto.getDateDebut());
        entity.setDateFin(dto.getDateFin());
        entity.setIdOeuvre(oeuvreFacade.find(dto.getIdOeuvre()));
        return entity;
    }
}
