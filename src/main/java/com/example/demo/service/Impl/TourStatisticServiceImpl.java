package com.example.demo.service.impl;

import com.example.demo.dto.TourBookingInfo;
import com.example.demo.dto.TourScheduleInfo;
import com.example.demo.dto.TourStat;
import com.example.demo.entity.Tour;
import com.example.demo.entity.TourBooking;
import com.example.demo.entity.TourSchedule;
import com.example.demo.repository.TourBookingRepository;
import com.example.demo.repository.TourRepository;
import com.example.demo.repository.TourScheduleRepository;
import com.example.demo.service.TourStatisticService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
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

        Map<Tour, List<TourSchedule>> toursWithSchedules = tourSchedulesInRange.stream()
                .collect(Collectors.groupingBy(TourSchedule::getTour));

        return toursWithSchedules.entrySet().stream()
                .map(entry -> {
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

                    return new TourStat(tour.getId(), tour.getTourName(), totalTickets, totalRevenue);
                })
                .toList();
    }

    @Override
    public List<TourScheduleInfo> GetListTourSchedule(Long tourId) {
        return tourScheduleRepository.findByTour_Id(tourId)
                .stream()
                .map(tourSchedule -> {
                    List<TourBooking> bookings = tourBookingRepository.findByTourSchedule(tourSchedule);
                    long totalTickets = bookings.stream().mapToInt(TourBooking::getNumberOfCustomer).sum();
                    BigDecimal totalRevenue = bookings.stream()
                            .filter(booking -> booking.getTourSchedule().getTour().getPrice() != null)
                            .map(booking -> BigDecimal.valueOf(booking.getNumberOfCustomer())
                                    .multiply(BigDecimal.valueOf(booking.getTourSchedule().getTour().getPrice())))
                            .reduce(BigDecimal.ZERO, BigDecimal::add);

                    LocalDate startDate = tourSchedule.getDepartureDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    LocalDate endDate = tourSchedule.getReturnDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    String tourGuideName = (tourSchedule.getEmployee() != null) ?
                            tourSchedule.getEmployee().getFullname()  :
                            generateRandomName();

                    return new TourScheduleInfo(
                            tourSchedule.getId(),
                            startDate,
                            endDate,
                            tourGuideName,
                            totalTickets,
                            totalRevenue
                    );
                })
                .toList();
    }

    @Override
    public List<TourBookingInfo> GetListTourBooking(Long tourScheduleId) {
        return tourBookingRepository.findByTourSchedule_Id(tourScheduleId)
                .stream()
                .map(tourBooking -> new TourBookingInfo(
                        tourBooking.getId(),
                        tourBooking.getCustomer().getFullname(),
                        tourBooking.getNumberOfCustomer(),
                        BigDecimal.valueOf(tourBooking.getNumberOfCustomer()).multiply(BigDecimal.valueOf(tourBooking.getTourSchedule().getTour().getPrice()))
                ))
                .toList();
    }


    private final Random random = new Random();
    private final List<String> firstNames = List.of("Nguyen", "Tran", "Le", "Pham", "Hoang", "Vu", "Phan", "Truong", "Bui", "Doan");
    private final List<String> lastNames = List.of("Van", "Thi", "Minh", "Thanh", "Duc", "Huy", "Anh", "Tuan", "Linh", "Mai");
    private String generateRandomName() {
        String firstName = firstNames.get(random.nextInt(firstNames.size()));
        String lastName = lastNames.get(random.nextInt(lastNames.size()));
        return firstName + " " + lastName;
    }
}
