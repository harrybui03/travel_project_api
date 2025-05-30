package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @OneToMany(mappedBy = "tour", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<TourSchedule> tourSchedules;

    public Tour() {
    }
    public List<TourSchedule> getTourSchedules() {
        return tourSchedules;
    }

    public void setTourSchedules(List<TourSchedule> tourSchedules) {
        this.tourSchedules = tourSchedules;
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

    public List<TourSchedule> getTourSchedule() {
        return tourSchedule;
    }

    public void setTourSchedule(List<TourSchedule> tourSchedule) {
        this.tourSchedule = tourSchedule;
    }
    @Transient
    private List<TourSchedule> tourSchedule = new ArrayList<>();
}