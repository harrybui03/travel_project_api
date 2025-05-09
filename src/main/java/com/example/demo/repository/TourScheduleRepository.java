package com.example.demo.repository;

import com.example.demo.entity.Employee;
import com.example.demo.entity.TourSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface TourScheduleRepository extends JpaRepository<TourSchedule, Long> {

    List<TourSchedule> findByEmployee(Employee employee);

    @Query("SELECT ts FROM TourSchedule ts " +
            "WHERE ts.employee = :employee " +
            "AND ((ts.departureDate < :returnDate AND ts.returnDate > :departureDate) " +
            "     OR ts.departureDate = :departureDate OR ts.returnDate = :returnDate)")
    List<TourSchedule> findOverlappingSchedules(
            @Param("employee") Employee employee,
            @Param("departureDate") Date departureDate,
            @Param("returnDate") Date returnDate
    );

    List<TourSchedule> findByDepartureDateBetween(Date startDate, Date endDate);

    List<TourSchedule> findByTour_Id(Long tourId);

}