package sn.modelsis.signart.service;

import sn.modelsis.signart.LignePaiement;
import sn.modelsis.signart.PaymentDetails;
import sn.modelsis.signart.converter.PaymentDetailsConverter;
import sn.modelsis.signart.dto.LignePaiementDto;
import sn.modelsis.signart.dto.PaymentDetailsDto;
import sn.modelsis.signart.facade.PaymentDetailsFacade;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Stateless
@Path("paymentDetails")
public class PaymentDetailsREST extends AbstractFacade<PaymentDetails> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    @Inject
    PaymentDetailsConverter paymentDetailsConverter;

    @Inject
    PaymentDetailsFacade paymentDetailsFacade;

    public PaymentDetailsREST() {
        super(PaymentDetails.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(PaymentDetails entity) {
        super.create(entity);
    }

    @POST
    @Path("postAll")
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(PaymentDetailsDto[] tabPaymentDetails) {
        for(int i=0;i < tabPaymentDetails.length;i++){
            super.create(paymentDetailsConverter.dtoToEntity(tabPaymentDetails[i]));
        }
    }

    @POST
    @Path("create")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(PaymentDetailsDto dto) {
        PaymentDetails entity = paymentDetailsConverter.dtoToEntity(dto);
        paymentDetailsFacade.create(entity);
        return Response.status(Response.Status.CREATED).entity(entity).build();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, PaymentDetails entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public PaymentDetailsDto find(@PathParam("id") Integer id) {
        return paymentDetailsConverter.entityToDto(super.find(id));
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public List<PaymentDetailsDto> findAllPays() {
        List<PaymentDetailsDto> listDto = new ArrayList<>();
        List<PaymentDetails> listEnt = paymentDetailsFacade.findAll();
        if (listEnt != null) {
            listEnt.stream().map(entity ->
                    paymentDetailsConverter.entityToDto(entity)
            ).forEachOrdered(dto ->
                    listDto.add(dto)
            );
        }
        return listDto;
    }
}
