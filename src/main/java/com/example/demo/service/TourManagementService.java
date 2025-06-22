package com.example.demo.service;


import com.example.demo.entity.Tour;

import java.util.List;

public interface TourManagementService {
    List<Tour> getAllTours();

    Tour addTour(Tour Tour);

    void deleteTour(Long id);

    Tour updateTour(Tour Tour);

    Tour getTourById(Long id);
}
