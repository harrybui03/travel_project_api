package com.example.demo.service.Impl;

import com.example.demo.dto.TourDTO;
import com.example.demo.entity.Tour;
import com.example.demo.mapper.TourMapper;
import com.example.demo.repository.TourRepository;
import com.example.demo.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TourServiceImpl implements TourService {

    @Autowired
    private TourRepository tourRepository;


    @Override
    public List<TourDTO> getAllTours() {
       List<Tour> tours = new ArrayList<>();
       tours = tourRepository.findAll();
       return tours.stream().map(TourMapper::mapToTourDTO).collect(Collectors.toList());
    }

    @Override
    public TourDTO addTour(TourDTO tourDTO) {
        Tour tour = new Tour();
        tour = TourMapper.mapToTourEntity(tourDTO);
        tourRepository.save(tour);
        return TourMapper.mapToTourDTO(tour);
    }

    @Override
    public void deleteTour(Long id) {
        tourRepository.deleteById(id);

    }

    @Override
    public TourDTO updateTour(TourDTO tourDTO) {
        Optional<Tour> optionalTour = tourRepository.findById(tourDTO.getId());

        Tour tour = optionalTour.get();
        updateTourEntityFromDTO(tour, tourDTO);
        tourRepository.save(tour);
        return TourMapper.mapToTourDTO(tour);
    }

    private void updateTourEntityFromDTO(Tour tour, TourDTO tourDTO) {
        if (tourDTO.getId()!= null) {
            tour.setId(tourDTO.getId());
        }
        if (tourDTO.getTourName()!=null) {
            tour.setTourName(tourDTO.getTourName());
        }
        if (tourDTO.getTourDuration()!=null) {
            tour.setTourDuration(tourDTO.getTourDuration());
        }
        if (tourDTO.getDescription()!=null) {
            tour.setDescription(tourDTO.getDescription());
        }
        if (tourDTO.getTransportation()!=null) {
            tour.setTransportation(tourDTO.getTransportation());
        }
        if (tourDTO.getMaxCustomer() != null) {
            tour.setMaxCustomer(tourDTO.getMaxCustomer());
        }
        if (tourDTO.getPrice() != null) {
            tour.setPrice(tourDTO.getPrice());
        }
    }

    @Override
    public TourDTO getTourById(Long id) {
        Optional<Tour> optionalTour = tourRepository.findById(id);
        Tour tour = optionalTour.get();
        return TourMapper.mapToTourDTO(tour);
    }
}
