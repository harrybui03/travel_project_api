package com.example.demo.controller;

import com.example.demo.response.TourBookingStat;
import com.example.demo.response.TourScheduleStat;
import com.example.demo.entity.TourStat;
import com.example.demo.service.TourStatisticService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/statistic/tour")
public class TourStatisticController {
    private final TourStatisticService tourStatisticService;

    public TourStatisticController(TourStatisticService tourStatisticService) {
        this.tourStatisticService = tourStatisticService;
    }

    @GetMapping()
    public ResponseEntity<List<TourStat>> getTourStatistics(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<TourStat> tourStats = tourStatisticService.StatisticTour(startDate, endDate);
        return ResponseEntity.ok(tourStats);
    }

    @GetMapping("/{tourId}")
    public ResponseEntity<List<TourScheduleStat>> getTourSchedulesWithDetails(@PathVariable Long tourId) {
        List<TourScheduleStat> tourScheduleStats = tourStatisticService.GetListTourSchedule(tourId);
        return ResponseEntity.ok(tourScheduleStats);
    }

    @GetMapping("/schedule/{tourScheduleId}/bookings")
    public ResponseEntity<List<TourBookingStat>> getBookingsByTourScheduleId(@PathVariable Long tourScheduleId) {
        List<TourBookingStat> tourBookingStats = tourStatisticService.GetListTourBooking(tourScheduleId);
        return ResponseEntity.ok(tourBookingStats);
    }
}
