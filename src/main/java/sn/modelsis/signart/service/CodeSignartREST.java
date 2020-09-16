/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.modelsis.signart.service;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import sn.modelsis.signart.CodeSignart;
import sn.modelsis.signart.dto.CodeSignartDto;
import sn.modelsis.signart.exception.SignArtException;
import sn.modelsis.signart.facade.CodeSignartFacade;

/**
 *
 * @author snfayemp
 */
@Stateless
@Path("codeSignart")
public class CodeSignartREST {
    
    @Inject
    CodeSignartFacade codeSignartFacade;
    
    @GET
    @Path("findByCode/{code}")
    @Produces({MediaType.APPLICATION_JSON})
    public CodeSignartDto find(@PathParam("code") String code) throws SignArtException {
        CodeSignart entity = codeSignartFacade.findByCode(code);
        return entityToDto(entity);
        
    }
    
    private CodeSignartDto entityToDto(CodeSignart entity) {
        CodeSignartDto dto = new CodeSignartDto();
        dto.setId(entity.getId());
        dto.setIdAbonne(entity.getIdAbonne().getId());
        return dto;
    }
    
}
