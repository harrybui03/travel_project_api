package com.example.demo.service;

import com.example.demo.dto.TourServiceDTO;

import java.util.List;

public interface TourServiceEntityService {
    List<TourServiceDTO> getAllTourServices();
    TourServiceDTO getTourServiceById(Long id);
    TourServiceDTO addTourService(TourServiceDTO tourServiceDTO);
    TourServiceDTO updateTourService(TourServiceDTO tourServiceDTO);
    void deleteTourService(Long id);
}
