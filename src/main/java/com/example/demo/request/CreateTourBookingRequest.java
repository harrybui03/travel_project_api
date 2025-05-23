package com.example.demo.request;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Tour;

public class CreateTourBookingRequest {
   Tour tour;
   Customer customer;

    public CreateTourBookingRequest(Tour tour, Customer customer) {
        this.tour = tour;
        this.customer = customer;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
