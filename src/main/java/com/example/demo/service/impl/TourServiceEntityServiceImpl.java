package com.example.demo.service.impl;

import com.example.demo.response.Partner;
import com.example.demo.response.TourService;
import com.example.demo.mapper.PartnerMapper;
import com.example.demo.mapper.TourServiceMapper;
import com.example.demo.repository.PartnerRepository;
import com.example.demo.repository.TourServiceRepository;
import com.example.demo.service.TourServiceEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TourServiceEntityServiceImpl implements TourServiceEntityService {
    @Autowired
    private TourServiceRepository tourServiceRepository;

    @Autowired
    private PartnerRepository partnerRepository;


    @Override
    public List<TourService> getAllTourServices() {
        List<com.example.demo.entity.TourService> tourServiceEntities = new ArrayList<>();
        tourServiceEntities = tourServiceRepository.findAll();
        return tourServiceEntities.stream().map(TourServiceMapper::mapToTourServiceDTO).collect(Collectors.toList());
    }

    @Override
    public TourService getTourServiceById(Long id) {
        Optional<com.example.demo.entity.TourService> optionalTourService = tourServiceRepository.findById(id);
        com.example.demo.entity.TourService tourService = optionalTourService.get();
        return TourServiceMapper.mapToTourServiceDTO(tourService);
    }

    @Override
    public TourService addTourService(TourService tourServiceDTO) {
        com.example.demo.entity.Partner partner = new com.example.demo.entity.Partner();
        Partner partnerDTO = tourServiceDTO.getPartner();
        if (partnerDTO != null) {
            partner = PartnerMapper.mapToPartnerEntity(partnerDTO);
            partnerRepository.save(partner);

        }

        com.example.demo.entity.TourService tourService = TourServiceMapper.mapToTourServiceEntity(tourServiceDTO);
        tourService.setPartner(partner);
        tourServiceRepository.save(tourService);
        return TourServiceMapper.mapToTourServiceDTO(tourService);
    }

    @Override
    public TourService updateTourService(TourService tourServiceDTO) {
        // find existing tourservice by id
        Optional<com.example.demo.entity.TourService> optionalTourService = tourServiceRepository.findById(tourServiceDTO.getId());
        // do partial update of tourserice
        com.example.demo.entity.TourService tourService = optionalTourService.get();
        updateTourServiceEntityfromDTO(tourService, tourServiceDTO);
        return TourServiceMapper.mapToTourServiceDTO(tourService);

    }

    @Override
    public void deleteTourService(Long id) {
        tourServiceRepository.deleteById(id);
    }


    private void updateTourServiceEntityfromDTO(com.example.demo.entity.TourService tourServiceToUpdate, TourService tourService) {
        if (tourService.getId() != null) {
            tourServiceToUpdate.setId(tourService.getId());
        }
        if (tourService.getName() != null) {
            tourServiceToUpdate.setName(tourService.getName());

        }
        if (tourService.getPrice()!=null) {
            tourServiceToUpdate.setPrice(tourService.getPrice());
        }

        if (tourService.getType() != null) {
            tourServiceToUpdate.setType(tourService.getType());
        }

        if (tourService.getPartner() != null) {
            tourServiceToUpdate.setPartner(PartnerMapper.mapToPartnerEntity(tourService.getPartner()));
        }
    }
}
