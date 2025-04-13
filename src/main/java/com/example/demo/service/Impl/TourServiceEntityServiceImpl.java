package com.example.demo.service.Impl;

import com.example.demo.dto.PartnerDTO;
import com.example.demo.dto.TourServiceDTO;
import com.example.demo.entity.Partner;
import com.example.demo.entity.TourService;
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
    public List<TourServiceDTO> getAllTourServices() {
        List<TourService> tourServiceEntities = new ArrayList<>();
        tourServiceEntities = tourServiceRepository.findAll();
        return tourServiceEntities.stream().map(TourServiceMapper::mapToTourServiceDTO).collect(Collectors.toList());
    }

    @Override
    public TourServiceDTO getTourServiceById(Long id) {
        Optional<TourService> optionalTourService = tourServiceRepository.findById(id);
        TourService tourService = optionalTourService.get();
        return TourServiceMapper.mapToTourServiceDTO(tourService);
    }

    @Override
    public TourServiceDTO addTourService(TourServiceDTO tourServiceDTO) {
        Partner partner = new Partner();
        PartnerDTO partnerDTO = tourServiceDTO.getPartner();
        if (partnerDTO != null) {
            partner = PartnerMapper.mapToPartnerEntity(partnerDTO);
            partnerRepository.save(partner);

        }

        TourService tourService = TourServiceMapper.mapToTourServiceEntity(tourServiceDTO);
        tourService.setPartner(partner);
        tourServiceRepository.save(tourService);
        return TourServiceMapper.mapToTourServiceDTO(tourService);
    }

    @Override
    public TourServiceDTO updateTourService(TourServiceDTO tourServiceDTO) {
        // find existing tourservice by id
        Optional<TourService> optionalTourService = tourServiceRepository.findById(tourServiceDTO.getId());
        // do partial update of tourserice
        TourService tourService = optionalTourService.get();
        updateTourServiceEntityfromDTO(tourService, tourServiceDTO);
        return TourServiceMapper.mapToTourServiceDTO(tourService);

    }

    @Override
    public void deleteTourService(Long id) {
        tourServiceRepository.deleteById(id);
    }


    private void updateTourServiceEntityfromDTO(TourService tourServiceToUpdate, TourServiceDTO tourServiceDTO) {
        if (tourServiceDTO.getId() != null) {
            tourServiceToUpdate.setId(tourServiceDTO.getId());
        }
        if (tourServiceDTO.getName() != null) {
            tourServiceToUpdate.setName(tourServiceDTO.getName());

        }
        if (tourServiceDTO.getPrice()!=null) {
            tourServiceToUpdate.setPrice(tourServiceDTO.getPrice());
        }

        if (tourServiceDTO.getType() != null) {
            tourServiceToUpdate.setType(tourServiceDTO.getType());
        }

        if (tourServiceDTO.getPartner() != null) {
            tourServiceToUpdate.setPartner(PartnerMapper.mapToPartnerEntity(tourServiceDTO.getPartner()));
        }
    }
}
