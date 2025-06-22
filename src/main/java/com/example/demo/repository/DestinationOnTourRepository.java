package com.example.demo.repository;

import com.example.demo.entity.DestinationOnTour;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DestinationOnTourRepository extends JpaRepository<DestinationOnTour, Long> {
    List<DestinationOnTour> findByTourId(Long tourId);

    void deleteByTourId(Long tourId);
}