package com.example.demo.repository;

import com.example.demo.entity.ServiceOnTour;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceOnTourRepository extends JpaRepository<ServiceOnTour, Long> {
    List<ServiceOnTour> findByTourId(Long tourId);

    void deleteByTourId(Long tourId);
}