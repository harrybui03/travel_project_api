package com.example.demo.service;

import com.example.demo.entity.Tour;
import com.example.demo.entity.TourSchedule;
import com.example.demo.response.TourBookingStat;
import com.example.demo.response.TourScheduleStat;
import com.example.demo.entity.TourStat;

import java.time.LocalDate;
import java.util.List;

public interface TourStatisticService {
    List<TourStat> StatisticTour(LocalDate startDate, LocalDate endDate);
    List<TourScheduleStat> GetListTourSchedule(Long tourId);
    List<TourBookingStat> GetListTourBooking(Long tourScheduleId);
}
