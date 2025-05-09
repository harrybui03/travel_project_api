package com.example.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TourScheduleInfo {
    private Long tourScheduleId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String tourGuide;
    private Long totalTicket;
    private BigDecimal totalRevenue;

    public TourScheduleInfo() {
    }

    public TourScheduleInfo(Long tourScheduleId, LocalDate startDate, LocalDate endDate, String tourGuide, Long totalTicket, BigDecimal totalRevenue) {
        this.tourScheduleId = tourScheduleId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.tourGuide = tourGuide;
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

    public String getTourGuide() {
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

    public void setTourGuide(String tourGuide) {
        this.tourGuide = tourGuide;
    }

    public void setTotalTicket(Long totalTicket) {
        this.totalTicket = totalTicket;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}