package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.response.TourBookingStat;
import com.example.demo.response.TourScheduleStat;
import com.example.demo.repository.TourBookingRepository;
import com.example.demo.repository.TourRepository;
import com.example.demo.repository.TourScheduleRepository;
import com.example.demo.service.TourStatisticService;
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
            if (tour != null){
                if (!toursWithSchedules.containsKey(tour)) {
                    toursWithSchedules.put(tour, new ArrayList<>());
                }
                toursWithSchedules.get(tour).add(tourSchedule);
            }
        }

        List<TourStat> tourStats = new ArrayList<>();

        for (Map.Entry<Tour, List<TourSchedule>> entry : toursWithSchedules.entrySet()) {
            Tour tour = entry.getKey();
            List<TourSchedule> schedulesForTourInDateRange = entry.getValue();

            int tourTotalTickets = 0;
            double tourTotalRevenue = 0.0;
            List<TourScheduleStat> tourScheduleStatList = new ArrayList<>();

            for (TourSchedule schedule : schedulesForTourInDateRange) {
                List<TourBooking> bookings = tourBookingRepository.findByTourSchedule(schedule);
                long scheduleTotalTickets = 0;
                BigDecimal scheduleTotalRevenue = BigDecimal.ZERO;
                List<TourBookingStat> tourBookingStatList = new ArrayList<>();

                for (TourBooking booking : bookings) {
                    scheduleTotalTickets += booking.getNumberOfCustomer();
                    BigDecimal bookingPricePerCustomer = BigDecimal.valueOf(booking.getTourSchedule().getTour().getPrice());
                    BigDecimal totalBookingPrice = BigDecimal.valueOf(booking.getNumberOfCustomer())
                            .multiply(bookingPricePerCustomer);
                    scheduleTotalRevenue = scheduleTotalRevenue.add(totalBookingPrice);

                    tourBookingStatList.add(new TourBookingStat(
                            booking.getId(),
                            booking.getCustomer().getFullname(),
                            booking.getNumberOfCustomer(),
                            totalBookingPrice
                    ));
                }

                LocalDate scheduleStartDate = schedule.getDepartureDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate scheduleEndDate = schedule.getReturnDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                TourScheduleStat tourScheduleStat = new TourScheduleStat(
                        schedule.getId(),
                        scheduleStartDate,
                        scheduleEndDate,
                        scheduleTotalTickets,
                        scheduleTotalRevenue
                );
                tourScheduleStat.setTourBookingStatList(tourBookingStatList);
                tourScheduleStatList.add(tourScheduleStat);

                tourTotalTickets += scheduleTotalTickets;
                tourTotalRevenue += scheduleTotalRevenue.doubleValue();
            }
            tourStats.add(new TourStat(tour.getId(), tour.getTourName(), tourTotalTickets, tourTotalRevenue, tourScheduleStatList));
        }
        return tourStats;
    }

    @Override
    public List<TourScheduleStat> GetListTourSchedule(Long tourId) {
        List<TourSchedule> tourSchedules = tourScheduleRepository.findByTour_Id(tourId);
        List<TourScheduleStat> tourScheduleStats = new ArrayList<>();

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

//            tourScheduleStats.add(new TourScheduleStat(
//                    tourSchedule.getId(),
//                    startDate,
//                    endDate,
//                    tourGuideName,
//                    totalTickets,
//                    totalRevenue
//            ));
        }
        return tourScheduleStats;
    }

    @Override
    public List<TourBookingStat> GetListTourBooking(Long tourScheduleId) {
        List<TourBooking> tourBookings = tourBookingRepository.findByTourSchedule_Id(tourScheduleId);
        List<TourBookingStat> tourBookingStats = new ArrayList<>();

        for (TourBooking tourBooking : tourBookings) {
            BigDecimal totalBookingPrice = BigDecimal.valueOf(tourBooking.getNumberOfCustomer())
                    .multiply(BigDecimal.valueOf(tourBooking.getTourSchedule().getTour().getPrice()));
            tourBookingStats.add(new TourBookingStat(
                    tourBooking.getId(),
                    tourBooking.getCustomer().getFullname(),
                    tourBooking.getNumberOfCustomer(),
                    totalBookingPrice
            ));
        }
        return tourBookingStats;
    }
}
