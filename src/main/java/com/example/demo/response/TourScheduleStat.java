package com.example.demo.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TourScheduleStat {
    private Long tourScheduleId;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<String> tourGuide;
    private Long totalTicket;
    private BigDecimal totalRevenue;

    private List<TourBookingStat> tourBookingStatList;
    public TourScheduleStat() {
    }

    public TourScheduleStat(Long tourScheduleId, LocalDate startDate, LocalDate endDate,  Long totalTicket, BigDecimal totalRevenue) {
        this.tourScheduleId = tourScheduleId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalTicket = totalTicket;
        this.totalRevenue = totalRevenue;
    }

    public Long getTourScheduleId() {
        return tourScheduleId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public List<String> getTourGuide() {
        return tourGuide;
    }

    public Long getTotalTicket() {
        return totalTicket;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public void setTourScheduleId(Long tourScheduleId) {
        this.tourScheduleId = tourScheduleId;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setTourGuide(List<String> tourGuide) {
        this.tourGuide = tourGuide;
    }

    public void setTotalTicket(Long totalTicket) {
        this.totalTicket = totalTicket;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public List<TourBookingStat> getTourBookingStatList() {
        return tourBookingStatList;
    }

    public void setTourBookingStatList(List<TourBookingStat> tourBookingStatList) {
        this.tourBookingStatList = tourBookingStatList;
    }
}