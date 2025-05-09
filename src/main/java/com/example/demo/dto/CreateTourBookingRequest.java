package com.example.demo.dto;

public class CreateTourBookingRequest {
    private int numberOfTicket;
    private Long userId;

    public CreateTourBookingRequest(int numberOfTicket, Long userId) {
        this.numberOfTicket = numberOfTicket;
        this.userId = userId;
    }

    public int getNumberOfTicket() {
        return numberOfTicket;
    }

    public void setNumberOfTicket(int numberOfTicket) {
        this.numberOfTicket = numberOfTicket;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
