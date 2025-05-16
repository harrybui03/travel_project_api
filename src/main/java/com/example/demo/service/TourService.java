package com.example.demo.service;

import com.example.demo.response.Tour;

import java.util.List;

public interface TourService {
    List<Tour> getAllTours();
    Tour addTour(Tour tour);
    void deleteTour(Long id);
    Tour updateTour(Tour tour);
    Tour getTourById(Long id);
}
