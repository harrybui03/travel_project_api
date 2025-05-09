package com.example.demo.repository;

import com.example.demo.entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TourRepository extends JpaRepository<Tour, Long> {
    @Query("SELECT t from Tour t WHERE LOWER(t.tourName) LIKE %:keyword%")
    List<Tour> searchTourByTourName(@Param("keyword") String keyword);
}
