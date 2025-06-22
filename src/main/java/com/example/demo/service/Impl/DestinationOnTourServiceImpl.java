package com.example.demo.service.impl;


import com.example.demo.entity.DestinationOnTour;
import com.example.demo.repository.DestinationOnTourRepository;
import com.example.demo.repository.DestinationRepository;
import com.example.demo.repository.TourRepository;
import com.example.demo.service.DestinationOnTourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DestinationOnTourServiceImpl implements DestinationOnTourService {
    @Autowired
    private DestinationOnTourRepository repository;
    @Autowired
    private TourRepository tourRepository;

    @Autowired
    private DestinationRepository destinationRepository;

    @Override
    public List<DestinationOnTour> getAll() {
        return repository.findAll();
    }

    @Override
    public DestinationOnTour add(DestinationOnTour destinationOnTour) {
        DestinationOnTour savedEntity = repository.save(destinationOnTour);
        return savedEntity;
    }
}
