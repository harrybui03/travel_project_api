package com.example.demo.mapper;

import com.example.demo.response.Tour;

public class TourMapper {

    public static Tour mapToTourDTO(com.example.demo.entity.Tour tour) {
        Tour tourDTO = new Tour();
        tourDTO.setId(tour.getId());
        tourDTO.setTourName(tour.getTourName());
        tourDTO.setTourDuration(tour.getTourDuration());
        tourDTO.setDescription(tour.getDescription());
        tourDTO.setTransportation(tour.getTransportation());
        tourDTO.setPrice(tour.getPrice());
        tourDTO.setMaxCustomer(tour.getMaxCustomer());

        return tourDTO;
    }


    public static com.example.demo.entity.Tour mapToTourEntity(Tour tourDTO) {
        com.example.demo.entity.Tour tour = new com.example.demo.entity.Tour();
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
