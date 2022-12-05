package sn.modelsis.signart.converter;

import sn.modelsis.signart.PaymentDetails;
import sn.modelsis.signart.dto.PaymentDetailsDto;

import javax.ejb.Stateless;

@Stateless
public class PaymentDetailsConverter {

    public PaymentDetailsDto entityToDto(PaymentDetails entity) {
        PaymentDetailsDto dto = new PaymentDetailsDto();
        dto.setId(entity.getId());
        dto.setReference(entity.getReference());
        dto.setDestinataire(entity.getDestinataire());
        dto.setPreuve(entity.getPreuve());
        return dto;
    }

    public PaymentDetails dtoToEntity(PaymentDetailsDto dto) {
        PaymentDetails entity = new PaymentDetails();
        entity.setId(dto.getId());
        entity.setReference(dto.getReference());
        entity.setDestinataire(dto.getDestinataire());
        entity.setPreuve(dto.getPreuve());
        return entity;
    }

}
