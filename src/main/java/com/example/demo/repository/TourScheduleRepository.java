package com.example.demo.repository;

import com.example.demo.entity.Employee;
import com.example.demo.entity.TourGuide;
import com.example.demo.entity.TourSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface TourScheduleRepository extends JpaRepository<TourSchedule, Long> {
    List<TourSchedule> findByTourGuides(TourGuide tourGuide);
    List<TourSchedule> findByDepartureDateBetween(Date startDate, Date endDate);

    List<TourSchedule> findByTour_Id(Long tourId);

}