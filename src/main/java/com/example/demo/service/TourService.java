package com.example.demo.service;

import com.example.demo.dto.PartnerDTO;
import com.example.demo.dto.TourDTO;

import java.util.List;

public interface TourService {
    List<TourDTO> getAllTours();
    TourDTO addTour(TourDTO tourDTO);
    void deleteTour(Long id);
    TourDTO updateTour(TourDTO tourDTO);
    TourDTO getTourById(Long id);
}
