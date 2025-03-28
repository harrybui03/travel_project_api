package com.example.demo.mapper;

import com.example.demo.dto.PartnerDTO;
import com.example.demo.entity.Partner;

public class PartnerMapper {

    //map entity to dto
    public static PartnerDTO mapToPartnerDTO(Partner partner) {
        PartnerDTO partnerDTO = new PartnerDTO();
        partnerDTO.setId(partner.getId());
        partnerDTO.setName(partner.getName());
        partnerDTO.setAddress(partner.getAddress());
        partnerDTO.setEmail(partner.getEmail());
        partnerDTO.setPhoneNumber(partner.getPhoneNumber());
        return partnerDTO;
    }

    //map dto to entity
    public static Partner mapToPartnerEntity(PartnerDTO partnerDTO) {
        Partner partner = new Partner();
        partner.setId(partnerDTO.getId());
        partner.setName(partnerDTO.getName());
        partner.setAddress(partnerDTO.getAddress());
        partner.setEmail(partnerDTO.getEmail());
        partner.setPhoneNumber(partnerDTO.getPhoneNumber());
        return partner;
    }
}
