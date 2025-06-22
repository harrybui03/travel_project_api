package com.example.demo.service;

import com.example.demo.entity.TourService;

import java.util.List;

public interface TourServiceEntityService {
    List<TourService> getAllTourServices();

    TourService getTourServiceById(Long id);

    TourService addTourService(TourService TourService);

    TourService updateTourService(TourService TourService);
    void deleteTourService(Long id);
}
