package com.example.demo.repository;

import com.example.demo.entity.TourSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourScheduleRepository extends JpaRepository<TourSchedule, Long> {}