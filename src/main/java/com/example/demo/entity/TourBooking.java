package com.example.demo.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
public class TourBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer numberOfCustomer;

    public TourBooking(Integer numberOfCustomer, TourSchedule tourSchedule, Customer customer) {
        this.numberOfCustomer = numberOfCustomer;
        this.tourSchedule = tourSchedule;
        this.customer = customer;
    }

    public TourBooking(Long id, Integer numberOfCustomer, TourSchedule tourSchedule, Customer customer) {
        this.id = id;
        this.numberOfCustomer = numberOfCustomer;
        this.tourSchedule = tourSchedule;
        this.customer = customer;
    }

    @ManyToOne
    @JoinColumn(name = "tour_schedule_id")
    private TourSchedule tourSchedule;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "tourBooking", fetch = FetchType.LAZY)
    private List<Bill> bills = new ArrayList<>();

    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

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

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }
}

