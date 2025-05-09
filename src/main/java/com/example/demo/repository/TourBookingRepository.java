package com.example.demo.repository;

import com.example.demo.entity.TourBooking;
import com.example.demo.entity.TourSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TourBookingRepository extends JpaRepository<TourBooking, Long> {
    List<TourBooking> findByTourSchedule(TourSchedule tourSchedule);

    List<TourBooking> findByTourSchedule_Id(Long tourScheduleId);
}