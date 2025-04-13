package com.example.demo.mapper;

import com.example.demo.dto.TourServiceDTO;
import com.example.demo.entity.TourService;

public class TourServiceMapper {

    // map entity to dto
    public static TourServiceDTO mapToTourServiceDTO(TourService tourService) {
        TourServiceDTO tourServiceDTO = new TourServiceDTO();
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
    public static TourService mapToTourServiceEntity(TourServiceDTO tourServiceDTO) {
        TourService tourService = new TourService();
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
