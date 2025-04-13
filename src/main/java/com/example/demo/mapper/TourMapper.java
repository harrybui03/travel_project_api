package com.example.demo.mapper;

import com.example.demo.dto.TourDTO;
import com.example.demo.entity.Tour;

public class TourMapper {

    public static TourDTO mapToTourDTO(Tour tour) {
        TourDTO tourDTO = new TourDTO();
        tourDTO.setId(tour.getId());
        tourDTO.setTourName(tour.getTourName());
        tourDTO.setTourDuration(tour.getTourDuration());
        tourDTO.setDescription(tour.getDescription());
        tourDTO.setTransportation(tour.getTransportation());
        tourDTO.setPrice(tour.getPrice());
        tourDTO.setMaxCustomer(tour.getMaxCustomer());

        return tourDTO;
    }


    public static  Tour mapToTourEntity(TourDTO tourDTO) {
        Tour tour = new Tour();
        tour.setId(tourDTO.getId());
        tour.setTourName(tourDTO.getTourName());
        tour.setTourDuration(tourDTO.getTourDuration());
        tour.setDescription(tourDTO.getDescription());
        tour.setDescription(tourDTO.getDescription());
        tour.setTransportation(tourDTO.getTransportation());
        tour.setPrice(tourDTO.getPrice());
        tour.setMaxCustomer(tourDTO.getMaxCustomer());

        return tour;
    }

}
