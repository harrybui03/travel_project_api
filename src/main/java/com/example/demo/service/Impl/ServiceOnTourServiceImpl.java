package com.example.demo.service.impl;

import com.example.demo.entity.ServiceOnTour;
import com.example.demo.repository.ServiceOnTourRepository;
import com.example.demo.repository.TourRepository;
import com.example.demo.repository.TourServiceRepository;
import com.example.demo.service.ServiceOnTourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceOnTourServiceImpl implements ServiceOnTourService {
    @Autowired
    private ServiceOnTourRepository repository;

    @Autowired
    private TourRepository tourRepository;

    @Autowired
    private TourServiceRepository tourServiceRepository;

    @Override
    public List<ServiceOnTour> getAll() {
        return repository.findAll();
    }

    @Override
    public ServiceOnTour add(ServiceOnTour serviceOnTour) {
        return repository.save(serviceOnTour);
    }
}
