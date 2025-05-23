package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long ticketNumber;

    @ManyToOne
    @JoinColumn(name = "tour_schedule_id")
    @JsonIgnore
    private TourSchedule tourSchedule;

    @ManyToOne()
    private TourBooking tourBooking;

    @ManyToOne()
    private Bill bill;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(Long ticketNumber) {
        this.ticketNumber = ticketNumber;
    }


    public Ticket(Long ticketNumber, TourBooking tourBooking) {
        this.ticketNumber = ticketNumber;
        this.tourBooking = tourBooking;
    }

    public Ticket() {
    }

    public TourSchedule getTourSchedule() {
        return tourSchedule;
    }

    public void setTourSchedule(TourSchedule tourSchedule) {
        this.tourSchedule = tourSchedule;
    }

    public TourBooking getTourBooking() {
        return tourBooking;
    }

    public void setTourBooking(TourBooking tourBooking) {
        this.tourBooking = tourBooking;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }
}
