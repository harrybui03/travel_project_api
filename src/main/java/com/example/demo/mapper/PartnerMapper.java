package com.example.demo.mapper;

import com.example.demo.response.Partner;

public class PartnerMapper {

    //map entity to dto
    public static Partner mapToPartnerDTO(com.example.demo.entity.Partner partner) {
        Partner partnerDTO = new Partner();
        partnerDTO.setId(partner.getId());
        partnerDTO.setName(partner.getName());
        partnerDTO.setAddress(partner.getAddress());
        partnerDTO.setEmail(partner.getEmail());
        partnerDTO.setPhoneNumber(partner.getPhoneNumber());
        return partnerDTO;
    }

    //map dto to entity
    public static com.example.demo.entity.Partner mapToPartnerEntity(Partner partnerDTO) {
        com.example.demo.entity.Partner partner = new com.example.demo.entity.Partner();
        partner.setId(partnerDTO.getId());
        partner.setName(partnerDTO.getName());
        partner.setAddress(partnerDTO.getAddress());
        partner.setEmail(partnerDTO.getEmail());
        partner.setPhoneNumber(partnerDTO.getPhoneNumber());
        return partner;
    }
}
