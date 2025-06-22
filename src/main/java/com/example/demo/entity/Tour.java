package com.example.demo.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tourName;
    private String transportation;
    private Integer maxCustomer;
    private String description;
    private Double price;
    private String tourDuration;
    @Transient
    private List<TourService> tourServices;
    @Transient
    private List<Activity> activities;
    @Transient
    private List<Destination> destinations;
    public Tour() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTourDuration() {
        return tourDuration;
    }

    public void setTourDuration(String tourDuration) {
        this.tourDuration = tourDuration;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMaxCustomer() {
        return maxCustomer;
    }

    public void setMaxCustomer(Integer maxCustomer) {
        this.maxCustomer = maxCustomer;
    }

    public String getTransportation() {
        return transportation;
    }

    public void setTransportation(String transportation) {
        this.transportation = transportation;
    }

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }


    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public List<Destination> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<Destination> destinations) {
        this.destinations = destinations;
    }

    public List<TourService> getTourServices() {
        return tourServices;
    }

    public void setTourServices(List<TourService> tourServices) {
        this.tourServices = tourServices;
    }
}