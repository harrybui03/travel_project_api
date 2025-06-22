package com.example.demo.repository;

import com.example.demo.entity.ActivityOnTour;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityOnTourRepository extends JpaRepository<ActivityOnTour, Long> {
    List<ActivityOnTour> findByTourId(Long tourId);

    void deleteByTourId(Long tourId);
}
