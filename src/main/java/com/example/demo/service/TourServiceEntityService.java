package com.example.demo.service;

import com.example.demo.response.TourService;

import java.util.List;

public interface TourServiceEntityService {
    List<TourService> getAllTourServices();
    TourService getTourServiceById(Long id);
    TourService addTourService(TourService tourService);
    TourService updateTourService(TourService tourService);
    void deleteTourService(Long id);
}
