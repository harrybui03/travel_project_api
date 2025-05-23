package com.example.demo.entity;


import com.example.demo.response.TourScheduleStat;

import java.io.Serializable;
import java.util.List;

public class TourStat implements Serializable {
    private Long tourId;
    private String tourName;
    private int totalTickets;
    private double totalRevenue;

    private List<TourScheduleStat> tourScheduleStatList;
    public TourStat() {
    }

    public TourStat(Long tourId, String tourName, int totalTickets, double totalRevenue, List<TourScheduleStat> tourScheduleStatList) {
        this.tourId = tourId;
        this.tourName = tourName;
        this.totalTickets = totalTickets;
        this.totalRevenue = totalRevenue;
        this.tourScheduleStatList = tourScheduleStatList;
    }


    public Long getTourId() {
        return tourId;
    }

    public String getTourName() {
        return tourName;
    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }



    public void setTourId(Long tourId) {
        this.tourId = tourId;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public List<TourScheduleStat> getTourScheduleStatList() {
        return tourScheduleStatList;
    }

    public void setTourScheduleStatList(List<TourScheduleStat> tourScheduleStatList) {
        this.tourScheduleStatList = tourScheduleStatList;
    }
}