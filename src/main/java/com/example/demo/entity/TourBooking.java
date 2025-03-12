package com.example.demo.entity;

import jakarta.persistence.*;



@Entity
public class TourBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer numberOfCustomer;

    @ManyToOne
    @JoinColumn(name = "tour_schedule_id")
    private TourSchedule tourSchedule;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;


    public TourBooking() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public TourSchedule getTourSchedule() {
        return tourSchedule;
    }

    public void setTourSchedule(TourSchedule tourSchedule) {
        this.tourSchedule = tourSchedule;
    }

    public Integer getNumberOfCustomer() {
        return numberOfCustomer;
    }

    public void setNumberOfCustomer(Integer numberOfCustomer) {
        this.numberOfCustomer = numberOfCustomer;
    }
}

