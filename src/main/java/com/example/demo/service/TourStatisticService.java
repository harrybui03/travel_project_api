package com.example.demo.service;

import com.example.demo.dto.TourBookingInfo;
import com.example.demo.dto.TourScheduleInfo;
import com.example.demo.dto.TourStat;

import java.time.LocalDate;
import java.util.List;

public interface TourStatisticService {
    List<TourStat> StatisticTour(LocalDate startDate, LocalDate endDate);
    List<TourScheduleInfo> GetListTourSchedule(Long tourId);
    List<TourBookingInfo> GetListTourBooking(Long tourScheduleId);
}
