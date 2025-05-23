package com.example.demo.response;

import java.math.BigDecimal;

public class TourBookingStat {
    private Long bookingId;
    private String customerFullName;
    private Integer numberOfTickets;
    private BigDecimal totalAmount;

    public TourBookingStat(Long bookingId, String customerFullName, Integer numberOfTickets, BigDecimal totalAmount) {
        this.bookingId = bookingId;
        this.customerFullName = customerFullName;
        this.numberOfTickets = numberOfTickets;
        this.totalAmount = totalAmount;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public String getCustomerFullName() {
        return customerFullName;
    }

    public void setCustomerFullName(String customerFullName) {
        this.customerFullName = customerFullName;
    }

    public Integer getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(Integer numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
}
