package com.example.demo.service.impl;

import com.example.demo.response.TourBookingInfo;
import com.example.demo.response.TourScheduleInfo;
import com.example.demo.response.TourStat;
import com.example.demo.entity.Tour;
import com.example.demo.entity.TourBooking;
import com.example.demo.entity.TourSchedule;
import com.example.demo.repository.TourBookingRepository;
import com.example.demo.repository.TourRepository;
import com.example.demo.repository.TourScheduleRepository;
import com.example.demo.service.TourStatisticService;
import com.example.demo.utils.Utils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service
public class TourStatisticServiceImpl implements TourStatisticService {
    TourRepository tourRepository;
    TourScheduleRepository tourScheduleRepository;
    TourBookingRepository tourBookingRepository;

    public TourStatisticServiceImpl(TourRepository tourRepository, TourScheduleRepository tourScheduleRepository, TourBookingRepository tourBookingRepository) {
        this.tourRepository = tourRepository;
        this.tourScheduleRepository = tourScheduleRepository;
        this.tourBookingRepository = tourBookingRepository;
    }

    @Override
    public List<TourStat> StatisticTour(LocalDate startDate, LocalDate endDate) {
        Date startDateConverted = Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDateConverted = Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).plusDays(1).minusNanos(1).toInstant());

        List<TourSchedule> tourSchedulesInRange = tourScheduleRepository.findByDepartureDateBetween(startDateConverted, endDateConverted);
        Map<Tour, List<TourSchedule>> toursWithSchedules = new HashMap<>();

        for (TourSchedule tourSchedule : tourSchedulesInRange) {
            Tour tour = tourSchedule.getTour();
            if (!toursWithSchedules.containsKey(tour)) {
                toursWithSchedules.put(tour, new ArrayList<>());
            }
            toursWithSchedules.get(tour).add(tourSchedule);
        }

        List<TourStat> tourStats = new ArrayList<>();
        for (Map.Entry<Tour, List<TourSchedule>> entry : toursWithSchedules.entrySet()) {
            Tour tour = entry.getKey();
            List<TourSchedule> schedulesForTour = tourScheduleRepository.findByTour_Id(tour.getId());

            int totalTickets = 0;
            double totalRevenue = 0.0;

            for (TourSchedule schedule : schedulesForTour) {
                List<TourBooking> bookings = tourBookingRepository.findByTourSchedule(schedule);
                for (TourBooking booking : bookings) {
                    totalTickets += booking.getNumberOfCustomer();
                    totalRevenue += (booking.getNumberOfCustomer() * tour.getPrice());
                }
            }
            tourStats.add(new TourStat(tour.getId(), tour.getTourName(), totalTickets, totalRevenue));
        }
        return tourStats;
    }

    @Override
    public List<TourScheduleInfo> GetListTourSchedule(Long tourId) {
        List<TourSchedule> tourSchedules = tourScheduleRepository.findByTour_Id(tourId);
        List<TourScheduleInfo> tourScheduleInfos = new ArrayList<>();

        for (TourSchedule tourSchedule : tourSchedules) {
            List<TourBooking> bookings = tourBookingRepository.findByTourSchedule(tourSchedule);
            long totalTickets = 0;
            BigDecimal totalRevenue = BigDecimal.ZERO;

            for (TourBooking booking : bookings) {
                totalTickets += booking.getNumberOfCustomer();
                if (booking.getTourSchedule().getTour().getPrice() != null) {
                    totalRevenue = totalRevenue.add(BigDecimal.valueOf(booking.getNumberOfCustomer())
                            .multiply(BigDecimal.valueOf(booking.getTourSchedule().getTour().getPrice())));
                }
            }

            LocalDate startDate = tourSchedule.getDepartureDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate endDate = tourSchedule.getReturnDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            String tourGuideName = "";

            tourScheduleInfos.add(new TourScheduleInfo(
                    tourSchedule.getId(),
                    startDate,
                    endDate,
                    tourGuideName,
                    totalTickets,
                    totalRevenue
            ));
        }
        return tourScheduleInfos;
    }

    @Override
    public List<TourBookingInfo> GetListTourBooking(Long tourScheduleId) {
        List<TourBooking> tourBookings = tourBookingRepository.findByTourSchedule_Id(tourScheduleId);
        List<TourBookingInfo> tourBookingInfos = new ArrayList<>();

        for (TourBooking tourBooking : tourBookings) {
            BigDecimal totalBookingPrice = BigDecimal.valueOf(tourBooking.getNumberOfCustomer())
                    .multiply(BigDecimal.valueOf(tourBooking.getTourSchedule().getTour().getPrice()));
            tourBookingInfos.add(new TourBookingInfo(
                    tourBooking.getId(),
                    tourBooking.getCustomer().getFullname(),
                    tourBooking.getNumberOfCustomer(),
                    totalBookingPrice
            ));
        }
        return tourBookingInfos;
    }
}
