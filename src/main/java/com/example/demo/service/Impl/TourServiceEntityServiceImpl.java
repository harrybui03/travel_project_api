package com.example.demo.service.impl;


import com.example.demo.entity.TourService;
import com.example.demo.repository.PartnerRepository;
import com.example.demo.repository.TourServiceRepository;
import com.example.demo.service.TourServiceEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TourServiceEntityServiceImpl implements TourServiceEntityService {
    @Autowired
    private TourServiceRepository tourServiceRepository;

    @Autowired
    private PartnerRepository partnerRepository;


    @Override
    public List<TourService> getAllTourServices() {
        List<TourService> tourServiceEntities = new ArrayList<>();
        return tourServiceRepository.findAll();

    }

    @Override
    public TourService getTourServiceById(Long id) {
        Optional<TourService> optionalTourService = tourServiceRepository.findById(id);
        return optionalTourService.get();

    }

    @Override
    public TourService addTourService(TourService tourService) {
        return tourServiceRepository.save(tourService);
    }

    @Override
    public TourService updateTourService(TourService tourService) {
        // find existing tourservice by id
        Optional<TourService> optionalTourService = tourServiceRepository.findById(tourService.getId());
        // do partial update of tourserice
        TourService tourServiceToUpdate = optionalTourService.get();
        updateTourService(tourServiceToUpdate, tourService);
        return tourServiceRepository.save(tourServiceToUpdate);

    }

    @Override
    public void deleteTourService(Long id) {
        tourServiceRepository.deleteById(id);
    }


    private void updateTourService(TourService tourServiceToUpdate, TourService tourService) {
        if (tourService.getId() != null) {
            tourServiceToUpdate.setId(tourService.getId());
        }
        if (tourService.getName() != null) {
            tourServiceToUpdate.setName(tourService.getName());

        }
        if (tourService.getPrice() != null) {
            tourServiceToUpdate.setPrice(tourService.getPrice());
        }

        if (tourService.getType() != null) {
            tourServiceToUpdate.setType(tourService.getType());
        }

        if (tourService.getPartnerId() != null) {
            tourServiceToUpdate.setPartnerId(tourService.getPartnerId());

        }
    }
}
