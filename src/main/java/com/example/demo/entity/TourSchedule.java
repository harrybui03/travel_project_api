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

    @OneToMany(mappedBy = "tourSchedule", fetch = FetchType.EAGER)
    private List<TourGuideSchedule> tourGuides;
    @OneToMany(mappedBy = "tourSchedule", fetch = FetchType.LAZY)
    private List<DestinationOnTourSchedule> destination;

    @OneToMany(mappedBy = "tourSchedule", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<TourBooking> tourBookings;

    public List<TourBooking> getTourBookings() {
        return tourBookings;
    }

    public void setTourBookings(List<TourBooking> tourBookings) {
        this.tourBookings = tourBookings;
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

    public List<TourGuideSchedule> getTourGuides() {
        List<TourGuideSchedule> tourGuideSchedules = this.tourGuides.stream().map(
                tourGuideSchedule -> {
                    tourGuideSchedule.setTourSchedule(this);
                    return tourGuideSchedule;
                }
        ).toList();
        return tourGuideSchedules;
    }

    public void setTourGuides(List<TourGuideSchedule> tourGuides) {
        this.tourGuides = tourGuides;
    }

    public List<DestinationOnTourSchedule> getDestination() {
        return destination;
    }

    public void setDestination(List<DestinationOnTourSchedule> destination) {
        this.destination = destination;
    }
}