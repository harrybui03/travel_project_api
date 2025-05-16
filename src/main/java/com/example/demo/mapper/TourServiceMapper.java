package com.example.demo.mapper;

import com.example.demo.response.TourService;

public class TourServiceMapper {

    // map entity to dto
    public static TourService mapToTourServiceDTO(com.example.demo.entity.TourService tourService) {
        TourService tourServiceDTO = new TourService();
        tourServiceDTO.setId(tourService.getId());
        tourServiceDTO.setName(tourService.getName());
        tourServiceDTO.setType(tourService.getType());
        tourServiceDTO.setPrice(tourService.getPrice());
        if (tourService.getPartner() != null) {
            tourServiceDTO.setPartner(PartnerMapper.mapToPartnerDTO(tourService.getPartner()));
        }
        return tourServiceDTO;

    }


    // map dto to entity
    public static com.example.demo.entity.TourService mapToTourServiceEntity(TourService tourServiceDTO) {
        com.example.demo.entity.TourService tourService = new com.example.demo.entity.TourService();
        tourService.setId(tourServiceDTO.getId());
        tourService.setName(tourServiceDTO.getName());
        tourService.setType(tourServiceDTO.getType());
        tourService.setPrice(tourServiceDTO.getPrice());
        if (tourServiceDTO.getPartner()!= null) {
            tourService.setPartner(PartnerMapper.mapToPartnerEntity(tourServiceDTO.getPartner()));
        }
        return tourService;
    }
}
