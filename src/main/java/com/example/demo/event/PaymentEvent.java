package com.example.demo.event;

import com.example.demo.entity.PartnerPayment;
import com.example.demo.entity.enums.PaymentStatus;

public class PaymentEvent {

    private PartnerPayment payment;
    private PaymentStatus eventType; // CREATED, APPROVED, PAID

    public PaymentEvent(PartnerPayment payment, PaymentStatus eventType) {
        this.payment = payment;
        this.eventType = eventType;
    }

    public PartnerPayment getPayment() { return payment; }
    public PaymentStatus getEventType() { return eventType; }
}
