package com.example.demo.response;


import java.io.Serializable;

public class TourStat implements Serializable {
    private Long tourId;
    private String tourName;
    private int totalTickets;
    private double totalRevenue;

    public TourStat() {
    }

    public TourStat(Long tourId, String tourName, int totalTickets, double totalRevenue) {
        this.tourId = tourId;
        this.tourName = tourName;
        this.totalTickets = totalTickets;
        this.totalRevenue = totalRevenue;
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
}