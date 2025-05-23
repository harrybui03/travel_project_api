package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class TourSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date departureDate;
    private Date returnDate;

    @ManyToOne
    @JoinColumn(name = "tour_id")
    private Tour tour;

    @ManyToMany
    @JoinTable(
            name = "tour_schedule_tour_guide",
            joinColumns = @JoinColumn(name = "tour_schedule_id"),
            inverseJoinColumns = @JoinColumn(name = "tour_guide_id")
    )
    private List<TourGuide> tourGuides = new ArrayList<>();

    public List<TourGuide> getTourGuides() {
        return tourGuides;
    }

    @OneToMany(mappedBy = "tourSchedule", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<TourBooking> tourBookings = new ArrayList<>();

    public List<TourBooking> getTourBookings() {
        return tourBookings;
    }

    public void setTourBookings(List<TourBooking> tourBookings) {
        this.tourBookings = tourBookings;
    }

    public void setTourGuides(List<TourGuide> tourGuides) {
        this.tourGuides = tourGuides;
    }

    public TourSchedule() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }
}