package com.example.demo.repository;

import com.example.demo.entity.TourBooking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourBookingRepository extends JpaRepository<TourBooking, Long> {}